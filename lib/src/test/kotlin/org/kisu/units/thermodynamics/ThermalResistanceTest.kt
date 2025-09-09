package org.kisu.units.thermodynamics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.kelvinsPerWatt
import org.kisu.units.thermodynamics.ThermalResistance.Companion.KelvinPerWatt

class ThermalResistanceTest : StringSpec({
    "creates a ThermalResistance" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().kelvinsPerWatt.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KelvinPerWatt(magnitude.builder().metric)
                symbol shouldBe KelvinPerWatt().toString()
            }
        }
    }

    "creates a base ThermalResistance" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.kelvinsPerWatt.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KelvinPerWatt()
                symbol shouldBe KelvinPerWatt().toString()
            }
        }
    }
})
