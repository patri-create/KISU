package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.string.shouldStartWith
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.bigDecimal
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.amperes

class CurrentTest : StringSpec({
    "creates a Current" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().amperes
                .representation shouldStartWith "${magnitude.bigDecimal} ${magnitude.builder().metric}"
        }
    }

    "creates a base Current" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.amperes.representation shouldStartWith "${magnitude.bigDecimal}"
        }
    }
})
