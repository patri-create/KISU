package com.kisu.decimalScale

import org.kisu.prefixes.Prefix
import com.kisu.common.utils.isSingleUnit
import com.kisu.common.utils.toString

enum class DecimalScale(override val base: Double, override val power: Int, override val symbol: String) : Prefix {
    PICO(DECIMAL_SCALE, -12, "p"),
    NANO(DECIMAL_SCALE, -9, "n"),
    MICRO(DECIMAL_SCALE, -6, "m"),
    MILLI(DECIMAL_SCALE, -3, "m"),
    CENTI(DECIMAL_SCALE, -2, "c"),
    DECI(DECIMAL_SCALE, -1, "d"),
    BASE(DECIMAL_SCALE, 0, ""),
    DECA(DECIMAL_SCALE, 1, "da"),
    HECTO(DECIMAL_SCALE, 2, "h"),
    KILO(DECIMAL_SCALE, 3, "k"),
    MEGA(DECIMAL_SCALE, 6, "M"),
    GIGA(DECIMAL_SCALE, 9, "G"),
    PETA(DECIMAL_SCALE, 12, "P");

    fun format(meters: Double) =
        "${meters.toString(decimalPlaces = 1)} ${if (meters.isSingleUnit()) symbol.dropLast(1) else symbol}"

    fun rescale(number: Double): Double = (number / base)

    fun inBase(number: Double): Double = (number * base)
}

private const val DECIMAL_SCALE = 10.0