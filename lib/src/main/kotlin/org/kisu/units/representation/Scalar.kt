package org.kisu.units

import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.ScalarSystem
import org.kisu.prefixes.primitives.System
import java.math.BigDecimal

/**
 * Represents a scalar unit, composed of a [Prefix] and a unit symbol (e.g., "km" for kilo + meter, "μs" for micro +
 * second).
 *
 * This is the atomic element of the [Expression] system, which models complex unit compositions such as "N⋅m" or
 * "mol⋅K".
 *
 * A [Scalar] combines a prefix and a base unit into a single symbolic component, and can be composed with other
 * expressions through multiplication and division to form [Product] and [Quotient] types.
 *
 * It delegates [System] behavior to a [ScalarSystem] built from the same prefix and unit.
 *
 * @param A the type of prefix used in this scalar unit.
 * @property prefix the prefix applied to the unit (e.g., kilo, micro).
 * @property unit the base unit symbol (e.g., "m" for meter, "s" for second).
 */
class Scalar<A>(
    private val prefix: A,
    private val unit: String
) : Expression<Scalar<A>>(), System<Scalar<A>> by ScalarSystem(prefix, unit)
    where A : Prefix<A>, A : System<A> {

    /** The scaling factor of this scalar unit, delegated from the prefix. */
    override val factor: BigDecimal = prefix.factor

    /** The combined symbol of the prefix and unit (e.g., "km", "μs"). */
    override val symbol: String by lazy { prefix.symbol + unit }

    /**
     * Multiplies this scalar unit with another scalar unit, returning a [Product] expression.
     *
     * Algebra: *A* · *B*
     *
     * Example: `km · ms` → a scalar product representing kilometre·millisecond.
     */
    operator fun <B> times(other: Scalar<B>): Product<Scalar<A>, Scalar<B>> where B : Prefix<B>, B : System<B> =
        Product(this, other)

    /**
     * Multiplies this scalar unit with an existing [Product], producing a nested [Product] expression.
     *
     * Algebra: *A* · (*B* · *C*)
     *
     * Example: `km · (ms · Mg)` → kilometre·millisecond·megagram (nested structure preserved).
     */
    operator fun <B, C> times(other: Product<B, C>): Product<Scalar<A>, Product<B, C>>
        where B : Expression<B>, B : System<B>, C : Expression<C>, C : System<C> =
        Product(this, other)

    /**
     * Multiplies this scalar unit with a [Quotient], producing a [Quotient] where the numerator is a [Product].
     *
     * Algebra: *A* · (*B* / *C*) = (*A* · *B*) / *C*
     *
     * Example: `km · (ms / ng)` → (kilometre·millisecond) / nanogram
     */
    operator fun <B, C> times(other: Quotient<B, C>): Quotient<Product<Scalar<A>, B>, C>
        where B : Expression<B>, B : System<B>, C : Expression<C>, C : System<C> =
        Quotient(Product(this, other.component1()), other.component2())

    /**
     * Divides this scalar unit by another scalar unit, producing a [Quotient] expression.
     *
     * Algebra: *A* / *B*
     *
     * Example: `km / ms` → kilometre / millisecond
     */
    operator fun <B> div(other: Scalar<B>): Quotient<Scalar<A>, Scalar<B>> where B : Prefix<B>, B : System<B> =
        Quotient(this, other)

    /**
     * Divides this scalar unit by a [Product], producing a [Quotient] with a product in the denominator.
     *
     * Algebra: *A* / (*B* · *C*)
     *
     * Example: `km / (ms · Mg)` → kilometre / (millisecond·megagram)
     */
    operator fun <B, C> div(other: Product<B, C>): Quotient<Scalar<A>, Product<B, C>>
        where B : Expression<B>, B : System<B>, C : Expression<C>, C : System<C> =
        Quotient(this, other)

    /**
     * Divides this scalar unit by a [Quotient], producing a [Quotient] where the original denominator becomes the
     * numerator.
     *
     * Algebra: *A* / (*B* / *C*) = (*A* · *C*) / *B*
     *
     * Example: `km / (ms / ng)` → (kilometre·nanogram) / millisecond
     */
    operator fun <B, C> div(other: Quotient<B, C>): Quotient<Product<Scalar<A>, C>, B>
        where B : Expression<B>, B : System<B>, C : Expression<C>, C : System<C> =
        Quotient(Product(this, other.component2()), other.component1())
}
