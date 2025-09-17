package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.henriesPerMetre
import org.kisu.units.electromagnetic.MagneticPermittivity.Companion.HenryPerMetre

class MagneticPermittivityTest : StringSpec({
    "creates a MagneticPermittivity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().henriesPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe HenryPerMetre(magnitude.builder().metric)
                symbol shouldBe HenryPerMetre().toString()
            }
        }
    }

    "creates a base MagneticPermittivity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.henriesPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe HenryPerMetre()
                symbol shouldBe HenryPerMetre().toString()
            }
        }
    }
})
