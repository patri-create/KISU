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
import io.kotest.property.arbitrary.short
import io.kotest.property.checkAll
import java.math.BigDecimal

class BigDecimalsTest : StringSpec({
    "BigDecimal is detected as zero" {
        BigDecimal.ZERO.zero.shouldBeTrue()
    }

    "zero returns false when it is not zero" {
        checkAll(Arb.bigDecimal().filter { !it.zero }) { number ->
            number.zero.shouldBeFalse()
        }
    }

    "converts a BigDecimal into a BigDecimal" {
        checkAll(Arb.bigDecimal()) { number ->
            number.bigDecimal shouldBe number
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
})
