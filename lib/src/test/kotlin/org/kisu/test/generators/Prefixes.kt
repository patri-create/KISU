package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.map
import org.kisu.prefixes.Prefix
import org.kisu.test.fakes.TestPrefix

object Prefixes : Generator<Prefix> {
    override val generator: Arb<Prefix> = Powers.generator.map { power -> TestPrefix(power) }
}
