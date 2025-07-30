package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.element
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.take
import org.kisu.units.representation.CANONICAL_ORDER
import org.kisu.units.representation.Unit

object Units : Generator<Unit> {
    override val generator: Arb<Unit> = Arb.element(CANONICAL_ORDER)

    val symbols = generator.map { unit -> unit.toString() }

    fun distinct(number: Int): Arb<List<Unit>> {
        require(number <= CANONICAL_ORDER.size) {
            "Cannot ask for more than ${CANONICAL_ORDER.size} distinct units"
        }
        return arbitrary { CANONICAL_ORDER.shuffled(it.random).take(number) }
    }
}
