package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kilogram
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Joule
import java.math.BigDecimal

/**
 * Measure of specific energy expressed in [JoulePerKilogram].
 *
 * Specific energy quantifies the amount of energy contained or transferred per unit mass,
 * commonly used to describe energy content of fuels or energy absorption in materials.
 *
 * Common applications include:
 * - Thermodynamics (energy per unit mass of fluids or solids)
 * - Aerospace engineering (specific kinetic or potential energy)
 * - Material science (energy absorbed per unit mass)
 *
 * @property magnitude Numerical value of the specific energy.
 * @property expression Unit of the specific energy, here [JoulePerKilogram].
 *
 * @see JoulePerKilogram
 */
class SpecificEnergy(
    magnitude: BigDecimal,
    expression: JoulePerKilogram
) : Measure<SpecificEnergy.JoulePerKilogram, SpecificEnergy>(magnitude, expression, ::SpecificEnergy) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
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
         * Creates a measure of **joules per kilogram** (J/kg).
         *
         * This derived unit expresses **specific energy** —
         * how much energy is associated with a unit mass.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Joule] (energy) with the specified [prefix]
         *  - divided by a [Kilogram] (mass)
         *
         * @param prefix Metric prefix to apply to the joule unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [JoulePerKilogram] representing J/kg.
         */
        @Suppress("FunctionNaming")
        internal fun JoulePerKilogram(prefix: Metric = Metric.BASE): JoulePerKilogram =
            Quotient(Joule(prefix), Kilogram())
    }
}
