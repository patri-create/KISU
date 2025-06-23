package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.RandomSource
import io.kotest.property.arbitrary.of
import org.kisu.prefixes.Binary
import org.kisu.prefixes.primitives.StandardSystem

object Binary {
    val system = Arb.of(StandardSystem(Binary::class).all)

    val sample: Binary
        get() = system.sample(RandomSource.default()).value
}
