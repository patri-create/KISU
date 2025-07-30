package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.bind
import org.kisu.prefixes.Metric
import org.kisu.units.representation.Scalar

object Scalars : Generator<Scalar<Metric>> {
    override val generator: Arb<Scalar<Metric>> =
        Arb.bind(Metrics.generator, Units.generator) { prefix, unit -> Scalar(prefix, unit = unit) }

    fun distinct(number: Int): Arb<List<Scalar<Metric>>> = arbitrary { rs ->
        Units.distinct(number).sample(rs).value.map { unit ->
            Scalar(Metrics.generator.sample(rs).value, unit = unit)
        }
    }
}
