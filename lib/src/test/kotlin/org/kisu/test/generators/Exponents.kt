package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.map
import org.kisu.units.Exponent

object Exponents : Generator<Exponent> {
    override val generator: Arb<Exponent> = Arb.int().map(::Exponent)

    fun range(range: IntRange) = Arb.int(range).map(::Exponent)
}
