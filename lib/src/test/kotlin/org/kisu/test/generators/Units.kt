package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.char
import io.kotest.property.arbitrary.map

object Units : Generator<String> {
    override val generator: Arb<String> = Arb.char('a'..'z').map { it.toString() }
}
