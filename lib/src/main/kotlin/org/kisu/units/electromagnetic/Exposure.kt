@file:Suppress("TooManyFunctions")

package org.kisu.units.electromagnetic

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.electromagnetic.Exposure.Companion.CoulombPerKilogram
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Coulomb

/**
 * Represents the physical quantity of **radiation exposure**, measured in
 * [CoulombPerKilogram].
 *
 * Exposure quantifies ionizing radiation in terms of the electric charge generated in
 * air per unit mass. It is specific to ionization in air and should be distinguished
 * from absorbed dose or biological effect.
 *
 * This quantity is used in dosimetry and radiation protection.
 *
 * The associated unit representation is [CoulombPerKilogram] (`C/kg`).
 */
class Exposure(
    magnitude: Magnitude,
    expression: CoulombPerKilogram
) : Measure<Exposure.CoulombPerKilogram, Exposure>(magnitude, expression, ::Exposure) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
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
         * Creates a [CoulombPerKilogram] expression for **coulomb per kilogram** (`C/kg`).
         *
         * @param prefix Metric prefix applied to the coulomb unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [CoulombPerKilogram] expression for `C/kg`.
         */
        @Suppress("FunctionNaming")
        internal fun CoulombPerKilogram(prefix: Metric = Metric.BASE): CoulombPerKilogram =
            Quotient(Coulomb(prefix), Kilogram())
    }

    // Dimension-aware arithmetic
    /**
     * Multiplies this [Exposure] by [Mass][org.kisu.units.base.Mass],
     * yielding [ElectricCharge][org.kisu.units.special.ElectricCharge].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Mass
    ): org.kisu.units.special.ElectricCharge =
        org.kisu.units.special.ElectricCharge(canonical.component1() * other.canonical.component1())
}
