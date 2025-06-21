package org.kisu.prefixes

import org.kisu.common.isSingleUnit
import org.kisu.common.toString
import java.math.BigDecimal
import kotlin.reflect.KClass

interface Prefix {
    val base: BigDecimal
    val power: Int
    val symbol: String

    fun rescale(other: Prefix): BigDecimal {
        return when {
            power == other.power -> BigDecimal.ONE
            power > other.power -> base.pow(power - other.power)
            else -> BigDecimal.ONE.div(base.pow(other.power - power))
        }
    }

    fun format(meters: Double) =
        "${meters.toString(decimalPlaces = 1)} ${if (meters.isSingleUnit()) symbol.dropLast(1) else symbol}"

    fun rescale(number: Double): BigDecimal = BigDecimal.valueOf(number) / ( base.pow(power))

    fun inBase(number: Double): BigDecimal = BigDecimal.valueOf(number) * base.pow(power)

    companion object {
        fun <T : Enum<T>> allEntries(klass: KClass<out T>): List<T> {
            return klass.java.enumConstants?.toList().orEmpty()
        }
    }
}