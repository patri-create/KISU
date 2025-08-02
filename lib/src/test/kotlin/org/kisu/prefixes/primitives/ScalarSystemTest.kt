package org.kisu.prefixes.primitives

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeSorted
import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.one
import org.kisu.test.fakes.TestUnit
import org.kisu.test.generators.Metrics
import org.kisu.units.representation.Scalar

class ScalarSystemTest : StringSpec({

    "retrieves base unit" {
        checkAll(Metrics.generator) { prefix ->
            Scalar(prefix, TestUnit.UNIT).canonical.factor.one.shouldBeTrue()
        }
    }

    "retrieves all prefixes for a system" {
        checkAll(Metrics.generator) { prefix ->
            Scalar(prefix, TestUnit.UNIT).all
                .map { scalar -> scalar.factor }
                .shouldContainInOrder(prefix.all.map { prefix -> prefix.factor })
        }
    }

    "all prefixes from a system are sorted by power" {
        checkAll(Metrics.generator) { prefix ->
            Scalar(prefix, TestUnit.UNIT).all.shouldBeSorted()
        }
    }

    "retrieves the smallest prefix" {
        checkAll(Metrics.generator) { prefix ->
            val scalar = Scalar(prefix, TestUnit.UNIT)
            scalar.smallest shouldBe scalar.all.first()
        }
    }

    "retrieves the largest prefix" {
        checkAll(Metrics.generator) { prefix ->
            val scalar = Scalar(prefix, TestUnit.UNIT)
            scalar.largest shouldBe scalar.all.last()
        }
    }
})
