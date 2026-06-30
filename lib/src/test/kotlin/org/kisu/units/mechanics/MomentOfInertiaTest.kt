package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.test.generators.reciprocalMagnitude
import org.kisu.units.base.Mass
import org.kisu.units.builders.gramsSquareMetre
import org.kisu.units.mechanics.MomentOfInertia.Companion.KilogramSquareMetre
import org.kisu.units.special.Area

class MomentOfInertiaTest : StringSpec({
    "creates a MomentOfInertia" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().gramsSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramSquareMetre(magnitude.builder().metric)
                symbol shouldBe KilogramSquareMetre().toString()
            }
        }
    }

    "creates a base MomentOfInertia" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.gramsSquareMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe KilogramSquareMetre()
                symbol shouldBe KilogramSquareMetre().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a MomentOfInertia by a Mass returns an Area" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MomentOfInertia(leftMagnitude, leftPrefix)
            val right = Mass(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Area(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a MomentOfInertia by an Area returns a Mass" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MomentOfInertia(leftMagnitude, leftPrefix)
            val right = Area(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Mass(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
