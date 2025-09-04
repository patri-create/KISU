package org.kisu.units.chemistry

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.cubicMetrePerMoleSecond

class CatalyticEfficiencyTest : StringSpec({
    "creates a CatalyticEfficiency" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().cubicMetrePerMoleSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CubicMetrePerMoleSecond(magnitude.builder().metric)
                symbol shouldBe CubicMetrePerMoleSecond().toString()
            }
        }
    }

    "creates a base CatalyticEfficiency" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.cubicMetrePerMoleSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CubicMetrePerMoleSecond()
                symbol shouldBe CubicMetrePerMoleSecond().toString()
            }
        }
    }
})
