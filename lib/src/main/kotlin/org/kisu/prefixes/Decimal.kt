package org.kisu.prefixes

import org.kisu.common.isSingleUnit
import org.kisu.common.pow
import org.kisu.common.toString

enum class Decimal(
    override val base: Double,
    override val power: Int,
    override val symbol: String
) : Prefix {

    BASE(DECIMAL_BASE, 0, ""),
    KILO(DECIMAL_BASE, 1, "k"),
    MEGA(DECIMAL_BASE, 2, "M"),
    GIGA(DECIMAL_BASE, 3, "G"),
    TERA(DECIMAL_BASE, 4, "T"),
    PETA(DECIMAL_BASE, 5, "P"),
    EXA(DECIMAL_BASE, 6, "E"),
    ZETTA(DECIMAL_BASE, 7, "Z"),
    YOTTA(DECIMAL_BASE, 8, "Y"),
    RONNA(DECIMAL_BASE, 9, "R"),
    QUETTA(DECIMAL_BASE, 10, "Q");

    fun format(value: Double): String =
        "${value.toString(decimalPlaces = 1)} ${if (value.isSingleUnit()) symbol.dropLast(1) else symbol}"

    fun rescale(number: Double): Double = number / base.pow(power)

    fun inBase(number: Double): Double = number * base.pow(power)
}

private const val DECIMAL_BASE = 1000.0