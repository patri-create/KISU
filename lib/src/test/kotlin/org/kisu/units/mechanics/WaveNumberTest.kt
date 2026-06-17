package org.kisu.units.mechanics

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.magnitude
import org.kisu.units.builders.reciprocalMetres

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
})
