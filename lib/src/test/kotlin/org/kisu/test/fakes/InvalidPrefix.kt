package org.kisu.test.fakes

import org.kisu.prefixes.LinearPrefix
import org.kisu.prefixes.primitives.LinearEnumSystem
import org.kisu.prefixes.primitives.Representation
import org.kisu.prefixes.primitives.Symbol
import org.kisu.prefixes.primitives.System
import java.math.BigDecimal

@Suppress("DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE")
enum class InvalidPrefix(
    override val factor: BigDecimal,
    symbol: String,
) : LinearPrefix<InvalidPrefix>,
    System<InvalidPrefix> by LinearEnumSystem(InvalidPrefix::class),
    Symbol by Representation(symbol) {
    ERROR(BigDecimal("1000"), ""),
}
