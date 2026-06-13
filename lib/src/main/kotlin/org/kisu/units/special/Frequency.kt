package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import org.kisu.units.scales.ExponentialScale
import org.kisu.units.scales.Scale
import java.math.BigDecimal

/**
 * Represents the physical quantity of **frequency**, measured in [Hertz].
 *
 * Frequency quantifies how often a periodic event repeats in time. It is fundamental
 * for oscillations, waves, rotating systems, and repeating signals.
 *
 * Typical examples include the pitch of a sound, the carrier frequency of a radio
 * signal, the clock rate of an electronic system, or the rotational rate of a machine.
 *
 * The canonical SI unit is the [Hertz] (`Hz`), often scaled as `kHz`, `MHz`, or `GHz`.
 */
class Frequency internal constructor(magnitude: BigDecimal, expression: Hertz) :
    Measure<Hertz, Frequency>(magnitude, expression, ::Frequency) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Hertz(prefix))
}

/**
 * Represents the unit **hertz** (`Hz`), used to express [Frequency].
 *
 * A hertz quantifies how often a repeating event occurs. One hertz means one cycle,
 * oscillation, or event per second.
 *
 * This unit is used for sound pitch, electrical alternating current, processor clocks,
 * rotating systems, and any periodic motion or signal.
 *
 * In unit form, `Hz = s⁻¹`.
 *
 * @see Frequency
 * @see Becquerel
 */
class Hertz private constructor(
    scale: Scale<Metric> = ExponentialScale(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Hertz>(scale, prefix, unit, ::Hertz) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for hertz: "Hz". */
        internal val UNIT = Unit("Hz", 1)
    }
}
