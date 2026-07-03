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
import org.kisu.prefixes.Decimal
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
                expression shouldBe Bit(Binary.BASE)
                symbol shouldBe Bit.UNIT.toString()
            }
        }
    }

    "keeps optimal information representation in its original lane" {
        Information(Magnitude(8000), Bit(Decimal.BASE)).optimal.representation shouldBe "8 kbit"
        Information(Magnitude(8192), Bit(Binary.BASE)).optimal.representation shouldBe "8 Kibit"
        Information(Magnitude(1000), Byte(Decimal.BASE)).optimal.representation shouldBe "1 kB"
        Information(Magnitude(1024), Byte(Binary.BASE)).optimal.representation shouldBe "1 KiB"
    }

    "canonicalizes byte information to bits" {
        Information(Magnitude(1), Byte(Decimal.KILO)).canonical.should { (amount, expression, symbol) ->
            amount shouldBe Magnitude(8000)
            expression shouldBe Bit(Decimal.BASE)
            symbol shouldBe Bit.UNIT.toString()
        }
        Information(Magnitude(1), Byte(Binary.KIBI)).canonical.should { (amount, expression, symbol) ->
            amount shouldBe Magnitude(8192)
            expression shouldBe Bit(Binary.BASE)
            symbol shouldBe Bit.UNIT.toString()
        }
    }
})
