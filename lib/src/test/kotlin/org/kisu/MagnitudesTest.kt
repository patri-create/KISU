package org.kisu

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.double
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.float
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.long
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.short
import io.kotest.property.checkAll
import org.kisu.test.generators.magnitude
import java.math.BigInteger

class MagnitudesTest : StringSpec({
    "number is detected as zero" {
        checkAll(
            Arb.int(range = 0..40)
                .map { scale -> Magnitude(BigInteger("0"), scale) },
        ) { number ->
            number.zero.shouldBeTrue()
        }
    }

    "number is detected as not zero" {
        checkAll(Arb.magnitude().filter { number -> number.compareTo(Magnitude.ZERO) != 0 }) { number ->
            number.zero.shouldBeFalse()
        }
    }

    "number is detected as one" {
        checkAll(
            Arb.int(range = 0..40)
                .map { scale -> Magnitude(BigInteger.TEN.pow(scale), scale) },
        ) { number ->
            number.one.shouldBeTrue()
        }
    }

    "number is detected as not one" {
        checkAll(Arb.magnitude().filter { number -> number.compareTo(Magnitude.ONE) != 0 }) { number ->
            number.one.shouldBeFalse()
        }
    }

    "a number is decimal" {
        checkAll(Arb.magnitude(minFractionalDigits = 1, maxDigits = 5)) { number ->
            number.hasFraction.shouldBeTrue()
        }
    }

    "a number is integer" {
        checkAll(Arb.int().map { number -> number.magnitude }) { number ->
            number.hasFraction.shouldBeFalse()
        }
    }

    "converts a Short into a Magnitude" {
        checkAll(Arb.short()) { number ->
            number.magnitude shouldBe Magnitude(number.toString())
        }
    }

    "converts an Integer into a Magnitude" {
        checkAll(Arb.int()) { number ->
            number.magnitude shouldBe Magnitude(number.toString())
        }
    }

    "converts a Long into a Magnitude" {
        checkAll(Arb.long()) { number ->
            number.magnitude shouldBe Magnitude(number.toString())
        }
    }

    "converts a Float into a Magnitude" {
        checkAll(Arb.float().filter { it.isFinite() && !it.isNaN() }) { number ->
            number.magnitude shouldBe Magnitude(number.toString())
        }
    }

    "converts a Double into a Magnitude" {
        checkAll(Arb.double().filter { it.isFinite() && !it.isNaN() }) { number ->
            number.magnitude shouldBe Magnitude(number.toString())
        }
    }

    "a negative number is tagged as negative" {
        checkAll(Arb.magnitude().filter { it.signum == Magnitude.Signum.NEGATIVE }) { number ->
            number.negative.shouldBeTrue()
        }
    }

    "a positive or zero number is not tagged as negative" {
        checkAll(Arb.magnitude().filter { it.signum != Magnitude.Signum.NEGATIVE }) { number ->
            number.negative.shouldBeFalse()
        }
    }
})
