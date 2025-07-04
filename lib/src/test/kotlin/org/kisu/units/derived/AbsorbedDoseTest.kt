package org.kisu.units.derived

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.string.shouldStartWith
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.bigDecimal
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.grays

class AbsorbedDoseTest : StringSpec({
    "creates an AbsorbedDose" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().grays
                .representation shouldStartWith "$magnitude ${magnitude.builder().metric}"
        }
    }

    "creates a base AbsorbedDose" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.grays.representation shouldStartWith "$magnitude"
        }
    }
})