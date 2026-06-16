package org.kisu.test.fakes

import org.kisu.prefixes.ExponentialPrefix

class TestExponentialPrefix(
    override val power: Int,
) : ExponentialPrefix<TestExponentialPrefix> {
    override val symbol: String = "e"

    override fun toString(): String {
        return "TestExponentialPrefix(power=$power, symbol='$symbol')"
    }
}
