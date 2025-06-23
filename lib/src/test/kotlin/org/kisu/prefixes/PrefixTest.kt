package org.kisu.prefixes

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeSorted
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.test.generators.Prefixes
import java.math.BigDecimal

class PrefixTest : StringSpec({
    "rescales to a different power" {
        checkAll(Prefixes.random, Prefixes.random) { left, right ->
            (left.scale(right) * right.scale(left)).compareTo(BigDecimal.ONE) shouldBe 0
        }
    }

    "order is maintained" {
        checkAll(Prefixes.random, Prefixes.random) { left, right ->
            left.sortWith(right).shouldBeSorted()
            right.sortWith(left).shouldBeSorted()
        }
    }
})
