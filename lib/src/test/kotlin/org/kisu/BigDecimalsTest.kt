package org.kisu

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.bigDecimal
import io.kotest.property.arbitrary.double
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.float
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.long
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.short
import io.kotest.property.checkAll
import org.kisu.test.generators.bigDecimal
import java.math.BigDecimal
import java.math.BigInteger

class BigDecimalsTest : StringSpec({
    "number is detected as zero" {
        checkAll(
            Arb.int(range = 0..40)
                .map { scale -> BigDecimal(BigInteger("0"), scale) },
        ) { number ->
            number.zero.shouldBeTrue()
        }
    }

    "number is detected as not zero" {
        checkAll(Arb.bigDecimal().filter { number -> number.compareTo(BigDecimal.ZERO) != 0 }) { number ->
            number.zero.shouldBeFalse()
        }
    }

    "number is detected as one" {
        checkAll(
            Arb.int(range = 0..40)
                .map { scale -> BigDecimal.ONE.setScale(scale) },
        ) { number ->
            number.one.shouldBeTrue()
        }
    }

    "number is detected as not one" {
        checkAll(Arb.bigDecimal().filter { number -> number.compareTo(BigDecimal.ONE) != 0 }) { number ->
            number.one.shouldBeFalse()
        }
    }

    "converts a BigDecimal into a BigDecimal" {
        checkAll(Arb.bigDecimal()) { number ->
            number.bigDecimal shouldBe number
        }
    }

    "a number is decimal" {
        checkAll(Arb.bigDecimal(minFractionalDigits = 1, maxDigits = 5)) { number ->
            number.hasFraction.shouldBeTrue()
        }
    }

    "a number is integer" {
        checkAll(Arb.int().map { number -> number.bigDecimal }) { number ->
            number.hasFraction.shouldBeFalse()
        }
    }

    "converts a Short into a BigDecimal" {
        checkAll(Arb.short()) { number ->
            number.bigDecimal shouldBe BigDecimal(number.toString())
        }
    }

    "converts an Integer into a BigDecimal" {
        checkAll(Arb.int()) { number ->
            number.bigDecimal shouldBe BigDecimal(number.toString())
        }
    }

    "converts a Long into a BigDecimal" {
        checkAll(Arb.long()) { number ->
            number.bigDecimal shouldBe BigDecimal(number.toString())
        }
    }

    "converts a Float into a BigDecimal" {
        checkAll(Arb.float().filter { it.isFinite() && !it.isNaN() }) { number ->
            number.bigDecimal shouldBe BigDecimal(number.toString())
        }
    }

    "converts a Double into a BigDecimal" {
        checkAll(Arb.double().filter { it.isFinite() && !it.isNaN() }) { number ->
            number.bigDecimal shouldBe BigDecimal(number.toString())
        }
    }

    "a negative number is tagged as negative" {
        checkAll(Arb.bigDecimal().filter { it.signum() == -1 }) { number ->
            number.negative.shouldBeTrue()
        }
    }

    "a positive or zero number is not tagged as negative" {
        checkAll(Arb.bigDecimal().filter { it.signum() != -1 }) { number ->
            number.negative.shouldBeFalse()
        }
    }
})
