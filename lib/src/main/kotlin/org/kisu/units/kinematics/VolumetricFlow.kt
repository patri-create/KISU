package org.kisu.units.kinematics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.representation.Quotient
import org.kisu.units.special.CubicMetre
import java.math.BigDecimal

/**
 * Represents the physical quantity of **volumetric flow rate**, measured in
 * [CubicMetrePerSecond].
 *
 * Volumetric flow rate quantifies how much volume passes through a section or boundary
 * per unit time. It is one of the standard ways to describe fluid transport in pipes,
 * ducts, pumps, and open channels.
 *
 * Typical examples include the output of a pump, airflow through HVAC equipment, or
 * water delivery in a distribution network.
 *
 * The associated unit representation is [CubicMetrePerSecond] (`m³/s`).
 */
class VolumetricFlow internal constructor(
    magnitude: BigDecimal,
    expression: CubicMetrePerSecond
) : Measure<VolumetricFlow.CubicMetrePerSecond, VolumetricFlow>(magnitude, expression, ::VolumetricFlow) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, CubicMetrePerSecond(prefix))

    /**
     * Represents the SI unit **cubic metre per second (m³/s)**.
     *
     * This unit is used to measure **volumetric flow rate**,
     * i.e., the volume of a fluid passing through a given surface per unit time.
     * It is defined as the [Quotient] of [CubicMetre] (volume) divided by [Second] (time).
     *
     * Example usages include:
     * - Measuring river discharge or water flow in hydrology
     * - Specifying pump or ventilation system capacities
     * - Quantifying fluid transport in engineering processes
     *
     * @see VolumetricFlow
     */
    typealias CubicMetrePerSecond = Quotient<CubicMetre, Second>

    companion object {
        /**
         * Creates a [CubicMetrePerSecond] expression for **cubic metre per second** (`m³/s`).
         *
         * @param prefix Metric prefix applied to the cubic metre unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [CubicMetrePerSecond] expression for `m³/s`.
         */
        @Suppress("FunctionNaming")
        internal fun CubicMetrePerSecond(prefix: Metric = Metric.BASE): CubicMetrePerSecond =
            Quotient(CubicMetre(prefix), Second())
    }
}
