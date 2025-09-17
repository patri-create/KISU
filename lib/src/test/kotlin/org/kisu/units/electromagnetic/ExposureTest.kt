package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.coulombsPerKilogram
import org.kisu.units.electromagnetic.Exposure.Companion.CoulombPerKilogram

class ExposureTest : StringSpec({
    "creates an Exposure" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().coulombsPerKilogram.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CoulombPerKilogram(magnitude.builder().metric)
                symbol shouldBe CoulombPerKilogram().toString()
            }
        }
    }

    "creates a base Exposure" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.coulombsPerKilogram.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CoulombPerKilogram()
                symbol shouldBe CoulombPerKilogram().toString()
            }
        }
    }
})
