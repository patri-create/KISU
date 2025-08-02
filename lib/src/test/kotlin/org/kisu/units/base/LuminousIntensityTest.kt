package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.positiveLong
import io.kotest.property.checkAll
import org.kisu.bigDecimal
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.candelas
import org.kisu.units.representation.Scalar

class LuminousIntensityTest : StringSpec({
    "creates LuminousIntensity" {
        checkAll(Arb.positiveLong(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().candelas.should { (amount, expression, symbol) ->
                amount shouldBe magnitude.bigDecimal
                expression shouldBe Scalar(magnitude.builder().metric, LuminousIntensity.UNIT)
                symbol shouldBe LuminousIntensity.UNIT.toString()
            }
        }
    }

    "creates a base LuminousIntensity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.candelas.should { (amount, expression, symbol) ->
                amount shouldBe magnitude.bigDecimal
                expression shouldBe Scalar(Metric.BASE, LuminousIntensity.UNIT)
                symbol shouldBe LuminousIntensity.UNIT.toString()
            }
        }
    }
})
