package org.kisu.units.derived

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.string.shouldStartWith
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.siemens

class ConductanceTest : StringSpec({
    "creates a Conductance" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().siemens
                .representation shouldStartWith "$magnitude ${magnitude.builder().metric}"
        }
    }

    "creates a base Conductance" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.siemens.representation shouldStartWith "$magnitude"
        }
    }
})
