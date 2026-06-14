package org.kisu.prefixes.primitives

import org.kisu.KisuConfig
import org.kisu.prefixes.Prefix
import org.kisu.zero
import java.math.BigDecimal
import kotlin.reflect.KClass

class ExponentialEnumSystem<T : Prefix<T>> : EnumSystem<T> {
    private val base: BigDecimal

    constructor(klass: KClass<T>) : this(klass, BigDecimal.TEN)

    constructor(klass: KClass<T>, base: Int) : this(klass, BigDecimal.valueOf(base.toLong()))

    constructor(klass: KClass<T>, base: BigDecimal) : super(klass) {
        this.base = base
    }

    override val canonical: T by lazy {
        all.find { prefix -> prefix.factor.zero } ?: super.canonical
    }

    override fun multiply(left: T, right: T): Pair<T, BigDecimal> {
        val factor = left.factor + right.factor
        val prefix = find(factor)
        return prefix to multiplicativeRemainder(factor, prefix)
    }

    override fun divide(left: T, right: T): Pair<T, BigDecimal> {
        val factor = left.factor - right.factor
        val prefix = find(factor)
        return prefix to multiplicativeRemainder(factor, prefix)
    }

    private fun multiplicativeRemainder(
        factor: BigDecimal,
        prefix: T,
    ): BigDecimal {
        val delta = factor - prefix.factor
        val power = base.pow(delta.abs().intValueExact())

        return if (delta < BigDecimal.ZERO) {
            BigDecimal.ONE.divide(power, KisuConfig.precision)
        } else {
            power
        }
    }
}
