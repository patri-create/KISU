package org.kisu.prefixes.primitives

import io.kotest.matchers.shouldBe
import net.jqwik.api.Arbitraries
import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.Provide

class RepresentationTest {

    @Property
    fun `displays the representation as the symbol`(@ForAll("symbols") symbol: String) {
        Representation(symbol).toString() shouldBe symbol
    }

    @Provide
    fun symbols() = Arbitraries.chars().map { symbol -> symbol.toString() }
}
