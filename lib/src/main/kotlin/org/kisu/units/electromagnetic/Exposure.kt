package org.kisu.units.electromagnetic

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Coulomb
import java.math.BigDecimal

/**
 * Represents **radiation exposure** (X), which quantifies the amount of ionizing
 * radiation in terms of the electric charge produced per unit mass of air.
 *
 * - **Dimension**: electric charge per mass (C/kg)
 * - **SI Unit**: coulomb per kilogram (C/kg)
 *
 * Exposure measures the ability of ionizing radiation to ionize air molecules,
 * and it is primarily used in **radiation protection** and **dosimetry**.
 * While it characterizes the intensity of radiation in air, it does not directly
 * account for the absorbed dose within human tissue.
 *
 * @param magnitude numerical value of the measure
 * @param expression unit expression in coulomb per kilogram (C/kg)
 */
class Exposure(
    magnitude: BigDecimal,
    expression: CoulombPerKilogram
) : Measure<Exposure.CoulombPerKilogram, Exposure>(magnitude, expression, ::Exposure) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, CoulombPerKilogram(prefix))

    /**
     * Represents the SI unit **coulomb per kilogram (C/kg)**.
     *
     * This unit measures **radiation exposure**, i.e., the amount of ionizing
     * radiation in terms of the electric charge produced per unit mass of air.
     * It is defined as the [Quotient] of [Coulomb] (electric charge) and [Kilogram] (mass).
     *
     * Example usages include:
     * - Measuring ionizing radiation in air
     * - Radiation protection and dosimetry calculations
     *
     * @see Exposure for the physical quantity represented by this unit.
     */
    typealias CoulombPerKilogram = Quotient<Coulomb, Kilogram>

    companion object {
        /**
         * Creates a measure of **coulombs per kilogram** (C/kg).
         *
         * This derived unit expresses **specific charge** —
         * the amount of electric charge per unit mass.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Coulomb] (electric charge) with the specified [prefix]
         *  - divided by a [Kilogram] (mass)
         *
         * @param prefix Metric prefix to apply to the coulomb unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [CoulombPerKilogram] representing C/kg.
         */
        @Suppress("FunctionNaming")
        internal fun CoulombPerKilogram(prefix: Metric = Metric.BASE): CoulombPerKilogram =
            Quotient(Coulomb(prefix), Kilogram())
    }
}
