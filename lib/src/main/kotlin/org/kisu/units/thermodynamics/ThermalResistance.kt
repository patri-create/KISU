@file:Suppress("TooManyFunctions")

package org.kisu.units.thermodynamics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kelvin
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Watt
import org.kisu.units.thermodynamics.ThermalResistance.Companion.KelvinPerWatt

/**
 * Represents the physical quantity of **thermal resistance**, measured in
 * [KelvinPerWatt].
 *
 * Thermal resistance quantifies how strongly a material, component, or interface
 * opposes heat flow. It tells how much temperature difference is required to sustain a
 * given rate of heat transfer.
 *
 * Typical examples include insulation layers, heat sinks, package-to-ambient thermal
 * paths, and building-envelope calculations.
 *
 * The associated SI unit representation is [KelvinPerWatt] (`K/W`).
 */
class ThermalResistance(
    magnitude: Magnitude,
    expression: KelvinPerWatt
) : Measure<ThermalResistance.KelvinPerWatt, ThermalResistance>(magnitude, expression, ::ThermalResistance) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, KelvinPerWatt(prefix))

    /**
     * Represents the SI unit **kelvin per watt (K/W)**.
     *
     * This unit measures **thermal resistance**, i.e., a material’s or system’s
     * opposition to heat flow.
     * It is defined as the [Quotient] of [Kelvin] (temperature difference) divided by [Watt] (heat flow rate).
     *
     * Example usages include:
     * - Evaluating insulation performance in buildings
     * - Thermal management of electronic components
     * - Heat transfer analysis in engineering systems
     *
     * @see ThermalResistance for the physical quantity represented by this unit.
     */
    typealias KelvinPerWatt = Quotient<Kelvin, Watt>

    companion object {
        /**
         * Creates a [KelvinPerWatt] expression for **kelvin per watt** (`K/W`).
         *
         * @param prefix Metric prefix applied to the kelvin unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [KelvinPerWatt] expression for `K/W`.
         */
        @Suppress("FunctionNaming")
        internal fun KelvinPerWatt(prefix: Metric = Metric.BASE): KelvinPerWatt =
            Quotient(Kelvin(prefix), Watt())
    }

    // Dimension-aware arithmetic
    operator fun times(
        other: org.kisu.units.special.Power
    ): org.kisu.units.base.Temperature =
        org.kisu.units.base.Temperature(canonical.component1() * other.canonical.component1())
}
