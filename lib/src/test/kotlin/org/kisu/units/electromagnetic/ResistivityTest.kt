package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.ohmsMetre
import org.kisu.units.electromagnetic.Resistivity.Companion.OhmMetre

class ResistivityTest : StringSpec({
    "creates a Resistivity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().ohmsMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe OhmMetre(magnitude.builder().metric)
                symbol shouldBe OhmMetre().toString()
            }
        }
    }

    "creates a base Resistivity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.ohmsMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe OhmMetre()
                symbol shouldBe OhmMetre().toString()
            }
        }
    }
})
