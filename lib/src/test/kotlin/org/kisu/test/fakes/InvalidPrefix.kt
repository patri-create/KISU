package org.kisu.test.fakes

import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.EnumSystem
import org.kisu.prefixes.primitives.Representation
import org.kisu.prefixes.primitives.Symbol
import org.kisu.prefixes.primitives.System
import java.math.BigDecimal

@Suppress("DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE")
enum class InvalidPrefix(
    override val factor: BigDecimal,
    symbol: String,
) : Prefix<InvalidPrefix>,
    System<InvalidPrefix> by EnumSystem(InvalidPrefix::class),
    Symbol by Representation(symbol) {
    ERROR(BigDecimal("1000"), ""),
}
