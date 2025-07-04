package org.kisu.units.derived

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.string.shouldStartWith
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.joules

class EnergyTest : StringSpec({
    "creates an Energy" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().joules
                .representation shouldStartWith "$magnitude ${magnitude.builder().metric}"
        }
    }

    "creates a base Energy" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.joules.representation shouldStartWith "$magnitude"
        }
    }
})
