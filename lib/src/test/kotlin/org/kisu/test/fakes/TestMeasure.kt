package org.kisu.test.fakes

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

class TestMeasure(
    magnitude: BigDecimal,
    expression: TestUnit,
) : Measure<TestUnit, TestMeasure>(magnitude, expression, ::TestMeasure) {

    constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, TestUnit(prefix))

    val magnitude: BigDecimal = magnitude
}

class TestUnit constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, TestUnit>(prefix, overflow, unit, ::TestUnit) {

    constructor(prefix: Metric) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The canonical SI symbol for electric current: "A". */
        internal val UNIT = Unit("ts", 1)
    }
}
