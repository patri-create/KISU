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
import org.kisu.units.builders.ohmsMetre
import org.kisu.units.electromagnetic.Resistivity.Companion.OhmMetre
import org.kisu.units.special.Resistance

class ResistivityTest : StringSpec({
    "creates a Resistivity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().ohmsMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe OhmMetre(magnitude.builder().metric)
                symbol shouldBe OhmMetre().toString()
            }
        }
    }

    "creates a base Resistivity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.ohmsMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe OhmMetre()
                symbol shouldBe OhmMetre().toString()
            }
        }
    }

    "converts to ElectricConductivity" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.ohmsMetre.electricConductivity.resistivity.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe OhmMetre()
                symbol shouldBe OhmMetre().toString()
            }
        }
    }
    // Dimension-aware arithmetic properties
    "dividing a Resistivity by a Length returns a Resistance" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Resistivity(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Resistance(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing a Resistivity by a Resistance returns a Length" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Resistivity(leftMagnitude, leftPrefix)
            val right = Resistance(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Length(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
