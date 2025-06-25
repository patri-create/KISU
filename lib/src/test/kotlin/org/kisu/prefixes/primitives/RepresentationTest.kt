package org.kisu.prefixes.primitives

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll

class RepresentationTest : StringSpec({

    "displays the representation as the symbol" {
        checkAll(Arb.string(1)) { symbol ->
            Representation(symbol).toString() shouldBe symbol
        }
    }
})
