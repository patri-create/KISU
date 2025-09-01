package org.kisu.units.chemistry

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.molPerKilogram

class MolalityTest : StringSpec({
    "creates a Molality" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().molPerKilogram.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MolPerKilogram(magnitude.builder().metric)
                symbol shouldBe MolPerKilogram().toString()
            }
        }
    }

    "creates a base Molality" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.molPerKilogram.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe MolPerKilogram()
                symbol shouldBe MolPerKilogram().toString()
            }
        }
    }
})
