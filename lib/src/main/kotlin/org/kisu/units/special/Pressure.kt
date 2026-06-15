package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **pressure**, measured in [Pascal].
 *
 * Pressure quantifies force distributed over an area. In solids the same unit also
 * appears for normal stress and many elastic moduli, which is why this quantity sits
 * at the boundary between fluid mechanics and solid mechanics.
 *
 * Typical examples include atmospheric pressure, tire pressure, hydraulic pressure, and
 * mechanical stress inside a loaded structural part.
 *
 * The canonical SI unit is the [Pascal] (`Pa`), with `kPa`, `MPa`, and `hPa` being
 * especially common in practice.
 */
class Pressure internal constructor(magnitude: BigDecimal, expression: Pascal) :
    Measure<Pascal, Pressure>(magnitude, expression, ::Pressure) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Pascal(prefix))
}

/**
 * Represents the unit **pascal** (`Pa`), used to express [Pressure].
 *
 * A pascal quantifies pressure or normal stress: force distributed over an area. One
 * pascal means one [Newton] acting uniformly on one [SquareMetre].
 *
 * This unit is used for atmospheric and tire pressure, hydraulic systems, sound
 * pressure, and material stress in engineering.
 *
 * In unit form, `Pa = N/m² = m⁻¹·kg·s⁻²`.
 *
 * @see Pressure
 * @see Newton
 * @see SquareMetre
 */
class Pascal private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Pascal>(algebra, prefix, unit, ::Pascal) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for pascal: "Pa". */
        internal val UNIT = Unit("Pa", 1)
    }
}
