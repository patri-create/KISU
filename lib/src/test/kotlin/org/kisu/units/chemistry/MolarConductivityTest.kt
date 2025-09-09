package org.kisu.units.chemistry

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.siemensSquareMetrePerMole
import org.kisu.units.chemistry.MolarConductivity.Companion.SiemensSquareMetrePerMole

class MolarConductivityTest : StringSpec({
    "creates a MolarConductivity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().siemensSquareMetrePerMole.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe SiemensSquareMetrePerMole(magnitude.builder().metric)
                symbol shouldBe SiemensSquareMetrePerMole().toString()
            }
        }
    }

    "creates a base MolarConductivity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.siemensSquareMetrePerMole.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe SiemensSquareMetrePerMole()
                symbol shouldBe SiemensSquareMetrePerMole().toString()
            }
        }
    }
})
