package org.kisu.units.base

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.positiveLong
import io.kotest.property.checkAll
import org.kisu.bigDecimal
import org.kisu.prefixes.Binary
import org.kisu.test.generators.BinaryBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.bits
import org.kisu.units.exceptions.SubBitInformation
import org.kisu.units.representation.Scalar
import java.math.MathContext

class InformationTest : StringSpec({
    "fractional information is physically meaningless" {
        checkAll(
            Arb.bigDecimal(minFractionalDigits = 1).map { it.abs() },
            BinaryBuilders.generator
        ) { magnitude, builder ->
            val scaled = magnitude.divide(magnitude.builder().binary.factor, MathContext.DECIMAL128)
            shouldThrow<SubBitInformation> { scaled.builder().bits }
        }
    }

    "creates Information" {
        checkAll(Arb.positiveLong(), BinaryBuilders.generator) { magnitude, builder ->
            magnitude.builder().bits.should { (amount, expression, symbol) ->
                amount shouldBe magnitude.bigDecimal
                expression shouldBe Scalar(magnitude.builder().binary, unit = Information.UNIT)
                symbol shouldBe Information.UNIT.toString()
            }
        }
    }

    "creates a base Information" {
        checkAll(Arb.positiveLong()) { magnitude ->
            magnitude.bits.should { (amount, expression, symbol) ->
                amount shouldBe magnitude.bigDecimal
                expression shouldBe Scalar(Binary.BASE, unit = Information.UNIT)
                symbol shouldBe Information.UNIT.toString()
            }
        }
    }
})
