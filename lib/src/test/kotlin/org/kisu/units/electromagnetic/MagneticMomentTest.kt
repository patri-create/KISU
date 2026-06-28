package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.test.generators.reciprocalMagnitude
import org.kisu.units.base.Length
import org.kisu.units.builders.webersMetre
import org.kisu.units.electromagnetic.MagneticMoment.Companion.WeberMetre
import org.kisu.units.special.MagneticFlux

class MagneticMomentTest : StringSpec({
    "creates a MagneticMoment" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().webersMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WeberMetre(magnitude.builder().metric)
                symbol shouldBe WeberMetre().toString()
            }
        }
    }

    "creates a base MagneticMoment" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.webersMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WeberMetre()
                symbol shouldBe WeberMetre().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a MagneticMoment by a Length returns a MagneticFlux" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MagneticMoment(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = MagneticFlux(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a MagneticMoment by a MagneticFlux returns a Length" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MagneticMoment(leftMagnitude, leftPrefix)
            val right = MagneticFlux(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Length(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
