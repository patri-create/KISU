package org.kisu.units.base

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldStartWith
import io.kotest.property.Arb
import io.kotest.property.arbitrary.negativeLong
import io.kotest.property.arbitrary.positiveLong
import io.kotest.property.checkAll
import org.kisu.bigDecimal
import org.kisu.test.generators.MetricBuilders
import org.kisu.units.builders.grams
import org.kisu.units.builders.kelvins
import org.kisu.units.exceptions.NegativeAmountOfSubstance
import org.kisu.units.exceptions.NegativeMass
import org.kisu.units.exceptions.NegativeTemperature

class TemperatureTest : StringSpec({
    "a negative temperature is physically meaningless" {
        checkAll(Arb.negativeLong(), MetricBuilders.generator) { magnitude, builder ->
            shouldThrow<NegativeTemperature> { magnitude.builder().kelvins }
        }
    }

    "a positive temperature constructs successfully" {
        checkAll(Arb.positiveLong(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().kelvins.representation shouldStartWith "${magnitude.bigDecimal} ${magnitude.builder().metric}"
        }
    }
})
