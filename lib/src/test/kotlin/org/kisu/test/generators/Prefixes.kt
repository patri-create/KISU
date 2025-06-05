package org.kisu.test.generators

import net.jqwik.api.Arbitrary
import org.kisu.prefixes.Prefix
import org.kisu.test.fakes.TestPrefix

object Prefixes {

   val random: Arbitrary<Prefix> = Power.powers.map { power -> TestPrefix(power) }
}