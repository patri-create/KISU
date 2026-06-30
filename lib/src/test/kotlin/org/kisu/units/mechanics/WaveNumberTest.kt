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
import org.kisu.units.builders.reciprocalMetres
import org.kisu.units.special.Area
import org.kisu.units.special.Volume

class WaveNumberTest : StringSpec({
    "creates a WaveNumber" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().reciprocalMetres.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe ReciprocalMetre(magnitude.builder().metric)
                symbol shouldBe ReciprocalMetre().toString()
            }
        }
    }

    "creates a base WaveNumber" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.reciprocalMetres.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe ReciprocalMetre()
                symbol shouldBe ReciprocalMetre().toString()
            }
        }
    }

    "converts to Length" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.reciprocalMetres.wavelength.waveNumber.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe ReciprocalMetre()
                symbol shouldBe ReciprocalMetre().toString()
            }
        }
    }
    // Dimension-aware arithmetic properties
    "multiplying a WaveNumber by a Volume returns an Area" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = WaveNumber(leftMagnitude, leftPrefix)
            val right = Volume(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Area(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
