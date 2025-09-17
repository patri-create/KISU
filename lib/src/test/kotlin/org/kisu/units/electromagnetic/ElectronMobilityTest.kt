package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.squareMetresPerVoltSecond
import org.kisu.units.electromagnetic.ElectronMobility.Companion.SquareMetrePerVoltSecond

class ElectronMobilityTest : StringSpec({
    "creates an ElectronMobility" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().squareMetresPerVoltSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe SquareMetrePerVoltSecond(magnitude.builder().metric)
                symbol shouldBe SquareMetrePerVoltSecond().toString()
            }
        }
    }

    "creates a base ElectronMobility" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.squareMetresPerVoltSecond.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe SquareMetrePerVoltSecond()
                symbol shouldBe SquareMetrePerVoltSecond().toString()
            }
        }
    }
})
