package org.kisu.test.fakes

import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.Representation
import org.kisu.prefixes.primitives.Symbol
import java.math.BigDecimal

class TestPrefix(override val factor: BigDecimal) : Prefix<TestPrefix> {
    override val symbol: String = "T"
    override fun toString(): String {
        return "$factor$symbol"
    }
}
