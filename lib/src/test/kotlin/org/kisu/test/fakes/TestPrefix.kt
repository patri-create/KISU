package org.kisu.test.fakes

import org.kisu.prefixes.LinearPrefix
import java.math.BigDecimal

class TestPrefix(override val factor: BigDecimal) : LinearPrefix<TestPrefix> {
    override val symbol: String = "T"

    override fun toString(): String {
        return "$factor$symbol"
    }
}
