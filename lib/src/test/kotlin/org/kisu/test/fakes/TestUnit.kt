package org.kisu.test.fakes

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import java.math.BigDecimal

object TestUnit {

    const val SYMBOL = "ts"

    operator fun invoke(magnitude: Double, prefix: Metric): Measure<Metric> = Measure(magnitude, prefix, SYMBOL)

    operator fun invoke(magnitude: BigDecimal, prefix: Metric): Measure<Metric> = Measure(magnitude, prefix, SYMBOL)
}
