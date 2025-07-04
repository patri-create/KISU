package org.kisu.units.derived

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.string.shouldStartWith
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.lumens

class LuminousFluxTest : StringSpec({
    "creates a LuminousFlux" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().lumens
                .representation shouldStartWith "$magnitude ${magnitude.builder().metric}"
        }
    }

    "creates a base LuminousFlux" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.lumens.representation shouldStartWith "$magnitude"
        }
    }
})
