package org.kisu.units.base

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.positiveLong
import io.kotest.property.checkAll
import org.kisu.Magnitude
import org.kisu.magnitude
import org.kisu.prefixes.Binary
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.test.generators.BinaryBuilders
import org.kisu.test.generators.magnitude
import org.kisu.units.builders.bits
import org.kisu.units.exceptions.SubBitInformation

class InformationTest : StringSpec({
    val scale = ExponentialAlgebra<Binary>(Magnitude.TWO)

    "fractional information is physically meaningless" {
        checkAll(
            Arb.magnitude(minFractionalDigits = 1).map { it.abs },
            BinaryBuilders.generator
        ) { magnitude, builder ->
            val scaled = magnitude / scale.factor(magnitude.builder().binary)
            shouldThrow<SubBitInformation> { scaled.builder().bits }
        }
    }

    "creates Information" {
        checkAll(Arb.positiveLong(), BinaryBuilders.generator) { magnitude, builder ->
            magnitude.builder().bits.should { (amount, expression, symbol) ->
                amount shouldBe magnitude.magnitude
                expression shouldBe Bit(magnitude.builder().binary)
                symbol shouldBe Bit.UNIT.toString()
            }
        }
    }

    "creates a base Information" {
        checkAll(Arb.positiveLong()) { magnitude ->
            magnitude.bits.should { (amount, expression, symbol) ->
                amount shouldBe magnitude.magnitude
                expression shouldBe Bit()
                symbol shouldBe Bit.UNIT.toString()
            }
        }
    }
})
