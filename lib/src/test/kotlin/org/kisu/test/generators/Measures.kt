package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.bigDecimal
import io.kotest.property.arbitrary.bind
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.flatMap
import io.kotest.property.arbitrary.map
import org.kisu.test.fakes.TestMeasure
import org.kisu.test.generators.Measures.generator

object Measures : Generator<TestMeasure> {
    override val generator: Arb<TestMeasure> =
        Arb.bind(Arb.bigDecimal().map { it }, Metrics.generator) { magnitude, prefix ->
            TestMeasure(magnitude, prefix)
        }
}

val Arb<TestMeasure>.nonZero: Arb<TestMeasure>
    get() = filter { measure -> !measure.zero }

val Arb<TestMeasure>.distinct: Arb<Pair<TestMeasure, TestMeasure>>
    get() = flatMap { a -> generator.filter { b -> !b.zero && b != a }.map { b -> a to b } }
