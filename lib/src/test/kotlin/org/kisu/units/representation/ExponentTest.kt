package org.kisu.units.representation

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldBeEmpty
import io.kotest.property.Arb
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.filterNot
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.map
import io.kotest.property.checkAll
import org.kisu.test.generators.Exponents
import org.kisu.test.utils.superscript
import kotlin.math.absoluteValue

class ExponentTest : StringSpec({
    "represents correctly an exponent" {
        checkAll(Arb.int().filter { it.absoluteValue > 1 }) { n ->
            Exponent(n).toString() shouldBe n.superscript
        }
    }

    "exponent is recognized as positive" {
        checkAll(Arb.int(1, Int.MAX_VALUE)) { exponent ->
            Exponent(exponent).positive.shouldBeTrue()
        }
    }

    "exponent is recognized as negative" {
        checkAll(Arb.int(Int.MIN_VALUE, 0)) { exponent ->
            Exponent(exponent).positive.shouldBeFalse()
        }
    }

    "exponent is recognized as zero" {
        Exponent(0).zero.shouldBeTrue()
    }

    "exponent is not recognized as zero" {
        checkAll(Arb.int().filter { it != 0 }) { exponent ->
            Exponent(exponent).zero.shouldBeFalse()
        }
    }

    "inverts the exponent" {
        checkAll(Arb.int()) { exponent ->
            Exponent(exponent).inverted shouldBe Exponent(-exponent)
        }
    }

    "exponent 0 and 1 are represented by empty string" {
        checkAll(Arb.int(0..1).map(::Exponent)) { exponent ->
            exponent.toString().shouldBeEmpty()
        }
    }

    "adds two exponents" {
        checkAll(Arb.int(), Arb.int()) { a, b ->
            (Exponent(a) + Exponent(b)) shouldBe Exponent(a + b)
        }
    }

    "addition should be associative" {
        checkAll(Exponents.generator, Exponents.generator, Exponents.generator) { a, b, c ->
            ((a + b) + c) shouldBe (a + (b + c))
        }
    }

    "addition is commutative" {
        checkAll(Exponents.generator, Exponents.generator) { a, b ->
            (a + b) shouldBe (b + a)
        }
    }

    "zero is the additive identity" {
        checkAll(Exponents.generator) { a ->
            val zero = Exponent(0)
            (a + zero) shouldBe a
            (zero + a) shouldBe a
        }
    }

    "each exponent has an additive inverse" {
        checkAll(Arb.int()) { x ->
            val a = Exponent(x)
            val negA = Exponent(-x)
            (a + negA) shouldBe Exponent(0)
        }
    }

    "substracts two exponents" {
        checkAll(Arb.int(), Arb.int()) { a, b ->
            (Exponent(a) - Exponent(b)) shouldBe Exponent(a - b)
        }
    }

    "subtraction undoes addition on the right" {
        checkAll(Exponents.generator, Exponents.generator) { a, b ->
            ((a + b) - b) shouldBe a
        }
    }

    "subtraction undoes addition on the left" {
        checkAll(Exponents.generator, Exponents.generator) { a, b ->
            ((b + a) - a) shouldBe b
        }
    }

    "zero is the identity for subtraction" {
        checkAll(Exponents.generator) { a ->
            val zero = Exponent(0)
            (a - zero) shouldBe a
        }
    }

    "self subtraction yields zero" {
        checkAll(Exponents.generator) { a ->
            (a - a).toString() shouldBe Exponent(0).toString()
        }
    }

    "exponents with different values are not equal" {
        checkAll(Arb.int(), Arb.int().filterNot { it == 0 }) { x, offset ->
            val a = Exponent(x)
            val b = Exponent(x + offset)
            (a == b) shouldBe false
        }
    }

    "equality is reflexive" {
        checkAll(Exponents.generator) { a ->
            a shouldBe a
        }
    }

    "equality is symmetric" {
        checkAll(Arb.int()) { x ->
            val a = Exponent(x)
            val b = Exponent(x)
            (a == b) shouldBe (b == a)
        }
    }

    "equality is transitive" {
        checkAll(Arb.int()) { x ->
            val a = Exponent(x)
            val b = Exponent(x)
            val c = Exponent(x)

            a shouldBe b
            b shouldBe c
            a shouldBe c
        }
    }
})
