package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import org.kisu.units.scales.ExponentialScale
import org.kisu.units.scales.Scale
import java.math.BigDecimal

/**
 * Represents the physical quantity of **magnetic flux density**, measured in [Tesla].
 *
 * Magnetic flux density quantifies how concentrated a magnetic field is over an area.
 * It is the quantity commonly denoted by `B` in electromagnetism and is especially
 * useful when describing field strength inside magnets, coils, and magnetic materials.
 *
 * Typical examples include Earth's magnetic field, the field inside an MRI scanner, or
 * the flux density in a motor or transformer core.
 *
 * The canonical SI unit is the [Tesla] (`T`), with `mT` and `µT` being common in
 * practice.
 */
class MagneticFluxDensity internal constructor(magnitude: BigDecimal, expression: Tesla) :
    Measure<Tesla, MagneticFluxDensity>(magnitude, expression, ::MagneticFluxDensity) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Tesla(prefix))
}

/**
 * Represents the unit **tesla** (`T`), used to express [MagneticFluxDensity].
 *
 * A tesla quantifies magnetic flux density, describing how concentrated a magnetic
 * field is over an area. One tesla means one [Weber] of magnetic flux through one
 * [SquareMetre].
 *
 * This unit is used for MRI systems, permanent magnets, electric motors, particle
 * beam systems, and general electromagnetic field analysis.
 *
 * In unit form, `T = Wb/m² = m⁻²·kg·s⁻²·A⁻¹`.
 *
 * @see MagneticFluxDensity
 * @see Weber
 * @see SquareMetre
 */
class Tesla private constructor(
    scale: Scale<Metric> = ExponentialScale(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Tesla>(scale, prefix, unit, ::Tesla) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for tesla: "T". */
        internal val UNIT = Unit("T", 1)
    }
}
