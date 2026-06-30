package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.test.generators.reciprocalMagnitude
import org.kisu.units.base.Time
import org.kisu.units.builders.grays
import org.kisu.units.mechanics.AbsorbedDoseRate

class AbsorbedDoseTest : StringSpec({
    "creates an AbsorbedDose" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().grays
                .should { (amount, expression, symbol) ->
                    amount shouldBe magnitude
                    expression shouldBe Gray(magnitude.builder().metric)
                    symbol shouldBe Gray.UNIT.toString()
                }
        }
    }

    "creates a base AbsorbedDose" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.grays.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe Gray()
                symbol shouldBe Gray.UNIT.toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing an AbsorbedDose by a Time returns an AbsorbedDoseRate" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = AbsorbedDose(leftMagnitude, leftPrefix)
            val right = Time(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = AbsorbedDoseRate(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing an AbsorbedDose by an AbsorbedDoseRate returns a Time" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = AbsorbedDose(leftMagnitude, leftPrefix)
            val right = AbsorbedDoseRate(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Time(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
