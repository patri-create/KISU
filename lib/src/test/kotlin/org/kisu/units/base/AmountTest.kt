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
import org.kisu.units.builders.moles
import org.kisu.units.exceptions.NegativeAmountOfSubstance

class AmountTest : StringSpec({

    "a negative amount is physically meaningless" {
        checkAll(Arb.negativeLong(), MetricBuilders.generator) { magnitude, builder ->
            shouldThrow<NegativeAmountOfSubstance> { magnitude.builder().moles }
        }
    }

    "a positive amount constructs successfully" {
        checkAll(Arb.positiveLong(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().moles
                .representation shouldStartWith "${magnitude.bigDecimal} ${magnitude.builder().metric}"
        }
    }
})
