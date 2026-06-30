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
import org.kisu.units.builders.reciprocalFarads

class ElastanceTest : StringSpec({
    "creates an Elastance" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().reciprocalFarads.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe InverseFarad(magnitude.builder().metric)
                symbol shouldBe InverseFarad().toString()
            }
        }
    }

    "creates a base Elastance" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.reciprocalFarads.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe InverseFarad()
                symbol shouldBe InverseFarad().toString()
            }
        }
    }

    "converts to Capacitance" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.reciprocalFarads.capacitance.elastance.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe InverseFarad()
                symbol shouldBe InverseFarad().toString()
            }
        }
    }
    // Dimension-aware arithmetic properties
    "multiplying an Elastance by an ElectricCharge returns an ElectricPotential" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = Elastance(leftMagnitude, leftPrefix)
            val right = ElectricCharge(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = ElectricPotential(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
