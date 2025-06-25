package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.int

object Power {
    val powers: Arb<Int> = Arb.int(-30..30)
}
