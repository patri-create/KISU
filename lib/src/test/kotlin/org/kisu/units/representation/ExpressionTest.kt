package org.kisu.units.representation

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.test.fakes.TestMeasure
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.Units

class ExpressionTest : StringSpec({
    "the string representation is the symbol" {
        checkAll(Units.distinct(2)) { (a, b) ->
            val expression = Quotient(a.self, b.self)

            expression.symbol shouldBe expression.toString()
        }
    }

    "equality is reflexive" {
        checkAll(Units.generator) { unit ->
            (unit == unit).shouldBeTrue()
        }
    }

    "equality is symmetric" {
        checkAll(Units.distinct(2)) { (a, b) ->
            (a == b) shouldBe (b == a)
        }
    }

    "equality is transitive" {
        checkAll(Units.generator) { unit ->
            val a = unit
            val b = unit
            val c = unit


            (a == b).shouldBeTrue()
            (b == c).shouldBeTrue()
            (a == c).shouldBeTrue()
        }
    }

    @Suppress("EqualsNullCall")
    "equality is non-null" {
        checkAll(Units.generator) { unit ->
            (unit.equals(null)).shouldBeFalse()
        }
    }
})
