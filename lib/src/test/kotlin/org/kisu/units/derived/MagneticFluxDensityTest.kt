package org.kisu.units.derived

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.teslas

class MagneticFluxDensityTest : StringSpec({
    "creates a MagneticFluxDensity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().teslas.should { (amount, prefix, symbol) ->
                amount shouldBe magnitude
                prefix shouldBe magnitude.builder().metric
                symbol shouldBe MagneticFluxDensity.SYMBOL
            }
        }
    }

    "creates a base MagneticFluxDensity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.teslas.should { (amount, prefix, symbol) ->
                amount shouldBe magnitude
                prefix shouldBe Metric.BASE
                symbol shouldBe MagneticFluxDensity.SYMBOL
            }
        }
    }
})
