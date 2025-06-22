package org.kisu.test.fakes

import org.kisu.prefixes.Decimal
import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.InBase
import org.kisu.prefixes.primitives.Representation
import org.kisu.prefixes.primitives.StandardSystem
import org.kisu.prefixes.primitives.Symbol
import org.kisu.prefixes.primitives.System

enum class InvalidSystem(
    override val power: Int,
    symbol: String
) : Prefix,
    InBase by InBase.Decimal,
    System<Decimal> by StandardSystem(Decimal::class),
    Symbol by Representation(symbol) {

    ERROR(10, "")
}
