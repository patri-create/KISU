package org.kisu.test.generators

import net.jqwik.api.Arbitraries
import net.jqwik.api.Arbitrary

object Power {
    val powers: Arbitrary<Int> = Arbitraries.integers().between(-30, 30)
}
