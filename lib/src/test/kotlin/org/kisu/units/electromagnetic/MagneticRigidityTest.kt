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
import org.kisu.units.builders.teslasMetre
import org.kisu.units.electromagnetic.MagneticRigidity.Companion.TeslaMetre
import org.kisu.units.special.MagneticFluxDensity

class MagneticRigidityTest : StringSpec({
    "creates a MagneticRigidity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().teslasMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe TeslaMetre(magnitude.builder().metric)
                symbol shouldBe TeslaMetre().toString()
            }
        }
    }

    "creates a base MagneticRigidity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.teslasMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe TeslaMetre()
                symbol shouldBe TeslaMetre().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "dividing a MagneticRigidity by a Length returns a MagneticFluxDensity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MagneticRigidity(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = MagneticFluxDensity(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a MagneticRigidity by a MagneticFluxDensity returns a Length" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = MagneticRigidity(leftMagnitude, leftPrefix)
            val right = MagneticFluxDensity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Length(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
