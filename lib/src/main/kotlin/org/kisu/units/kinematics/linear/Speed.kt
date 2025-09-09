package org.kisu.units.kinematics.linear

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.base.Second
import org.kisu.units.representation.Quotient
import java.math.BigDecimal

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
) : Measure<Speed.MetrePerSecond, Speed>(magnitude, expression, ::Speed) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Quotient(Metre(prefix), Second()))

    /**
     * Represents the SI unit **metre per second (m/s)**.
     *
     * This unit is used to measure **linear velocity**,
     * i.e., the rate of change of position with respect to time.
     * It is defined as the [Quotient] of [Metre] (length) divided by [Second] (time).
     *
     * Example usages include:
     * - Measuring the speed of vehicles, projectiles, or moving objects
     * - Describing fluid flow rates in physics and engineering
     * - Analysing motion in mechanics and kinematics
     *
     * @see Speed
     */
    typealias MetrePerSecond = Quotient<Metre, Second>
}
