package org.kisu.units.kinematics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.representation.Quotient
import org.kisu.units.special.CubicMetre
import java.math.BigDecimal

typealias CubicMetrePerSecond = Quotient<CubicMetre, Second>

/**
 * Represents the physical quantity of **volumetric flow**, the rate of volume change over time.
 *
 * Volumetric flow quantifies how much volume of a substance passes through a given point or area per unit time.
 * Its SI unit is the **cubic metre per second (m³/s)**, represented here by [CubicMetrePerSecond].
 *
 * Typical applications include:
 * - Fluid dynamics and pipe flow measurement
 * - HVAC and water distribution systems
 * - Chemical and process engineering
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision. Instances of [VolumetricFlow] are
 * immutable, and the [expression] parameter ties the measurement to its unit representation ([CubicMetrePerSecond]).
 *
 * @property magnitude The numeric value of the volumetric flow.
 * @property expression The unit expression of the volumetric flow, always [CubicMetrePerSecond].
 */
class VolumetricFlow internal constructor(
    magnitude: BigDecimal,
    expression: CubicMetrePerSecond
) : Measure<CubicMetrePerSecond, VolumetricFlow>(magnitude, expression, ::VolumetricFlow) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Quotient(CubicMetre(prefix), Second()))
}
