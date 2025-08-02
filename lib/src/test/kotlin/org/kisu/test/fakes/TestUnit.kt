package org.kisu.test.fakes

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

class TestUnit(
    magnitude: BigDecimal,
    expression: Scalar<Metric>,
) : Measure<Scalar<Metric>, TestUnit>(magnitude, expression, ::TestUnit) {

    constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Scalar(prefix, UNIT))

    val magnitude: BigDecimal = magnitude

    companion object {
        val UNIT = Unit("ts", 1)
    }
}
