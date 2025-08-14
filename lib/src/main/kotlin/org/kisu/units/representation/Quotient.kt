package org.kisu.units.representation

import org.kisu.KisuConfig
import org.kisu.Matcher
import org.kisu.Merger
import org.kisu.intersect
import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.CompositeSystem
import org.kisu.prefixes.primitives.System
import org.kisu.productSymbol
import java.math.BigDecimal

/**
 * Represents the quotient of two unit expressions in the physical unit system.
 *
 * This class is part of the expression system used to model complex **divisive physical units**,
 * such as:
 * - `m/s` (meter per second)
 * - `J/(mol·K)` (joule per mole-kelvin)
 * - `(kg·m)/(s²)` (newton)
 *
 * A [Quotient] is formed by dividing a [numerator] expression by a [denominator] expression,
 * both of which are themselves required to be [Expression]s and [System]s.
 *
 * It preserves mathematical and symbolic correctness by:
 * - Delegating system behavior to a [CompositeSystem]
 * - Automatically wrapping composite expressions in parentheses in the unit symbol
 * - Computing the resulting factor as the division of numerator and denominator factors
 *
 * @param A the type of the numerator expression (e.g., `Joule`, `Meter`, or a [Product])
 * @param B the type of the denominator expression (e.g., `Second`, `mol·K`, or another [Product])
 * @property numerator the expression in the numerator of the quotient
 * @property denominator the expression in the denominator of the quotient
 */
class Quotient<A, B>(
    private val numerator: A,
    private val denominator: B
) : Expression<Quotient<A, B>>(),
    System<Quotient<A, B>> by CompositeSystem(numerator, denominator, ::Quotient)
    where A : Expression<A>, A : System<A>, B : Expression<B>, B : System<B> {

    /**
     * The numeric factor of the quotient, computed as numerator divided by denominator.
     *
     * This ensures accurate scaling for expressions involving prefixed units such as
     * `kN/s`, `MJ/(mol·K)`, or `μA/mm²`.
     */
    override val factor: BigDecimal by lazy {
        numerator.factor.divide(denominator.factor, KisuConfig.precision)
    }

    /**
     * A symbolic string representation of the quotient expression.
     *
     * This symbol is computed by partitioning the [factors] into numerators and denominators
     * based on their sign (positive or negative). It then constructs a textual representation
     * in the form:
     *
     * - `"1/denominator"` if there are no numerator factors.
     * - `"numerator"` if there are no denominator factors.
     * - `"numerator/denominator"` otherwise.
     *
     * When multiple denominator terms exist, they are grouped in parentheses to preserve
     * correct order of operations (e.g., `"m/(kg·s²)"`).
     *
     * This representation is useful for displaying compound units such as acceleration
     * (`m/s²`) or pressure (`kg/(m·s²)`).
     */
    override val symbol: String by lazy {
        val (numerators, denominators) = factors.partition { it.positive }
        val numerator = numerators.productSymbol
        val denominator = denominators.map { it.inverted }.productSymbol
            .let { if (denominators.size > 1) "($it)" else it }

        when {
            numerators.isEmpty() && denominators.isEmpty() -> ""
            numerators.isEmpty() -> "1/$denominator"
            denominators.isEmpty() -> numerator
            else -> "$numerator/$denominator"
        }
    }

    /**
     * The reduced set of scalar factors that define this quotient expression.
     *
     * This combines the [factors] of the [numerator] and [denominator] expressions by:
     * - Finding scalars with matching units and merging them using subtraction (`this - other`).
     * - Retaining scalars that are only in the numerator.
     * - Inverting and including scalars that are only in the denominator.
     *
     * Scalars with a zero exponent (i.e., dimensionless or neutral) are excluded from the final result.
     *
     * The result is a normalized set that simplifies expressions like `m / m` (cancelled) or `m / s²`
     * (resulting in `m·s⁻²`).
     */
    @Suppress("UNCHECKED_CAST")
    override val factors: Set<Scalar<*>> by lazy {
        val numerators = numerator.factors
        val denominators = denominator.factors

        val matcher: Matcher<Scalar<*>> = { other -> this.unit == other.unit }
        val merger: Merger<Scalar<*>> = { other -> (this as Scalar<Any>) - (other as Scalar<Any>) }

        val intersected = numerators.intersect(denominators, matcher, merger)

        val onlyInLeft = numerators.filterNot { l -> denominators.any { r -> matcher(l, r) } }
        val onlyInRight = denominators.filterNot { r -> numerators.any { l -> matcher(r, l) } }

        (intersected + onlyInLeft + onlyInRight.map { it.inverted }).filter { !it.zero }.toSet()
    }

    /**
     * Returns the numerator component of the quotient.
     */
    operator fun component1() = numerator

    /**
     * Returns the denominator component of the quotient.
     */
    operator fun component2() = denominator

    /**
     * Multiplies this quotient by a scalar unit expression.
     *
     * This multiplies the numerator by the scalar, leaving the denominator unchanged.
     *
     * Algebra: (*A* / *B*) · *C* = (*A* · *C*) / *B*
     *
     * Example:
     * `(J / mol·K) * μ = (J·μ) / (mol·K)`
     * Represents joule times micro in numerator per mole kelvin in denominator.
     */
    operator fun <C> times(other: Scalar<C>): Quotient<Product<A, Scalar<C>>, B>
        where C : Prefix<C>, C : System<C> =
        Quotient(Product(numerator, other), denominator)

    /**
     * Multiplies this quotient by a product of two unit expressions.
     *
     * This multiplies the numerator by the entire product expression.
     *
     * Algebra: (*A* / *B*) · (*C* · *D*) = (*A* · *C* · *D*) / *B*
     *
     * Example:
     * `(m / s) * (kg · s) = (m · kg · s) / s`
     * Represents meter times kilogram times second, divided by second.
     */
    operator fun <C, D> times(other: Product<C, D>): Quotient<Product<A, Product<C, D>>, B>
        where C : Expression<C>, C : System<C>, D : Expression<D>, D : System<D> =
        Quotient(Product(numerator, other), denominator)

    /**
     * Multiplies this quotient by another quotient.
     *
     * Both numerators multiply and both denominators multiply.
     *
     * Algebra: (*A* / *B*) · (*C* / *D*) = (*A* · *C*) / (*B* · *D*)
     *
     * Example:
     * `(J / mol·K) * (V / A) = (J · V) / (mol · K · A)`
     * Represents joule volt per mole kelvin ampere.
     */
    operator fun <C, D> times(other: Quotient<C, D>): Quotient<Product<A, C>, Product<B, D>>
        where C : Expression<C>, C : System<C>, D : Expression<D>, D : System<D> =
        Quotient(
            Product(numerator, other.numerator),
            Product(denominator, other.denominator)
        )

    /**
     * Divides this quotient by a scalar unit expression.
     *
     * This multiplies the denominator by the scalar.
     *
     * Algebra: (*A* / *B*) / *C* = *A* / (*B* · *C*)
     *
     * Example:
     * `(J / mol·K) / μ = J / (mol · K · μ)`
     * Represents joule per mole kelvin micro.
     */
    operator fun <C> div(other: Scalar<C>): Quotient<A, Product<B, Scalar<C>>>
        where C : Prefix<C>, C : System<C> =
        Quotient(numerator, Product(denominator, other))

    /**
     * Divides this quotient by a product of two unit expressions.
     *
     * The denominator is extended by the product.
     *
     * Algebra: (*A* / *B*) / (*C* · *D*) = *A* / (*B* · *C* · *D*)
     *
     * Example:
     * `(kg · m / s²) / (mol · K) = (kg · m) / (s² · mol · K)`
     * Represents kilogram meter over second squared mole kelvin.
     */
    operator fun <C, D> div(other: Product<C, D>): Quotient<A, Product<B, Product<C, D>>>
        where C : Expression<C>, C : System<C>, D : Expression<D>, D : System<D> =
        Quotient(numerator, Product(denominator, other))

    /**
     * Divides this quotient by another quotient.
     *
     * Inverts the divisor and multiplies numerator and denominator accordingly.
     *
     * Algebra: (*A* / *B*) / (*C* / *D*) = (*A* · *D*) / (*B* · *C*)
     *
     * Example:
     * `(a / b) / (c / d) = (a · d) / (b · c)`
     * For example, `(J / mol·K) / (V / A) = (J · A) / (mol · K · V)`
     */
    operator fun <C, D> div(other: Quotient<C, D>)
    where C : Expression<C>, C : System<C>, D : Expression<D>, D : System<D> =
        Quotient(Product(numerator, other.denominator), Product(denominator, other.numerator))
}
