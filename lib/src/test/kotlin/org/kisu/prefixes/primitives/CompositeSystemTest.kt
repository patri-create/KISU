package org.kisu.prefixes.primitives

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeSorted
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.prefixes.Binary
import org.kisu.prefixes.Metric
import org.kisu.prefixes.isCanonical
import org.kisu.test.fakes.FakeCompositePrefix
import org.kisu.test.generators.Binaries
import org.kisu.test.generators.Metrics

class CompositeSystemTest : StringSpec({
    "retrieves base unit" {
        checkAll(Metrics.generator, Binaries.generator) { metric, binary ->
            val (left, right) = createSystem(metric, binary).canonical

            left.isCanonical.shouldBeTrue()
            right.isCanonical.shouldBeTrue()
        }
    }

    "retrieves all prefixes for a system" {
        checkAll(Metrics.generator, Binaries.generator) { metric, binary ->
            createSystem(metric, binary).all.forEach { (_, right) ->
                right shouldBe binary
            }
        }
    }

    "all prefixes from a system are sorted by power" {
        checkAll(Metrics.generator, Binaries.generator) { metric, binary ->
            createSystem(metric, binary).all.shouldBeSorted()
        }
    }

    "retrieves the smallest prefix" {
        checkAll(Metrics.generator, Binaries.generator) { metric, binary ->
            createSystem(metric, binary).smallest shouldBe createSystem(metric, binary).all.first()
        }
    }

    "retrieves the largest prefix" {
        checkAll(Metrics.generator, Binaries.generator) { metric, binary ->
            createSystem(metric, binary).largest shouldBe createSystem(metric, binary).all.last()
        }
    }
})

private fun createSystem(
    metric: Metric,
    binary: Binary
): CompositeSystem<FakeCompositePrefix<Metric, Binary>, Metric, Binary> = CompositeSystem(
    metric,
    binary,
    { left: Metric, right: Binary -> FakeCompositePrefix(left, right) }
)
