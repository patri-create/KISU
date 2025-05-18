package org.kisu.prefixes

import org.kisu.common.isSingleUnit
import org.kisu.common.toString
import kotlin.math.pow
import kotlin.reflect.KClass

interface Prefix {
    val base: Double
    val power: Int
    val symbol: String

    fun rescale(other: Prefix): Double {
        return when {
            power == other.power -> 1.0
            power > other.power -> base.pow(power - other.power)
            else -> 1.0 / base.pow(other.power - power)
        }
    }

    fun format(meters: Double) =
        "${meters.toString(decimalPlaces = 1)} ${if (meters.isSingleUnit()) symbol.dropLast(1) else symbol}"

    fun rescale(number: Double): Double = number / base.pow(power)

    fun inBase(number: Double): Double = number * base.pow(power)

    companion object {
        fun <T : Enum<T>> allEntries(klass: KClass<out T>): List<T> {
            return klass.java.enumConstants?.toList().orEmpty()
        }
    }
}