package org.kisu.units.chemistry

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.base.Amount
import org.kisu.units.base.Mole
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit

/**
 * Represents the reciprocal of an amount of substance, measured in [ReciprocalMole].
 *
 * Reciprocal amount is useful when a relationship is expressed per mole without another
 * numerator quantity attached. It is the direct inverse of [Amount].
 *
 * The associated unit representation is [ReciprocalMole] (`mol⁻¹`).
 */
class ReciprocalAmount internal constructor(magnitude: Magnitude, expression: ReciprocalMole) :
    Measure<ReciprocalMole, ReciprocalAmount>(magnitude, expression, ::ReciprocalAmount) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, ReciprocalMole(prefix))

    /**
     * Returns the amount associated with this reciprocal amount by inverting its canonical magnitude.
     */
    val amount: Amount
        get() = Amount(canonical.component1().inverted)
}

/**
 * Represents the unit **reciprocal mole** (`mol⁻¹`), used to express [ReciprocalAmount].
 *
 * A reciprocal mole is the inverse of the mole unit. It appears in quantities where
 * amount of substance is the only denominator.
 *
 * In this library, [ReciprocalMole] is defined as the inverse of `Mole.UNIT`.
 *
 * @see ReciprocalAmount
 * @see Mole
 */
class ReciprocalMole private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit,
) : Scalar<Metric, ReciprocalMole>(algebra, prefix, unit, ::ReciprocalMole) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The SI unit for reciprocal amount of substance (`mol⁻¹`). */
        internal val UNIT = Mole.UNIT.inverted
    }
}
