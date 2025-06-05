package org.kisu.test.generators

import net.jqwik.api.Arbitraries
import net.jqwik.api.Arbitrary
import org.kisu.prefixes.Binary
import org.kisu.prefixes.primitives.StandardSystem

object Binary {
    val system: Arbitrary<Binary> = Arbitraries.of(StandardSystem(Binary::class).all)

    fun sample(): Binary = system.sample()
}
