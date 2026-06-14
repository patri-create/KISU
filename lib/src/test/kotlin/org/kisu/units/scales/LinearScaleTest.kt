package org.kisu.units.scales

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.prefixes.Time
import org.kisu.test.generators.Times

class LinearScaleTest : StringSpec({
    "calculates a linear factor" {
        checkAll(Times.generator) { prefix ->
            LinearScale<Time>().factor(prefix) shouldBe prefix.factor
        }
    }
})
