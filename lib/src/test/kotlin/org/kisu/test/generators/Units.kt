package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.char
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.flatMap
import io.kotest.property.arbitrary.map
import org.kisu.units.representation.Unit

object Units : Generator<Unit> {
    val symbols = Arb.char('a'..'z').map { it.toString() }

    override val generator: Arb<Unit> = symbols.map { symbol -> Unit(symbol, 1) }

    val distinct: Arb<Pair<Unit, Unit>> = generator.flatMap { left ->
        generator.filter { right -> right != left }.map { right -> left to right }
    }
}
