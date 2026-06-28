package org.kisu.units.chemistry

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.magnitude
import org.kisu.test.generators.reciprocalMagnitude
import org.kisu.units.builders.reciprocalMoles

class ReciprocalAmountTest : StringSpec({
    "creates a ReciprocalAmount" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().reciprocalMoles.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe ReciprocalMole(magnitude.builder().metric)
                symbol shouldBe ReciprocalMole().toString()
            }
        }
    }

    "creates a base ReciprocalAmount" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.reciprocalMoles.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe ReciprocalMole()
                symbol shouldBe ReciprocalMole().toString()
            }
        }
    }

    "converts to Amount" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.reciprocalMoles.amount.reciprocalAmount.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe ReciprocalMole()
                symbol shouldBe ReciprocalMole().toString()
            }
        }
    }
})
