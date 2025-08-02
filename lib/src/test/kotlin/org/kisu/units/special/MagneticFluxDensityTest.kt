package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.teslas
import org.kisu.units.representation.Scalar

class MagneticFluxDensityTest : StringSpec({
    "creates a MagneticFluxDensity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().teslas.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(magnitude.builder().metric, MagneticFluxDensity.SYMBOL)
                symbol shouldBe MagneticFluxDensity.SYMBOL
            }
        }
    }

    "creates a base MagneticFluxDensity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.teslas.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(Metric.BASE, MagneticFluxDensity.SYMBOL)
                symbol shouldBe MagneticFluxDensity.SYMBOL
            }
        }
    }
})
