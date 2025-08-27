package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import org.kisu.units.special.Pascal
import java.math.BigDecimal

/**
 * Measure of compressibility expressed in [ReciprocalPascal].
 *
 * Compressibility quantifies the **ease with which a material or fluid can be compressed**
 * under applied pressure. It is commonly used in fluid mechanics, thermodynamics, and materials science.
 *
 * Common applications include:
 * - Determining bulk modulus of solids and fluids
 * - Analyzing acoustic properties of materials
 * - Evaluating fluid compressibility in engineering systems
 *
 * @property magnitude Numerical value of the compressibility.
 * @property expression Unit of the compressibility, here [ReciprocalPascal].
 *
 * @see ReciprocalPascal
 */
class Compressibility(
    magnitude: BigDecimal,
    expression: ReciprocalPascal
) : Measure<ReciprocalPascal, Compressibility>(magnitude, expression, ::Compressibility) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, ReciprocalPascal(prefix))
}

/**
 * Unit of [Compressibility].
 *
 * Represents the unit of **compressibility**, i.e., the physical quantity measuring
 * the relative change in volume per unit pressure.
 *
 * Symbol: `Pa⁻¹`
 * SI: `m⁻¹·kg⁻¹·s²`
 *
 * @see Compressibility
 */
class ReciprocalPascal private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit,
) : Scalar<Metric, ReciprocalPascal>(prefix, overflow, unit, ::ReciprocalPascal) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        internal val UNIT = Pascal.UNIT.inverted
    }
}
