package org.kisu.units

import org.kisu.KisuConfig
import org.kisu.compositeRepresentation
import org.kisu.prefixes.primitives.CompositeSystem
import org.kisu.prefixes.primitives.System
import java.math.BigDecimal

/**
 * Represents a composite [Expression] formed by the quotient of two other expressions,
 * such as "m/s" (meter per second) or "J/(mol·K)" (joule per mole-kelvin).
 *
 * This class models divisive unit relationships, combining a numerator and a denominator,
 * and delegates [System] behavior to a [CompositeSystem] that understands both sides.
 *
 * The resulting symbol is a slash-separated string of the numerator and denominator
 * composite representations. If either side is itself a composite (e.g., a [Product] or [Quotient]),
 * its representation is wrapped in parentheses to preserve grouping — e.g., "(kg·m)/(s²)".
 *
 * @param A the type of the numerator expression.
 * @param B the type of the denominator expression.
 * @property numerator the expression on the top of the quotient.
 * @property denominator the expression on the bottom of the quotient.
 */
class Quotient<A, B>(
    private val numerator: A,
    private val denominator: B
) : Expression<Quotient<A, B>>(), System<Quotient<A, B>> by CompositeSystem(numerator, denominator, ::Quotient)
    where A : Expression<A>, A : System<A>, B : Expression<B>, B : System<B> {

    /** The combined factor of the quotient, equal to numerator divided by denominator. */
    override val factor: BigDecimal by lazy {
        numerator.factor.divide(denominator.factor, KisuConfig.precision)
    }

    /**
     * The combined symbol, rendered as "numerator/denominator". If either operand is a composite expression,
     * its representation will be wrapped in parentheses — e.g., "W/(m·K)".
     */
    override val symbol: String by lazy {
        "${numerator.compositeRepresentation}/${denominator.compositeRepresentation}"
    }
}
