package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.RandomSource

interface Generator<T> {
    val generator: Arb<T>

    val value: T
        get() = generator.sample(RandomSource.default()).value
}