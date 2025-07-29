package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.bind
import org.kisu.prefixes.Metric
import org.kisu.units.Scalar

object Scalars : Generator<Scalar<Metric>> {
    override val generator: Arb<Scalar<Metric>> =
        Arb.bind(Metrics.generator, Units.generator) { prefix, unit -> Scalar(prefix, unit) }
}
