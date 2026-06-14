package org.kisu.test.fakes

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import org.kisu.units.scales.ExponentialScale
import org.kisu.units.scales.Scale
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
    scale: Scale<Metric> = ExponentialScale(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, TestUnit>(scale, prefix, unit, ::TestUnit) {

    constructor(prefix: Metric) : this(ExponentialScale(), prefix, UNIT)

    constructor(prefix: Metric, unit: Unit) : this(ExponentialScale(), prefix, unit)

    companion object {
        /** The canonical SI symbol for electric current: "A". */
        internal val UNIT = Unit("ts", 1)
    }
}
