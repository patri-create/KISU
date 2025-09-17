package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.amperesRadian
import org.kisu.units.electromagnetic.MagnetomotiveForce.Companion.AmpereRadian

class MagnetomotiveForceTest : StringSpec({
    "creates a MagnetomotiveForce" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().amperesRadian.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe AmpereRadian(magnitude.builder().metric)
                symbol shouldBe AmpereRadian().toString()
            }
        }
    }

    "creates a base MagnetomotiveForce" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.amperesRadian.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe AmpereRadian()
                symbol shouldBe AmpereRadian().toString()
            }
        }
    }
})
