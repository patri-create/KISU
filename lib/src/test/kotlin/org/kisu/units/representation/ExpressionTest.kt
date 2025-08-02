package org.kisu.units.representation

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.test.fakes.TestUnit
import org.kisu.test.generators.Binaries
import org.kisu.test.generators.Metrics

class ExpressionTest : StringSpec({
    "the string representation is the symbol" {
        checkAll(Metrics.generator, Binaries.generator) { metric, binary ->
            val expression = Quotient(
                Scalar(metric, TestUnit.SYMBOL),
                Scalar(binary, TestUnit.SYMBOL)
            )
            expression.symbol shouldBe expression.toString()
        }
    }

    "equality is reflexive" {
        checkAll(Metrics.generator) { metric ->
            val x = Scalar(metric, TestUnit.SYMBOL)

            (x == x).shouldBeTrue()
        }
    }

    "equality is symmetric" {
        checkAll(Metrics.generator) { metric ->
            val x = Scalar(metric, TestUnit.SYMBOL)
            val y = Scalar(metric, TestUnit.SYMBOL)

            (x == y) shouldBe (y == x)
        }
    }

    "equality is transitive" {
        checkAll(Metrics.generator) { metric ->
            val x = Scalar(metric, TestUnit.SYMBOL)
            val y = Scalar(metric, TestUnit.SYMBOL)
            val z = Scalar(metric, TestUnit.SYMBOL)

            (x == y).shouldBeTrue()
            (y == z).shouldBeTrue()
            (x == z).shouldBeTrue()
        }
    }

    @Suppress("EqualsNullCall")
    "equality is non-null" {
        checkAll(Metrics.generator) { metric ->
            val x = Scalar(metric, TestUnit.SYMBOL)

            (x.equals(null)).shouldBeFalse()
        }
    }
})
