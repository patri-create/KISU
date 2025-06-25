package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.map
import org.kisu.prefixes.Prefix
import org.kisu.test.fakes.TestPrefix

object Prefixes {
    val random: Arb<Prefix> = Power.powers.map { power -> TestPrefix(power) }
}
