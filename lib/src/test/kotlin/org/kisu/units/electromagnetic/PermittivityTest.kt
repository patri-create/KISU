package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.faradsPerMetre
import org.kisu.units.electromagnetic.Permittivity.Companion.FaradPerMetre

class PermittivityTest : StringSpec({
    "creates a Permittivity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().faradsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe FaradPerMetre(magnitude.builder().metric)
                symbol shouldBe FaradPerMetre().toString()
            }
        }
    }

    "creates a base Permittivity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.faradsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe FaradPerMetre()
                symbol shouldBe FaradPerMetre().toString()
            }
        }
    }
})
