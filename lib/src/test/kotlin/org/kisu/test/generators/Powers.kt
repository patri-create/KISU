package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.int

object Powers : Generator<Int> {
    override val generator: Arb<Int> = Arb.int(-30..30)
}
