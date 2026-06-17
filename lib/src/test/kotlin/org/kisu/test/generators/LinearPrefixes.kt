package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.map
import org.kisu.test.fakes.TestPrefix

object LinearPrefixes : Generator<TestPrefix> {
    override val generator: Arb<TestPrefix> = Arb.magnitude().filter { !it.zero }.map(::TestPrefix)
}
