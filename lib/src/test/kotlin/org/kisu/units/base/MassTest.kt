package org.kisu.units.base

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.string.shouldStartWith
import io.kotest.property.Arb
import io.kotest.property.arbitrary.negativeLong
import io.kotest.property.arbitrary.positiveLong
import io.kotest.property.checkAll
import org.kisu.bigDecimal
import org.kisu.test.generators.MetricBuilders
import org.kisu.units.builders.grams
import org.kisu.units.exceptions.NegativeMass

class MassTest : StringSpec({
    "a negative mass is physically meaningless" {
        checkAll(Arb.negativeLong(), MetricBuilders.generator) { magnitude, builder ->
            shouldThrow<NegativeMass> { magnitude.builder().grams }
        }
    }

    "a positive mass constructs successfully" {
        checkAll(Arb.positiveLong(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().grams
                .representation shouldStartWith "${magnitude.bigDecimal} ${magnitude.builder().metric}"
        }
    }
})
