package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.prefixes.Metric
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.lumens
import org.kisu.units.representation.Scalar

class LuminousFluxTest : StringSpec({
    "creates a LuminousFlux" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().lumens.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(magnitude.builder().metric, LuminousFlux.UNIT)
                symbol shouldBe LuminousFlux.UNIT.toString()
            }
        }
    }

    "creates a base LuminousFlux" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.lumens.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Scalar(Metric.BASE, LuminousFlux.UNIT)
                symbol shouldBe LuminousFlux.UNIT.toString()
            }
        }
    }
})
