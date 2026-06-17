package org.kisu.units.chemistry

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.magnitude
import org.kisu.units.builders.cubicMetresPerMoleSecond
import org.kisu.units.chemistry.CatalyticEfficiency.Companion.CubicMetrePerMoleSecond

class CatalyticEfficiencyTest : StringSpec({
    "creates a CatalyticEfficiency" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().cubicMetresPerMoleSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CubicMetrePerMoleSecond(magnitude.builder().metric)
                symbol shouldBe CubicMetrePerMoleSecond().toString()
            }
        }
    }

    "creates a base CatalyticEfficiency" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.cubicMetresPerMoleSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CubicMetrePerMoleSecond()
                symbol shouldBe CubicMetrePerMoleSecond().toString()
            }
        }
    }
})
