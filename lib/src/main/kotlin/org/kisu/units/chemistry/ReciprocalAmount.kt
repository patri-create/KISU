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

class ReciprocalAmount internal constructor(magnitude: Magnitude, expression: ReciprocalMole) :
    Measure<ReciprocalMole, ReciprocalAmount>(magnitude, expression, ::ReciprocalAmount) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, ReciprocalMole(prefix))

    val amount: Amount
        get() = Amount(canonical.component1().inverted)
}

class ReciprocalMole private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit,
) : Scalar<Metric, ReciprocalMole>(algebra, prefix, unit, ::ReciprocalMole) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The SI symbol for amount of substance: "mol⁻¹". */
        internal val UNIT = Mole.UNIT.inverted
    }
}
