package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import org.kisu.units.scales.ExponentialScale
import org.kisu.units.scales.Scale
import java.math.BigDecimal

/**
 * Represents the physical quantity of **magnetic flux**, measured in [Weber].
 *
 * Magnetic flux quantifies how much magnetic field passes through a surface. It is the
 * quantity that appears naturally in electromagnetic induction: changing flux produces
 * induced voltage.
 *
 * Typical examples include the flux linked by a transformer winding, the flux through
 * a loop in a magnetic field, or the working flux inside a magnetic core.
 *
 * The canonical SI unit is the [Weber] (`Wb`), often scaled to `mWb` or `µWb`.
 */
class MagneticFlux internal constructor(magnitude: BigDecimal, expression: Weber) :
    Measure<Weber, MagneticFlux>(magnitude, expression, ::MagneticFlux) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Weber(prefix))
}

/**
 * Represents the unit **weber** (`Wb`), used to express [MagneticFlux].
 *
 * A weber quantifies the total magnetic field passing through a surface or circuit. It
 * is especially useful when discussing induction, where changing magnetic flux induces
 * an electromotive force.
 *
 * This unit appears in transformers, electric machines, magnetic cores, and field
 * calculations involving loops, coils, and enclosed areas.
 *
 * In unit form, `Wb = V·s = T·m² = m²·kg·s⁻²·A⁻¹`.
 *
 * @see MagneticFlux
 * @see Tesla
 * @see Volt
 */
class Weber private constructor(
    scale: Scale<Metric> = ExponentialScale(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Weber>(scale, prefix, unit, ::Weber) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for weber: "Wb". */
        internal val UNIT = Unit("Wb", 1)
    }
}
