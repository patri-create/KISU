package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.of
import org.kisu.prefixes.Binary
import org.kisu.prefixes.primitives.ExponentialEnumSystem

object Binaries : Generator<Binary> {
    override val generator: Arb<Binary> = Arb.of(ExponentialEnumSystem(Binary::class).all)
}
