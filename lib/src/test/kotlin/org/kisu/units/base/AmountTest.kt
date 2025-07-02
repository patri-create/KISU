package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.string.shouldStartWith
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.bigDecimal
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.bigDecimal
import org.kisu.units.builders.moles

class AmountTest : StringSpec({

    "creates an Amount" {
        checkAll(Arb.bigDecimal(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().moles
                .representation shouldStartWith "${magnitude.bigDecimal} ${magnitude.builder().metric}"
        }
    }
})
