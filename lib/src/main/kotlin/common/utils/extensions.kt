package com.kisu.common.utils

import com.kisu.common.Prefix
import java.util.*

fun Double.pow(exp: Int): Double {

    fun calculate(base: Double, exp: Int): Double {
        var b = base
        var e = exp
        var result = 1.0

        while (e > 0) {
            if (e % 2 == 1) {
                result *= b
            }
            b *= b
            e /= 2
        }
        return result
    }

    return when {
        exp == 0 -> 1.0
        exp > 0 -> calculate(this, exp)
        else -> 1 / calculate(this, -exp)
    }
}

fun Double.toString(decimalPlaces: Int) = if (this % 1.0 == 0.0) {
    toInt().toString()
} else {
    String.format(Locale.getDefault(), "%.${decimalPlaces}f", this) // Single decimal
}

fun Double.isSingleUnit(): Boolean = this == 1.0

@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
fun Prefix.toString(): String = symbol