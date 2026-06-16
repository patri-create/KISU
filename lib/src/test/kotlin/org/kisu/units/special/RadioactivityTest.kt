package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.magnitude
import org.kisu.units.builders.becquerels

class RadioactivityTest : StringSpec({
    "creates a Radioactivity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().becquerels.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Becquerel(magnitude.builder().metric)
                symbol shouldBe Becquerel.UNIT.toString()
            }
        }
    }

    "creates a base Radioactivity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.becquerels.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Becquerel()
                symbol shouldBe Becquerel.UNIT.toString()
            }
        }
    }
})
