package org.kisu.test.fakes

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit

class TestMeasure(
    magnitude: Magnitude,
    expression: TestUnit,
) : Measure<TestUnit, TestMeasure>(magnitude, expression, ::TestMeasure) {

    constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, TestUnit(prefix))

    val magnitude: Magnitude = magnitude
}

class TestUnit constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, TestUnit>(algebra, prefix, unit, ::TestUnit) {

    constructor(prefix: Metric) : this(ExponentialAlgebra(), prefix, UNIT)

    constructor(prefix: Metric, unit: Unit) : this(ExponentialAlgebra(), prefix, unit)

    companion object {
        /** The canonical SI symbol for electric current: "A". */
        internal val UNIT = Unit("ts", 1)
    }
}
