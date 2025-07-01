package org.kisu.units.base

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldStartWith
import io.kotest.property.Arb
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.long
import io.kotest.property.arbitrary.map
import io.kotest.property.checkAll
import org.kisu.bigDecimal
import org.kisu.test.generators.MetricBuilders
import org.kisu.units.builders.amperes
import org.kisu.units.builders.moles

class CurrentTest : StringSpec({
    "current creates successfully" {
        checkAll(Arb.long().filter { it != 0L }, MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().amperes.representation shouldStartWith "${magnitude.bigDecimal} ${magnitude.builder().metric}"
        }
    }
})
