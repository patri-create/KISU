package org.kisu.test.fakes

import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.System
import java.math.BigDecimal

class FakeCompositePrefix<A, B>(
    val a: A,
    val b: B,
) : Prefix<FakeCompositePrefix<A, B>>
    where A : Prefix<A>, A : System<A>, B : Prefix<B>, B : System<B> {

    override val factor: BigDecimal = a.factor + b.factor
    override val symbol: String = a.symbol + "-" + b.symbol
    override fun toString(): String = symbol
    operator fun component1() = a
    operator fun component2() = b

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FakeCompositePrefix<*, *>

        return symbol == other.symbol
    }

    override fun hashCode(): Int {
        return symbol.hashCode()
    }
}
