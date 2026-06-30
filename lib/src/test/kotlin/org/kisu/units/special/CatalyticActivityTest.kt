package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.base.Amount
import org.kisu.units.base.Time
import org.kisu.units.builders.katals

class CatalyticActivityTest : StringSpec({
    "creates a CatalyticActivity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().katals.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Katal(magnitude.builder().metric)
                symbol shouldBe Katal.UNIT.toString()
            }
        }
    }

    "creates a base CatalyticActivity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.katals.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Katal()
                symbol shouldBe Katal.UNIT.toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying a CatalyticActivity by a Time returns an Amount" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = CatalyticActivity(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Amount(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
