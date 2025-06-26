package org.kisu.test.fakes

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import java.math.BigDecimal

class TestUnit(
    magnitude: BigDecimal,
    prefix: Metric = Metric.BASE,
) : Measure<Metric, TestUnit>(magnitude, prefix, SYMBOL) {
    constructor(magnitude: Double, prefix: Metric = Metric.BASE) : this(BigDecimal.valueOf(magnitude), prefix)

    val magnitude: BigDecimal = magnitude

    override fun invoke(
        magnitude: BigDecimal,
        prefix: Metric,
    ): TestUnit = TestUnit(magnitude, prefix)

    companion object {
        const val SYMBOL = "ts"
    }
}
