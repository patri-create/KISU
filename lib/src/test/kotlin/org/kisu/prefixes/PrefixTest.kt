package org.kisu.prefixes

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeSorted
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import org.kisu.test.generators.Prefixes
import org.kisu.test.matchers.plusOrMinus
import java.math.BigDecimal

class PrefixTest : StringSpec({
    "rescales to a different power" {
        checkAll(Prefixes.generator, Prefixes.generator) { left, right ->
            val decimal = left.to(right) * right.to(left)
            decimal shouldBe (BigDecimal.ONE plusOrMinus (BigDecimal.valueOf(ALLOWED_TOLERANCE)))
        }
    }

    "order is maintained" {
        checkAll(Prefixes.generator, Prefixes.generator) { left, right ->
            left.sortWith(right).toList().shouldBeSorted()
            right.sortWith(left).toList().shouldBeSorted()
        }
    }
})

private const val ALLOWED_TOLERANCE = 1e-14
