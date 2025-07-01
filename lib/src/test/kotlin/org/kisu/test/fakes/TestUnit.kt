package org.kisu.test.fakes

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import java.math.BigDecimal

class TestUnit(
    magnitude: BigDecimal,
    prefix: Metric = Metric.BASE,
) : Measure<Metric, TestUnit>(magnitude, prefix, SYMBOL, ::TestUnit) {

    constructor(magnitude: Double, prefix: Metric = Metric.BASE) : this(BigDecimal.valueOf(magnitude), prefix)

    val magnitude: BigDecimal = magnitude

    companion object {
        const val SYMBOL = "ts"
    }
}
