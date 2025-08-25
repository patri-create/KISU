package org.kisu.units.special

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.cubicMetres

class VolumeTest : StringSpec({
    "creates a Volume" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().cubicMetres.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CubicMetre(magnitude.builder().metric)
                symbol shouldBe CubicMetre.UNIT.toString()
            }
        }
    }

    "creates a base Volume" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.cubicMeters.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe CubicMetre()
                symbol shouldBe CubicMetre.UNIT.toString()
            }
        }
    }
})
