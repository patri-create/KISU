package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.map
import org.kisu.units.representation.Exponent

object Exponents : Generator<Exponent> {
    override val generator: Arb<Exponent> = Arb.int().map(::Exponent)

    fun range(min: Int = Int.MIN_VALUE, max: Int = Int.MAX_VALUE) = range(min..max)

    fun range(range: IntRange) = Arb.int(range).map(::Exponent)
}
