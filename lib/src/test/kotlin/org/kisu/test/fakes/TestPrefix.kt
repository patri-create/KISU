package org.kisu.test.fakes

import org.kisu.Magnitude
import org.kisu.prefixes.LinearPrefix

class TestPrefix(override val factor: Magnitude) : LinearPrefix<TestPrefix> {
    override val symbol: String = "T"

    override fun toString(): String {
        return "$factor$symbol"
    }
}
