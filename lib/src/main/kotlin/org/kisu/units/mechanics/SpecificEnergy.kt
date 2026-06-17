package org.kisu.units.mechanics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Joule

/**
 * Represents the physical quantity of **specific energy**, measured in
 * [JoulePerKilogram].
 *
 * Specific energy quantifies energy per unit mass. It is useful when comparing fuels,
 * flows, or materials independently of total sample size.
 *
 * Typical examples include fuel energy content, gravitational or kinetic energy per unit
 * mass, and absorbed energy in irradiated matter.
 *
 * The associated unit representation is [JoulePerKilogram] (`J/kg`).
 */
class SpecificEnergy(
    magnitude: Magnitude,
    expression: JoulePerKilogram
) : Measure<SpecificEnergy.JoulePerKilogram, SpecificEnergy>(magnitude, expression, ::SpecificEnergy) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, JoulePerKilogram(prefix))

    /**
     * Unit of [SpecificEnergy].
     *
     * Represents the unit of **specific energy**, i.e., the physical quantity measuring
     * energy per unit mass.
     *
     * Symbol: `J/kg`
     * SI: `m²·s⁻²`
     *
     * @see SpecificEnergy
     */
    typealias JoulePerKilogram = Quotient<Joule, Kilogram>

    companion object {
        /**
         * Creates a [JoulePerKilogram] expression for **joule per kilogram** (`J/kg`).
         *
         * @param prefix Metric prefix applied to the joule unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [JoulePerKilogram] expression for `J/kg`.
         */
        @Suppress("FunctionNaming")
        internal fun JoulePerKilogram(prefix: Metric = Metric.BASE): JoulePerKilogram =
            Quotient(Joule(prefix), Kilogram())
    }
}
