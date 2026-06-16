package org.kisu.units.representation

import org.kisu.KisuConfig
import org.kisu.prefixes.Prefix
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.primitives.ScalarSystem
import org.kisu.prefixes.primitives.System
import org.kisu.zero
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Atomic unit expression consisting of a prefixed [unit], such as `km`, `ms`, or `kg`.
 *
 * A [Scalar] is the building block for composite expressions. It can be combined with other expressions through
 * multiplication and division to produce [Product] and [Quotient] values.
 *
 * @param A The prefix type used by this scalar.
 * @property unit The base unit symbol and exponent carried by this scalar.
 */
abstract class Scalar<A, Self : Scalar<A, Self>>(
    private val algebra: Algebra<A>,
    private val prefix: A,
    internal val unit: Unit,
    private val create: (Algebra<A>, A, Unit) -> Self,
) : Expression<Self>(),
    System<Self> by ScalarSystem(prefix, unit, { prefix, unit -> create(algebra, prefix, unit) })
    where A : Prefix<A>, A : System<A> {

    /**
     * Returns the multiplicative inverse of this scalar.
     *
     * This creates a new [Scalar] with the same [prefix], but with the [unit] inverted.
     * For example, if the current scalar represents "m", the inverted scalar represents "1/m".
     *
     * The result is computed lazily and cached for future access.
     */
    val inverted: Self by lazy { create(algebra, prefix, unit.inverted) }

    /**
     * Indicates whether this scalar's [unit] has a positive exponent.
     *
     * Returns `true` if the [unit] represents a positive power (e.g., m²),
     * and `false` if it is inverted or has a negative exponent (e.g., m⁻¹).
     *
     * The result is computed lazily and cached.
     */
    val positive: Boolean by lazy {
        unit.positive
    }

    /**
     * Indicates whether this scalar's [unit] has a zero exponent.
     *
     * Returns `true` if the [unit] represents a positive power (e.g., m⁰), and `false` otherwise.
     *
     * The result is computed lazily and cached.
     */
    val zero: Boolean by lazy {
        unit.zero
    }

    internal val self: Self by lazy {
        @Suppress("UNCHECKED_CAST")
        this as Self
    }

    /** The scaling factor of this scalar unit, computed by the configured prefix algebra. */
    override val factor: BigDecimal by lazy { algebra.factor(prefix) }

    override fun decompose(magnitude: BigDecimal): List<Pair<BigDecimal, Self>> {
        var remainder = magnitude.stripTrailingZeros().abs()
        val ordered = prefix.all.sortedDescending()
        return ordered.foldIndexed(listOf<Pair<BigDecimal, A>>()) { index, list, prefix ->
            val factor = algebra.factor(prefix)
            val quotient =
                if (index == ordered.lastIndex) {
                    remainder.divide(factor, KisuConfig.precision)
                } else {
                    remainder.divide(factor, 0, RoundingMode.DOWN)
                }
            (list + (quotient to prefix)).also {
                remainder -= factor.multiply(quotient)
            }
        }.filter { (measure, _) -> !measure.zero }
            .map { (magnitude, prefix) -> magnitude to create(algebra, prefix, unit) }
    }

    /** The combined symbol of the prefix and unit (e.g., "km", "μs"). */
    override val symbol: String by lazy {
        if (unit.zero) {
            ""
        } else {
            prefix.symbol + unit
        }
    }

    /**
     * The only factor of a scalar is itself.
     *
     * This defines the scalar as an atomic unit in dimensional expressions—irreducible
     * and self-contained. It is used when building composite expressions from basic scalars.
     */
    override val factors: Set<Scalar<*, *>> = sortedSetOf(this)

    override fun to(other: Self): BigDecimal =
        factor.divide(other.factor, KisuConfig.precision)

    /**
     * Multiplies this scalar expression by [other] within the same scalar family.
     *
     * This combines both prefixes and unit exponents. For example, `m + m` yields `m²`, while `km + m` yields a
     * scalar whose factor reflects the combined prefixes.
     *
     * @param other The scalar expression to multiply with this one.
     * @return A new scalar expression representing the product.
     */
    operator fun plus(other: Self): Self {
        val (prefix) = algebra.multiply(prefix, other.prefix)
        val remainder = factor
            .multiply(other.factor)
            .divide(algebra.factor(prefix), KisuConfig.precision)
        return create(algebra.adjustedBy(remainder), prefix, unit * other.unit)
    }

    internal fun addErased(other: Scalar<*, *>): Scalar<*, *> = self + requireSameFamily(other)

    internal fun subtractErased(other: Scalar<*, *>): Scalar<*, *> = self - requireSameFamily(other)

    @Suppress("UNCHECKED_CAST")
    private fun requireSameFamily(other: Scalar<*, *>): Self {
        require(this::class == other::class) {
            "Cannot combine scalar families: ${this::class.qualifiedName} vs ${other::class.qualifiedName}"
        }
        return other as Self
    }

    /**
     * Divides this scalar expression by [other] within the same scalar family.
     *
     * This subtracts unit exponents and adjusts the prefix factor accordingly.
     *
     * @param other The scalar expression to divide out of this one.
     * @return A new scalar expression representing the quotient.
     */
    operator fun minus(other: Self): Self {
        val (prefix) = algebra.divide(prefix, other.prefix)
        val remainder = factor
            .divide(other.factor, KisuConfig.precision)
            .divide(algebra.factor(prefix), KisuConfig.precision)
        return create(algebra.adjustedBy(remainder), prefix, unit / other.unit)
    }

    /**
     * Multiplies this scalar unit with another scalar unit, returning a [Product] expression.
     *
     * Algebra: *A* · *B*
     *
     * Example: `km · ms` → a scalar product representing kilometre·millisecond.
     */
    operator fun <B, SelfB> times(other: Scalar<B, SelfB>): Product<Self, SelfB>
        where B : Prefix<B>, B : System<B>, SelfB : Scalar<B, SelfB> =
        Product(self, other.self)

    /**
     * Multiplies this scalar unit with an existing [Product], producing a nested [Product] expression.
     *
     * Algebra: *A* · (*B* · *C*)
     *
     * Example: `km · (ms · Mg)` → kilometre·millisecond·megagram (nested structure preserved).
     */
    operator fun <B, C> times(other: Product<B, C>): Product<Self, Product<B, C>>
        where B : Expression<B>, B : System<B>, C : Expression<C>, C : System<C> =
        Product(self, other)

    /**
     * Multiplies this scalar unit with a [Quotient], producing a [Quotient] where the numerator is a [Product].
     *
     * Algebra: *A* · (*B* / *C*) = (*A* · *B*) / *C*
     *
     * Example: `km · (ms / ng)` → (kilometre·millisecond) / nanogram
     */
    operator fun <B, C> times(other: Quotient<B, C>): Quotient<Product<Self, B>, C>
        where B : Expression<B>, B : System<B>, C : Expression<C>, C : System<C> =
        Quotient(Product(self, other.component1()), other.component2())

    /**
     * Divides this scalar unit by another scalar unit, producing a [Quotient] expression.
     *
     * Algebra: *A* / *B*
     *
     * Example: `km / ms` → kilometre / millisecond
     */
    operator fun <B, SelfB> div(other: Scalar<B, SelfB>): Quotient<Self, SelfB>
        where B : Prefix<B>, B : System<B>, SelfB : Scalar<B, SelfB> =
        Quotient(self, other.self)

    /**
     * Divides this scalar unit by a [Product], producing a [Quotient] with a product in the denominator.
     *
     * Algebra: *A* / (*B* · *C*)
     *
     * Example: `km / (ms · Mg)` → kilometre / (millisecond·megagram)
     */
    operator fun <B, C> div(other: Product<B, C>): Quotient<Self, Product<B, C>>
        where B : Expression<B>, B : System<B>, C : Expression<C>, C : System<C> =
        Quotient(self, other)

    /**
     * Divides this scalar unit by a [Quotient], producing a [Quotient] where the original denominator becomes the
     * numerator.
     *
     * Algebra: *A* / (*B* / *C*) = (*A* · *C*) / *B*
     *
     * Example: `km / (ms / ng)` → (kilometre·nanogram) / millisecond
     */
    operator fun <B, C> div(other: Quotient<B, C>): Quotient<Product<Self, C>, B>
        where B : Expression<B>, B : System<B>, C : Expression<C>, C : System<C> =
        Quotient(Product(self, other.component2()), other.component1())
}

private fun <A> Algebra<A>.adjustedBy(remainder: BigDecimal): Algebra<A> where A : Prefix<A>, A : System<A> {
    if (remainder.compareTo(BigDecimal.ONE) == 0) {
        return this
    }

    val delegate = this
    return object : Algebra<A> {
        override fun factor(prefix: A): BigDecimal = delegate.factor(prefix) * remainder

        override fun multiply(left: A, right: A): Pair<A, BigDecimal> =
            delegate.multiply(left, right).let { (prefix, overflow) -> prefix to overflow * remainder }

        override fun divide(left: A, right: A): Pair<A, BigDecimal> =
            delegate.divide(left, right).let { (prefix, overflow) ->
                prefix to overflow.divide(remainder, KisuConfig.precision)
            }
    }
}
