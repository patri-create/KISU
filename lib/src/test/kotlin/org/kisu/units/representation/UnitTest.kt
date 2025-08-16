package org.kisu.units.representation

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll
import org.kisu.test.generators.Exponents
import org.kisu.test.generators.Units

class UnitTest : StringSpec({
    "unit is recognized as positive" {
        checkAll(Units.symbols, Exponents.range(1, Int.MAX_VALUE)) { symbol, exponent ->
            Unit(symbol, exponent).positive.shouldBeTrue()
        }
    }

    "unit is recognized as negative" {
        checkAll(Units.symbols, Exponents.range(Int.MIN_VALUE, 0)) { symbol, exponent ->
            Unit(symbol, exponent).positive.shouldBeFalse()
        }
    }

    "unit is recognized as zero" {
        checkAll(Units.symbols) { symbol ->
            Unit(symbol, Exponent(0)).zero.shouldBeTrue()
        }
    }

    "unit is not recognized as zero" {
        checkAll(Units.symbols, Exponents.range().filter { !it.zero }) { symbol, exponent ->
            Unit(symbol, exponent).zero.shouldBeFalse()
        }
    }

    "inverts the exponent" {
        checkAll(Units.symbols, Exponents.generator) { symbol, exponent ->
            Unit(symbol, exponent).inverted shouldBe Unit(symbol, exponent.inverted)
        }
    }

    "multiplying UnitRepresentations with same unit adds exponents" {
        checkAll(Units.symbols, Arb.int(), Arb.int()) { unit, a, b ->
            val u1 = Unit(unit, a)
            val u2 = Unit(unit, b)
            val result = u1 * u2

            result.toString() shouldBe Unit(unit, a + b).toString()
        }
    }

    "cannot multiply two different units" {
        checkAll(Units.distinct(2)) { (left, right) ->
            shouldThrow<IllegalArgumentException> {
                left.unit * right.unit
            }
        }
    }

    "dividing UnitRepresentations with same unit subtracts exponents" {
        checkAll(Units.symbols, Arb.int(), Arb.int()) { unit, a, b ->
            val u1 = Unit(unit, a)
            val u2 = Unit(unit, b)
            val result = u1 / u2

            result.toString() shouldBe Unit(unit, a - b).toString()
        }
    }

    "cannot divide two different units" {
        checkAll(Units.distinct(2)) { (left, right) ->
            shouldThrow<IllegalArgumentException> {
                left.unit / right.unit
            }
        }
    }

    "equality only depends on unit name, not exponent" {
        checkAll(Units.symbols, Arb.int(), Arb.int()) { unit, a, b ->
            val u1 = Unit(unit, a)
            val u2 = Unit(unit, b)

            u1 shouldBe u2
            u1.hashCode() shouldBe u2.hashCode()
        }
    }

    "inequality when units differ" {
        checkAll(Arb.int()) { exponent ->
            val u1 = Unit("kg", exponent)
            val u2 = Unit("g", exponent)

            u1 shouldNotBe u2
        }
    }

    "equality is reflexive" {
        checkAll(Units.symbols, Exponents.generator) { unit, exponent ->
            val a = Unit(unit, exponent)
            a shouldBe a
        }
    }

    "equality is symmetric" {
        checkAll(Units.symbols, Exponents.generator) { unit, exponent ->
            val a = Unit(unit, exponent)
            val b = Unit(unit, exponent)

            a shouldBe b
            b shouldBe a
        }
    }

    "equality is transitive" {
        checkAll(Units.symbols, Exponents.generator) { unit, exponent ->
            val a = Unit(unit, exponent)
            val b = Unit(unit, exponent)
            val c = Unit(unit, exponent)

            a shouldBe b
            b shouldBe c
            b shouldBe c
        }
    }

    "representation includes superscript exponent" {
        checkAll(Arb.int()) { exponent ->
            val representation = Unit("s", exponent).toString()
            val expected = "s" + Exponent(exponent).toString()
            representation shouldBe expected
        }
    }

    "Units respect the canonical order" {
        checkAll(arbitrary { CANONICAL_ORDER.shuffled() }) { list ->
            list.sorted() shouldContainInOrder CANONICAL_ORDER
        }
    }
})
