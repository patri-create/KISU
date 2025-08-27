package org.kisu.units.electromagnetic

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import org.kisu.units.special.Henry
import java.math.BigDecimal

/**
 * Represents **magnetic reluctance** (ℛ), which quantifies the opposition
 * that a material or magnetic circuit presents to the establishment of a magnetic flux.
 *
 * - **Dimension**: reciprocal of inductance (H⁻¹)
 * - **SI Unit**: reciprocal henry (1/H)
 *
 * Magnetic reluctance is analogous to electrical resistance in circuits:
 *
 *     ℛ = Φ / F
 *
 * where:
 * - ℛ is the magnetic reluctance,
 * - Φ is the magnetic flux (Wb),
 * - F is the magnetomotive force (A·turns).
 *
 * Example usages include:
 * - Modeling magnetic circuits
 * - Designing transformers, inductors, and electromagnets
 * - Analyzing magnetic field distribution in materials
 *
 * @param magnitude numerical value of the measure
 * @param expression unit expression in reciprocal henry (1/H)
 */
class MagneticReluctance(
    magnitude: BigDecimal,
    expression: ReciprocalHenry
) : Measure<ReciprocalHenry, MagneticReluctance>(magnitude, expression, ::MagneticReluctance) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, ReciprocalHenry(prefix))
}

/**
 * Represents the **reciprocal of inductance** (1/H), a scalar quantity used
 * to express **magnetic reluctance**.
 *
 * - **Dimension**: reciprocal of inductance
 * - **SI Unit**: 1/henry (H⁻¹)
 *
 * This class wraps a numeric value together with its unit and supports
 * operations consistent with other [Scalar] types in the system.
 * The unit is defined as the inverse of [Henry.UNIT].
 *
 * Example usages include:
 * - Modeling magnetic reluctance in magnetic circuits
 * - Calculating opposition to magnetic flux
 *
 * @param prefix the metric prefix applied to the unit (default is [Metric.BASE])
 * @param overflow numeric factor to scale the unit
 * @param unit the underlying unit representation (always the reciprocal of henry)
 *
 * @see MagneticReluctance for the physical quantity represented by this unit.
 */
class ReciprocalHenry private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit,
) : Scalar<Metric, ReciprocalHenry>(prefix, overflow, unit, ::ReciprocalHenry) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The SI unit for reciprocal henry, used in magnetic reluctance calculations. */
        internal val UNIT = Henry.UNIT.inverted
    }
}
