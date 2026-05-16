package org.kisu.units.base

import org.kisu.hasFraction
import org.kisu.prefixes.Binary
import org.kisu.prefixes.isCanonical
import org.kisu.units.Measure
import org.kisu.units.base.Bit.Companion.UNIT
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
 * - Negative values are not permitted.
 * - In canonical form, the quantity must also be whole: fractional raw bits are rejected.
 * - Fractional prefixed values are allowed only when they still resolve to a whole number of bits.
 *
 * Precision is maintained using [BigDecimal].
 *
 * Instances are immutable and safely validated at construction.
 */
class Information private constructor(magnitude: BigDecimal, expression: Bit) :
    Measure<Bit, Information>(magnitude, expression, Companion::invoke) {

    companion object {
        /**
         * Creates a new [Information] quantity with the given [magnitude] and [expression].
         *
         * If [expression] is canonical, [magnitude] must be a whole number of bits.
         *
         * @param magnitude The magnitude of the information quantity.
         * @param expression The bit expression, including its [Binary] prefix.
         * @return A new [Information] instance with the specified magnitude and expression.
         * @throws SubBitInformation if a non-integer bit value is used with the base unit.
         */
        operator fun invoke(
            magnitude: BigDecimal,
            expression: Bit = Bit(Binary.BASE),
        ): Information {
            if (expression.isCanonical && magnitude.hasFraction) {
                throw SubBitInformation(magnitude, UNIT.toString())
            }
            val information = Information(magnitude, expression)
            information.canonical // Forces validation in canonical bits so sub-bit values fail fast
            return information
        }

        /**
         * Creates a new [Information] quantity with the given [magnitude] and binary [prefix].
         *
         * @param magnitude The magnitude of the information quantity.
         * @param prefix The [Binary] prefix to apply to the bit unit.
         * @return A new [Information] instance with the specified magnitude and prefix.
         * @throws SubBitInformation if a non-integer bit value is used with the base unit.
         */
        operator fun invoke(
            magnitude: BigDecimal,
            prefix: Binary = Binary.BASE,
        ) = invoke(magnitude, Bit(prefix))
    }
}

/**
 * Represents the library's scalar bit unit.
 *
 * One bit represents the canonical unit of digital information.
 * Use [Bit.UNIT] for the canonical unit.
 *
 * This type uses [Binary] prefixes such as `Ki`, `Mi`, and `Gi`.
 */
class Bit private constructor(
    prefix: Binary,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Binary, Bit>(prefix, overflow, unit, ::Bit) {

    /**
     * Secondary constructor for convenience.
     *
     * @param prefix Binary prefix to apply to the bit unit.
     */
    constructor(prefix: Binary = Binary.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The canonical symbol for digital information: "bit". */
        internal val UNIT = Unit("bit", 1)
    }
}
