package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.map
import org.kisu.test.fakes.TestExponentialPrefix

object ExponentialPrefixes : Generator<TestExponentialPrefix> {
    override val generator: Arb<TestExponentialPrefix> = Arb.int().map(::TestExponentialPrefix)
}
