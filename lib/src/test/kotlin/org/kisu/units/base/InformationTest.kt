package org.kisu.units.base

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.string.shouldStartWith
import io.kotest.property.Arb
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.negativeLong
import io.kotest.property.arbitrary.positiveLong
import io.kotest.property.checkAll
import org.kisu.bigDecimal
import org.kisu.test.generators.BinaryBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.bits
import org.kisu.units.exceptions.NegativeInformation
import org.kisu.units.exceptions.SubBitInformation
import java.math.MathContext

class InformationTest : StringSpec({
    "negative information is physically meaningless" {
        checkAll(Arb.negativeLong(), BinaryBuilders.generator) { magnitude, builder ->
            shouldThrow<NegativeInformation> { magnitude.builder().bits }
        }
    }

    "fractional information is physically meaningless" {
        checkAll(
            Arb.bigDecimal(minFractionalDigits = 1).map { it.abs() },
            BinaryBuilders.generator
        ) { magnitude, builder ->
            val scaled = magnitude.divide(magnitude.builder().binary.factor, MathContext.DECIMAL128)
            shouldThrow<SubBitInformation> { scaled.builder().bits }
        }
    }

    "a positive and integer information constructs successfully" {
        checkAll(Arb.positiveLong(), BinaryBuilders.generator) { magnitude, builder ->
            magnitude.builder().bits.representation shouldStartWith "${magnitude.bigDecimal} ${magnitude.builder().binary}"
        }
    }
})
