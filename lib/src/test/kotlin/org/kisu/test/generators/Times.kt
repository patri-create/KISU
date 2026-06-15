package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.of
import org.kisu.prefixes.Time
import org.kisu.prefixes.primitives.LinearEnumSystem

object Times : Generator<Time> {
    override val generator: Arb<Time> = Arb.of(LinearEnumSystem(Time::class).all)
}
