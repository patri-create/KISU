package org.kisu.test.fakes

import org.kisu.prefixes.ExponentialPrefix
import org.kisu.prefixes.LinearPrefix
import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.System
import java.math.BigDecimal

class FakeCompositePrefix<A, B>(
    val a: A,
    val b: B,
) : LinearPrefix<FakeCompositePrefix<A, B>>
    where A : Prefix<A>, A : System<A>, B : Prefix<B>, B : System<B> {

    override val factor: BigDecimal = a.coordinate + b.coordinate
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

private val Prefix<*>.coordinate: BigDecimal
    get() =
        when (this) {
            is ExponentialPrefix<*> -> power.toBigDecimal()
            is LinearPrefix<*> -> factor
            else -> error("Unsupported prefix type: ${this::class.qualifiedName}")
        }
