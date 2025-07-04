package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.string.shouldStartWith
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.bigDecimal
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.grams

class MassTest : StringSpec({
    "creates mass" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().grams
                .representation shouldStartWith "${magnitude.bigDecimal} ${magnitude.builder().metric}"
        }
    }

    "creates a base Mass" {
        checkAll(Arb.bigDecimal()) { magnitude ->
            magnitude.grams.representation shouldStartWith "${magnitude.bigDecimal}"
        }
    }
})
