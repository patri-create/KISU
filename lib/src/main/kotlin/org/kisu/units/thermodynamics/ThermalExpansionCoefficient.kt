package org.kisu.units.thermodynamics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Kelvin
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **thermal expansion coefficient**.
 *
 * The thermal expansion coefficient quantifies the **fractional change in a material’s
 * dimensions (length, area, or volume) per unit change in temperature**.
 * It describes how much a material expands or contracts when its temperature changes.
 * Its SI unit is the **reciprocal kelvin (1/K)**, represented here by [ReciprocalKelvin].
 *
 * Example usages include:
 * - Linear expansion of solids (e.g., steel ≈ 12 × 10⁻⁶ 1/K)
 * - Volume expansion of liquids and gases
 * - Engineering applications involving thermal stress or dimensional tolerances
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision.
 * Instances of [ThermalExpansionCoefficient] are immutable, and the [expression] parameter ties
 * the measurement to its unit representation ([ReciprocalKelvin]).
 *
 * @property magnitude The numeric value of the thermal expansion coefficient.
 * @property expression The unit expression of the thermal expansion coefficient, always [ReciprocalKelvin].
 */
class ThermalExpansionCoefficient(
    magnitude: BigDecimal,
    expression: ReciprocalKelvin
) : Measure<ReciprocalKelvin, ThermalExpansionCoefficient>(magnitude, expression, ::ThermalExpansionCoefficient)

/**
 * Represents the **reciprocal of temperature** in the SI system.
 *
 * Reciprocal kelvin (1/K) is used in several thermodynamic contexts, such as
 * the **thermal expansion coefficient** and other relations where temperature
 * appears in the denominator.
 * This class models the unit `K⁻¹` by inverting the [Kelvin] base unit.
 *
 * The class is implemented as a [Scalar], parameterized with the [Metric] system
 * of prefixes to allow scaling (e.g., mK⁻¹, µK⁻¹).
 * The primary constructor is private, and instances are typically created through
 * the public constructor using a [Metric] prefix, defaulting to the base unit.
 *
 * Example usages include:
 * - Expressing a thermal expansion coefficient (e.g., 12 × 10⁻⁶ 1/K for steel)
 * - Other physics and engineering calculations where reciprocal temperature is needed
 *
 * @constructor Creates a [ReciprocalKelvin] with a given [Metric] prefix, defaulting to [Metric.BASE].
 * @property prefix The metric prefix applied to the reciprocal kelvin.
 * @property overflow Scaling factor for large or reduced unit expressions (internal use).
 * @property unit The underlying [Unit], fixed to [Kelvin.UNIT.inverted].
 */
class ReciprocalKelvin private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit,
) : Scalar<Metric, ReciprocalKelvin>(prefix, overflow, unit, ::ReciprocalKelvin) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The SI unit for reciprocal kelvin (K⁻¹), defined as the inverse of [Kelvin.UNIT]. */
        internal val UNIT = Kelvin.UNIT.inverted
    }
}
