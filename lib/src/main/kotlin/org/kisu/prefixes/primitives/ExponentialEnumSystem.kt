package org.kisu.prefixes.primitives

import org.kisu.prefixes.Prefix
import org.kisu.zero
import java.math.BigDecimal
import kotlin.reflect.KClass

class ExponentialEnumSystem<T : Prefix<T>>(klass: KClass<T>) : EnumSystem<T>(klass) {
    override val canonical: T by lazy {
        all.find { prefix -> prefix.factor.zero } ?: super.canonical
    }

    override fun multiply(left: T, right: T): Pair<T, BigDecimal> {
        val factor = left.factor + right.factor
        val prefix = find(factor)
        return prefix to (factor - prefix.factor)
    }

    override fun divide(left: T, right: T): Pair<T, BigDecimal> {
        val factor = left.factor - right.factor
        val prefix = find(factor)
        return prefix to (factor - prefix.factor)
    }
}
