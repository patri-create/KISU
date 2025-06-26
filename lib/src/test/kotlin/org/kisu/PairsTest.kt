package org.kisu

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.char
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.flatMap
import io.kotest.property.arbitrary.map
import io.kotest.property.checkAll

class PairsTest : StringSpec({

    val distinctChars = Arb.char().flatMap { a -> Arb.char().filter { b -> a != b }.map { b -> a to b } }

    "inverts a pair" {
        checkAll(distinctChars) { pair ->
            val (left, right) = pair
            val (invertedLeft, invertedRight) = pair.inverted
            invertedLeft shouldBe right
            invertedRight shouldBe left
        }
    }
})
