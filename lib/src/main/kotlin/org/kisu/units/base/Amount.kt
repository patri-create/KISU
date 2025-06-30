package org.kisu.units.base

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Amount.Companion.AVOGADROS_NUMBER
import org.kisu.units.exceptions.NegativeAmountOfSubstance
import java.math.BigDecimal

/**
 * Represents the physical quantity of **amount of substance**, measured in moles (mol).
 *
 * This class models the SI base unit for counting discrete entities like atoms, molecules, or particles in a substance.
 * One mole corresponds to [AVOGADROS_NUMBER] elementary entities, typically used in chemistry and physics.
 *
 * The amount is composed of a [magnitude] and an optional metric [prefix], allowing expressions such as millimoles
 * (mmol), micromoles (µmol), or kilomoles (kmol).
 *
 * The value must not be negative, as a physical quantity representing a count of real entities cannot be less than
 * zero.
 *
 * Negative amounts would be physically meaningless in the context of matter.
 *
 * Instances of this class are immutable and preserve their precision using [BigDecimal].
 */
class Amount private constructor(magnitude: BigDecimal, prefix: Metric) :
    Measure<Metric, Amount>(magnitude, prefix, SYMBOL) {
    /**
     * Creates a new [Amount] with the given [magnitude] and [prefix].
     *
     * The magnitude must be zero or positive. A negative amount of substance is not allowed,
     * as it would represent a negative count of entities, which is physically meaningless.
     *
     * @throws NegativeAmountOfSubstance if the magnitude is less than zero.
     */
    override fun invoke(
        magnitude: BigDecimal,
        prefix: Metric,
    ): Amount {
        if (magnitude < BigDecimal.ZERO) {
            throw NegativeAmountOfSubstance(magnitude, prefix, SYMBOL)
        }
        return Amount(magnitude, prefix)
    }

    companion object {
        /** The SI symbol for amount of substance: "mol". */
        private const val SYMBOL = "mol"

        /**
         * Avogadro's number — the number of entities in one mole:
         * 6.02214076 × 10²³ entities per mole.
         *
         * This is a fundamental physical constant.
         */
        val AVOGADROS_NUMBER: BigDecimal = BigDecimal("6.02214076e23")
    }
}
