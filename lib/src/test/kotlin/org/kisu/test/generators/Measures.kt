package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.bigDecimal
import io.kotest.property.arbitrary.bind
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.flatMap
import io.kotest.property.arbitrary.map
import org.kisu.test.fakes.TestUnit
import org.kisu.test.generators.Measures.generator

object Measures : Generator<TestUnit> {
    override val generator: Arb<TestUnit> = Arb.bind(Arb.bigDecimal().map { it }, Metrics.generator) { magnitude, prefix ->
        TestUnit(magnitude, prefix)
    }
}

val Arb<TestUnit>.nonZero: Arb<TestUnit>
    get() = filter { measure -> !measure.zero }

val Arb<TestUnit>.distinct: Arb<Pair<TestUnit, TestUnit>>
    get() = flatMap { a -> generator.filter { b -> !b.zero && b != a }.map { b -> a to b } }