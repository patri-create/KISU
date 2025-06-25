package org.kisu.test.fakes

import org.kisu.prefixes.Metric
import org.kisu.test.fakes.TestUnit.Companion.SYMBOL
import org.kisu.units.Measure
import java.math.BigDecimal

class TestUnit(magnitude: BigDecimal, prefix: Metric) : Measure<Metric, TestUnit>(magnitude, prefix, SYMBOL) {

    constructor(magnitude: Double, prefix: Metric): this(BigDecimal.valueOf(magnitude), prefix)

    override fun create(
        magnitude: BigDecimal,
        prefix: Metric,
    ): Measure<Metric, TestUnit> = TestUnit(magnitude, prefix)

    companion object {
        const val SYMBOL = "ts"
    }
}
