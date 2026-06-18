package org.kisu.units.special

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit

/**
 * Represents the physical quantity of **capacitance**, measured in [Farad].
 *
 * Capacitance quantifies a system's ability to store electric charge for a given
 * potential difference. It describes how much charge accumulates when a voltage is
 * applied.
 *
 * This quantity is fundamental in electronics, where it appears in capacitors used for
 * filtering, timing, smoothing power supplies, signal coupling, and energy storage.
 *
 * The canonical SI unit is the [Farad] (`F`), although practical circuits usually use
 * `mF`, `µF`, `nF`, or `pF`.
 */
class Capacitance internal constructor(magnitude: Magnitude, expression: Farad) :
    Measure<Farad, Capacitance>(magnitude, expression, ::Capacitance) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, Farad(prefix))

    /**
     * Returns the elastance associated with this capacitance by inverting its canonical magnitude.
     */
    val elastance: Elastance
        get() = Elastance(canonical.component1().inverted)
}

/**
 * Represents the unit **farad** (`F`), used to express [Capacitance].
 *
 * A farad quantifies how much electric charge a system can store for a given electric
 * potential difference. One farad means one coulomb of charge stored per volt.
 *
 * In practice, the farad is a large unit, so electronics often use microfarads,
 * nanofarads, or picofarads for capacitors in filters, timing circuits, and power
 * supplies.
 *
 * In unit form, `F = C/V = m⁻²·kg⁻¹·s⁴·A²`.
 *
 * @see Capacitance
 * @see Coulomb
 * @see Volt
 */
class Farad private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Farad>(algebra, prefix, unit, ::Farad) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for farad: "F". */
        internal val UNIT = Unit("F", 1)
    }
}
