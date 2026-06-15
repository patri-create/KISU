package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **inductance**, measured in [Henry].
 *
 * Inductance quantifies how strongly a conductor or circuit resists changes in current
 * by inducing a voltage. It reflects the magnetic coupling between current and magnetic
 * flux.
 *
 * This quantity is central in coils, transformers, filters, switching circuits, and
 * electromagnetic energy storage.
 *
 * The canonical SI unit is the [Henry] (`H`), while `mH` and `µH` are common in
 * practical electronics.
 */
class Inductance internal constructor(magnitude: BigDecimal, expression: Henry) :
    Measure<Henry, Inductance>(magnitude, expression, ::Inductance) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Henry(prefix))
}

/**
 * Represents the unit **henry** (`H`), used to express [Inductance].
 *
 * A henry quantifies how strongly an electrical conductor or coil opposes a change in
 * current by inducing a voltage. One henry corresponds to one volt induced when the
 * current changes at one ampere per second.
 *
 * This unit is used for inductors, transformers, electromagnets, and transient
 * analysis in electrical circuits.
 *
 * In unit form, `H = Wb/A = V·s/A = m²·kg·s⁻²·A⁻²`.
 *
 * @see Inductance
 * @see Volt
 * @see Weber
 */
class Henry private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Henry>(algebra, prefix, unit, ::Henry) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for henry: "H". */
        internal val UNIT = Unit("H", 1)
    }
}
