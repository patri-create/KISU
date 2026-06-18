package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.magnitude
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
})
