package org.kisu.units.thermodynamics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.wattsPerMetreKelvin
import org.kisu.units.thermodynamics.ThermalConductivity.Companion.WattPerMetreKelvin

class ThermalConductivityTest : StringSpec({
    "creates a ThermalConductivity" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().wattsPerMetreKelvin.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerMetreKelvin(magnitude.builder().metric)
                symbol shouldBe WattPerMetreKelvin().toString()
            }
        }
    }

    "creates a base ThermalConductivity" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.wattsPerMetreKelvin.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WattPerMetreKelvin()
                symbol shouldBe WattPerMetreKelvin().toString()
            }
        }
    }
})
