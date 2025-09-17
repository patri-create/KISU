package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.siemensPerMetre
import org.kisu.units.electromagnetic.ElectricConductivity.Companion.SiemensPerMetre

class ElectricConductivityTest : StringSpec({
    "creates an ElectricConductivity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().siemensPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe SiemensPerMetre(magnitude.builder().metric)
                symbol shouldBe SiemensPerMetre().toString()
            }
        }
    }

    "creates a base ElectricConductivity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.siemensPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe SiemensPerMetre()
                symbol shouldBe SiemensPerMetre().toString()
            }
        }
    }
})
