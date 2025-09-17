package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.webersPerMetre
import org.kisu.units.electromagnetic.MagneticVectorPotential.Companion.WeberPerMetre

class MagneticVectorPotentialTest : StringSpec({
    "creates a MagneticVectorPotential" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().webersPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WeberPerMetre(magnitude.builder().metric)
                symbol shouldBe WeberPerMetre().toString()
            }
        }
    }

    "creates a base MagneticVectorPotential" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.webersPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe WeberPerMetre()
                symbol shouldBe WeberPerMetre().toString()
            }
        }
    }
})
