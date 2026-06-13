package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import org.kisu.units.scales.ExponentialScale
import org.kisu.units.scales.Scale
import java.math.BigDecimal

/**
 * Represents the physical quantity of **energy**, measured in [Joule].
 *
 * Energy quantifies the capacity to perform work, transfer heat, or produce change. In
 * mechanics it appears as kinetic or potential energy; in thermodynamics as heat; in
 * electricity as energy delivered or stored.
 *
 * Typical examples include the energy consumed by an appliance, the work done lifting
 * a load, the heat delivered to a sample, or the stored energy in a battery.
 *
 * The canonical SI unit is the [Joule] (`J`), commonly scaled as `mJ`, `kJ`, or `MJ`.
 */
class Energy internal constructor(magnitude: BigDecimal, expression: Joule) :
    Measure<Joule, Energy>(magnitude, expression, ::Energy) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Joule(prefix))
}

/**
 * Represents the unit **joule** (`J`), used to express [Energy].
 *
 * A joule quantifies energy, work, or heat. One joule is the energy transferred when
 * a force of one [Newton] acts through a distance of one metre.
 *
 * It is used for mechanical work, thermal energy, electrical energy delivered over
 * time, and many everyday quantities such as the energy content of food or the energy
 * stored in a battery cell.
 *
 * In unit form, `J = N·m = m²·kg·s⁻²`.
 *
 * @see Energy
 * @see Newton
 * @see Watt
 */
class Joule private constructor(
    scale: Scale<Metric> = ExponentialScale(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Joule>(scale, prefix, unit, ::Joule) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for joule: "J". */
        internal val UNIT = Unit("J", 1)
    }
}
