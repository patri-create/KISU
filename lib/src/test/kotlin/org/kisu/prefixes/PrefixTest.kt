package org.kisu.prefixes

import io.kotest.matchers.collections.shouldBeSorted
import io.kotest.matchers.shouldBe
import net.jqwik.api.Arbitrary
import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.Provide
import org.kisu.test.generators.Prefixes
import java.math.BigDecimal

class PrefixTest {

    @Property
    fun `rescales to a different power`(@ForAll("prefixes") left: Prefix, @ForAll("prefixes") right: Prefix) {
        (left.scale(right) * right.scale(left)).compareTo(BigDecimal.ONE) shouldBe 0
    }

    @Property
    fun `order is maintained`(@ForAll("prefixes") left: Prefix, @ForAll("prefixes") right: Prefix) {
        left.sortWith(right).shouldBeSorted()
        right.sortWith(left).shouldBeSorted()
    }

    @Provide
    private fun prefixes(): Arbitrary<Prefix> = Prefixes.random
}
