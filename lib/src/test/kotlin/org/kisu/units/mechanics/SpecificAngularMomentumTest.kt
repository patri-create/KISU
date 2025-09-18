package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.newtonsMetreSecondPerKilogram
import org.kisu.units.mechanics.SpecificAngularMomentum.Companion.NetwonMetreSecondPerKilogram

class SpecificAngularMomentumTest : StringSpec({
    "creates a SpecificAngularMomentum" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().newtonsMetreSecondPerKilogram.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe NetwonMetreSecondPerKilogram(magnitude.builder().metric)
                symbol shouldBe NetwonMetreSecondPerKilogram().toString()
            }
        }
    }

    "creates a base SpecificAngularMomentum" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.newtonsMetreSecondPerKilogram.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe NetwonMetreSecondPerKilogram()
                symbol shouldBe NetwonMetreSecondPerKilogram().toString()
            }
        }
    }
})
