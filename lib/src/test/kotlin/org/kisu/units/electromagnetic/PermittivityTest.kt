package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.units.base.Length
import org.kisu.units.builders.faradsPerMetre
import org.kisu.units.electromagnetic.Permittivity.Companion.FaradPerMetre
import org.kisu.units.special.Capacitance

class PermittivityTest : StringSpec({
    "creates a Permittivity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().faradsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe FaradPerMetre(magnitude.builder().metric)
                symbol shouldBe FaradPerMetre().toString()
            }
        }
    }

    "creates a base Permittivity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.faradsPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe FaradPerMetre()
                symbol shouldBe FaradPerMetre().toString()
            }
        }
    }

    // Dimension-aware arithmetic properties
    "multiplying a Permittivity by a Length returns a Capacitance" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Permittivity(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Capacitance(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
