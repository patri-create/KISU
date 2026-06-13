package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import org.kisu.units.scales.ExponentialScale
import org.kisu.units.scales.Scale
import java.math.BigDecimal

/**
 * Represents the physical quantity of **electrical resistance**, measured in [Ohm].
 *
 * Resistance quantifies how strongly a component or material opposes the flow of
 * electric current. It is one of the core descriptive quantities of electric circuits
 * and conductive media.
 *
 * Typical examples include resistor values, wire resistance, winding resistance, and
 * material measurements used to characterize electronic components.
 *
 * The canonical SI unit is the [Ohm] (`Ω`), commonly scaled as `mΩ`, `kΩ`, or `MΩ`.
 */
class Resistance internal constructor(magnitude: BigDecimal, expression: Ohm) :
    Measure<Ohm, Resistance>(magnitude, expression, ::Resistance) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Ohm(prefix))
}

/**
 * Represents the unit **ohm** (`Ω`), used to express [Resistance].
 *
 * An ohm quantifies electrical resistance, the opposition a component or material
 * presents to the flow of current. One ohm means that a potential difference of one
 * volt produces a current of one ampere.
 *
 * This unit is used for resistors, heaters, wiring, sensors, and material property
 * measurements in electronics and electrical engineering.
 *
 * In unit form, `Ω = V/A = m²·kg·s⁻³·A⁻²`.
 *
 * @see Resistance
 * @see Volt
 * @see Siemens
 */
class Ohm private constructor(
    scale: Scale<Metric> = ExponentialScale(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Ohm>(scale, prefix, unit, ::Ohm) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for ohm: "Ω". */
        internal val UNIT = Unit("Ω", 1)
    }
}
