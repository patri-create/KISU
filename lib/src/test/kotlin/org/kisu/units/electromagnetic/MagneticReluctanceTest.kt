package org.kisu.units.electromagnetic

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.reciprocalHenries

class MagneticReluctanceTest : StringSpec({
    "creates a MagneticReluctance" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().reciprocalHenries.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe ReciprocalHenry(magnitude.builder().metric)
                symbol shouldBe ReciprocalHenry().toString()
            }
        }
    }

    "creates a base MagneticReluctance" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.reciprocalHenries.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe ReciprocalHenry()
                symbol shouldBe ReciprocalHenry().toString()
            }
        }
    }
})
