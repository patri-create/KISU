package org.kisu.test.fakes

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.Scalar
import java.math.BigDecimal

class TestUnit(
    magnitude: BigDecimal,
    expression: Scalar<Metric>,
) : Measure<Scalar<Metric>, TestUnit>(magnitude, expression, ::TestUnit) {

    constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Scalar(prefix, SYMBOL))

    val magnitude: BigDecimal = magnitude

    companion object {
        const val SYMBOL = "ts"
    }
}
