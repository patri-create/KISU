package org.kisu.prefixes

import org.kisu.common.isSingleUnit
import org.kisu.common.pow
import org.kisu.common.toString

enum class Binary(
    override val base: Double,
    override val power: Int,
    override val symbol: String
) : Prefix {

    BASE(BINARY_BASE, 0, ""),
    KIBI(BINARY_BASE, 10, "Ki"),
    KILO(BINARY_BASE, 10, "K"),
    MEBI(BINARY_BASE, 20, "Mi"),
    MEGA(BINARY_BASE, 20, "M"),
    GIBI(BINARY_BASE, 30, "Gi"),
    GIGA(BINARY_BASE, 30, "G"),
    TEBI(BINARY_BASE, 40, "Ti"),
    PEBI(BINARY_BASE, 50, "Pi"),
    EXBI(BINARY_BASE, 60, "Ei"),
    ZEBI(BINARY_BASE, 70, "Zi"),
    YOBI(BINARY_BASE, 80, "Yi"),
    ROBI(BINARY_BASE, 90, "Ri"),
    QUEBI(BINARY_BASE, 100, "Qi");

    fun format(value: Double): String {
        return "${value.toString(decimalPlaces = 1)} " +
                if (value.isSingleUnit()) symbol.dropLast(1) else symbol
    }

    fun rescale(number: Double): Double = number / base.pow(power)

    fun inBase(number: Double): Double = number * base.pow(power)
}

private const val BINARY_BASE = 2.0