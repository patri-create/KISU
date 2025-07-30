package org.kisu.units.base

import org.kisu.hasFraction
import org.kisu.prefixes.Binary
import org.kisu.prefixes.isCanonical
import org.kisu.units.Measure
import org.kisu.units.exceptions.NegativeInformation
import org.kisu.units.exceptions.SubBitInformation
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the quantity of **digital information**, measured in bits.
 *
 * This class models the fundamental unit of information in computing and digital communications: the **bit** (binary
 * digit).
 *
 * It supports binary prefixes such as kibibit (Ki), mebibit (Mi), gibibit (Gi), etc., allowing precise modeling of
 * quantities such as storage, memory, and bandwidth.
 *
 * - The **smallest representable unit** is a single bit.
 * - Negative values are not permitted, as information content cannot be less than zero.
 * - In canonical form (i.e., using the base prefix), the quantity must also be **whole** — fractional bits have no
 *   meaningful interpretation in physical or computational systems.
 *
 * This class enforces those constraints and will reject values that violate them. Precision is maintained using
 * [BigDecimal].
 *
 * Instances are immutable and safely validated at construction.
 */
class Information private constructor(magnitude: BigDecimal, expression: Scalar<Binary>) :
    Measure<Scalar<Binary>, Information>(magnitude, expression, Companion::invoke) {

    companion object {
        /** The unit symbol for digital information: "bit". */
        internal val UNIT = Unit("bit", 1)

        /**
         * Creates a new [Information] quantity with the given [magnitude] and [expression].
         *
         * Constraints:
         * - The [magnitude] must be strictly positive; negative information is physically meaningless.
         * - If the [expression] is canonical (i.e., no prefix, representing raw bits), the [magnitude] must not include
         * fractional parts.
         *   Fractional bits are not representable as atomic units of digital information.
         *
         * If these constraints are violated:
         * - A [NegativeInformation] exception is thrown for negative values.
         * - A [SubBitInformation] exception is thrown for fractional bit values in canonical form.
         *
         * @param magnitude The magnitude of the information quantity.
         * @param expression The binary prefix to apply (e.g., Ki, Mi, Gi, etc.).
         * @return A new [Information] instance with the specified magnitude and prefix.
         * @throws NegativeInformation if the value is negative.
         * @throws SubBitInformation if a non-integer bit value is used with the base unit.
         */
        operator fun invoke(
            magnitude: BigDecimal,
            expression: Scalar<Binary> = Scalar(Binary.BASE, unit = UNIT),
        ): Information {
            if (expression.isCanonical && magnitude.hasFraction) {
                throw SubBitInformation(magnitude, UNIT.toString())
            }
            val information = Information(magnitude, expression)
            information.canonical // Forces evaluation for potential validation during construction
            return information
        }

        /**
         * Creates a new [Information] quantity with the given [magnitude] and [prefix].
         *
         * Constraints:
         * - The [magnitude] must be strictly positive; negative information is physically meaningless.
         * - If the [prefix] is canonical (i.e., no prefix, representing raw bits), the [magnitude] must not include
         * fractional parts.
         *   Fractional bits are not representable as atomic units of digital information.
         *
         * If these constraints are violated:
         * - A [NegativeInformation] exception is thrown for negative values.
         * - A [SubBitInformation] exception is thrown for fractional bit values in canonical form.
         *
         * @param magnitude The magnitude of the information quantity.
         * @param prefix The binary prefix to apply (e.g., Ki, Mi, Gi, etc.).
         * @return A new [Information] instance with the specified magnitude and prefix.
         * @throws NegativeInformation if the value is negative.
         * @throws SubBitInformation if a non-integer bit value is used with the base unit.
         */
        operator fun invoke(
            magnitude: BigDecimal,
            prefix: Binary = Binary.BASE,
        ) = invoke(magnitude, Scalar(prefix, unit = UNIT))
    }
}
