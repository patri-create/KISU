package org.kisu.units.special

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit

class Elastance internal constructor(magnitude: Magnitude, expression: InverseFarad) :
    Measure<InverseFarad, Elastance>(magnitude, expression, ::Elastance) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, InverseFarad(prefix))

    val capacitance: Capacitance
        get() = Capacitance(canonical.component1().inverted)
}

class InverseFarad constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, InverseFarad>(algebra, prefix, unit, ::InverseFarad) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for farad: "F⁻¹". */
        internal val UNIT = Farad.UNIT.inverted
    }
}
