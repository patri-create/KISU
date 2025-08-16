package org.kisu.units.representation

import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.CompositeSystem
import org.kisu.prefixes.primitives.System
import org.kisu.productSymbol
import java.math.BigDecimal

/**
 * Represents the product of two unit expressions in the physical unit system.
 *
 * This class is part of the unit expression framework for modeling complex physical units
 * such as `N·m` (newton-meter), `mol·K`, or `W·s`, by combining simpler unit components through
 * multiplication.
 *
 * A [Product] composes two expressions — [left] and [right] — which are themselves both
 * [Expression]s and [System]s. The resulting expression retains type safety and can be further
 * composed, multiplied, or divided with other units to build arbitrarily complex units.
 *
 * For example:
 * - `Newton * Meter` becomes `Product<Newton, Meter>` and represents `N·m`
 * - `Mole * Kelvin` becomes `mol·K`
 * - You can further multiply or divide these to construct expressions like `kg·m/s²`
 *
 * @param A the type of the left component (e.g., `Newton`, `Mole`), must implement [Expression] and [System]
 * @param B the type of the right component (e.g., `Meter`, `Kelvin`), must implement [Expression] and [System]
 * @property left the left unit expression in the product
 * @property right the right unit expression in the product
 */
class Product<A, B>(
    private val left: A,
    private val right: B
) : Expression<Product<A, B>>(), System<Product<A, B>> by CompositeSystem(left, right, ::Product)
    where A : Expression<A>, A : System<A>, B : Expression<B>, B : System<B> {
    /**
     * The numerical factor of this unit product, computed as the product of the factors
     * of the left and right components.
     *
     * This allows correct scaling when units carry metric or binary prefixes, such as `kN·mm`
     * or `μmol·K`.
     */
    override val factor: BigDecimal by lazy { left.factor * right.factor }

    /**
     * The symbol representing the unit product, rendered as `"left·right"`.
     *
     * For example, a product of `N` and `m` will be rendered as `"N·m"`.
     */
    override val symbol: String by lazy {
        factors.productSymbol
    }

    /**
     * The normalized set of scalar factors that result from the product of two expressions.
     *
     * This property computes the combined factors of the [left] and [right] expressions,
     * merging them by symbol. Scalars with the same symbol are coalesced.
     *
     * Zero-valued scalars are excluded both before and after the reduction to ensure the resulting
     * set contains only meaningful (non-neutral) components.
     *
     * This normalization step is essential to simplify compound expressions by collapsing
     * duplicate units (e.g., `m * m` becomes `m²`) and removing neutral elements (e.g., units
     * raised to the power of 0).
     *
     * The computation is cached after the first access using [lazy].
     */
    override val factors: Set<Scalar<*, *>> by lazy {
        left.factors.toList()
            .plus(right.factors)
            .filter { !it.zero }
            .groupBy { factor -> factor.symbol }
            .map { (_, group) ->
                @Suppress("UNCHECKED_CAST")
                val castedGroup = group as List<Scalar<Any, Any>>
                castedGroup.reduce { a, b -> (a + b) as Scalar<Any, Any> }
            }
            .filter { !it.zero }
            .toSet()
    }

    /**
     * Multiplies this product expression by a scalar unit, producing a nested [Product].
     *
     * Algebra: (*A* · *B*) · *C*
     *
     * Example:
     * `(N · mol) · s = N · mol · s`
     * Represents newton·mole·second.
     */
    operator fun <C, SelfC> times(other: Scalar<C, SelfC>): Product<Product<A, B>, SelfC>
        where C : Prefix<C>, C : System<C>, SelfC : Scalar<C, SelfC> = Product(this, other.self)

    /**
     * Multiplies this product expression by another product, yielding a nested [Product].
     *
     * Algebra: (*A* · *B*) · (*C* · *D*)
     *
     * Example:
     * `(N · mol) · (s · K) = N · mol · s · K`
     * Represents newton·mole·second·kelvin.
     */
    operator fun <C, D> times(other: Product<C, D>): Product<Product<A, B>, Product<C, D>>
        where C : Expression<C>, C : System<C>, D : Expression<D>, D : System<D> =
        Product(this, other)

    /**
     * Multiplies this product expression by a [Quotient], combining the numerator and preserving the denominator.
     *
     * Algebra: (*A* · *B*) · (*C* / *D*) = (*A* · *B* · *C*) / *D*
     *
     * Example:
     * `(N · mol) · (s / A) = (N · mol · s) / A`
     * Represents newton·mole·second per ampere.
     */
    operator fun <C, D> times(other: Quotient<C, D>): Quotient<Product<Product<A, B>, C>, D>
        where C : Expression<C>, C : System<C>, D : Expression<D>, D : System<D> =
        Quotient(Product(this, other.component1()), other.component2())

    /**
     * Divides this product expression by a scalar unit, forming a [Quotient].
     *
     * Algebra: (*A* · *B*) / *C*
     *
     * Example:
     * `(N · mol) / s = N · mol / s`
     * Represents newton·mole per second.
     */
    operator fun <C, SelfC> div(other: Scalar<C, SelfC>): Quotient<Product<A, B>, SelfC>
        where C : Prefix<C>, C : System<C>, SelfC : Scalar<C, SelfC> = Quotient(this, other.self)

    /**
     * Divides this product expression by another product expression, forming a [Quotient].
     *
     * Algebra: (*A* · *B*) / (*C* · *D*)
     *
     * Example:
     * `(N · mol) / (s · K) = N · mol / (s · K)`
     * Represents newton·mole per (second·kelvin).
     */
    operator fun <C, D> div(other: Product<C, D>): Quotient<Product<A, B>, Product<C, D>>
        where C : Expression<C>, C : System<C>, D : Expression<D>, D : System<D> =
        Quotient(this, other)

    /**
     * Divides this product expression by a [Quotient], inverting the denominator and multiplying.
     *
     * Algebra: (*A* · *B*) / (*C* / *D*) = (*A* · *B* · *D*) / *C*
     *
     * Example:
     * `(N · mol) / (s / A) = (N · mol · A) / s`
     * Represents newton·mole·ampere per second.
     */
    operator fun <C, D> div(other: Quotient<C, D>): Quotient<Product<Product<A, B>, D>, C>
        where C : Expression<C>, C : System<C>, D : Expression<D>, D : System<D> =
        Quotient(Product(this, other.component2()), other.component1())
}
