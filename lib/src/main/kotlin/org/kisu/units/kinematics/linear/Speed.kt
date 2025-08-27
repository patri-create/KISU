package org.kisu.units.kinematics.linear

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.base.Second
import org.kisu.units.representation.Quotient
import java.math.BigDecimal

typealias MetrePerSecond = Quotient<Metre, Second>

/**
 * Represents the physical quantity of **speed** (velocity magnitude).
 *
 * Speed quantifies the **rate of change of position** over time.
 * Its SI unit is **metre per second (m/s)**, represented here by [MetrePerSecond].
 *
 * Typical applications:
 * - Vehicle speed
 * - Object motion analysis
 * - Any system where displacement changes over time
 *
 * The magnitude is stored as a [BigDecimal] for high precision. Instances are immutable.
 */
class Speed(
    magnitude: BigDecimal,
    expression: MetrePerSecond
) : Measure<MetrePerSecond, Speed>(magnitude, expression, ::Speed) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Quotient(Metre(prefix), Second()))
}
