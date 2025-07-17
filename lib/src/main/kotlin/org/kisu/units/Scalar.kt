package org.kisu.units

import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.ScalarSystem
import org.kisu.prefixes.primitives.System
import java.math.BigDecimal

/**
 * Represents a scalar unit composed of a [Prefix] and a unit symbol, such as "km" (kilo + meter)
 * or "μs" (micro + second).
 *
 * This is the base case of an [Expression], used to join together a prefix and a raw unit string,
 * producing a complete symbol representation (e.g., "k" + "m" → "km").
 *
 * It delegates [System] behavior to a [ScalarSystem] composed of the same prefix and unit.
 *
 * @param A the type of prefix used in this scalar unit.
 * @property prefix the prefix applied to the unit (e.g., kilo, milli).
 * @property unit the symbol of the unit itself (e.g., "m" for meter).
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
}
