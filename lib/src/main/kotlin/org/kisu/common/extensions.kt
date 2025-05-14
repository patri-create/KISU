package org.kisu.common

import org.kisu.prefixes.Prefix
import java.util.*

fun Double.toString(decimalPlaces: Int) = if (this % 1.0 == 0.0) {
    toInt().toString()
} else {
    String.format(Locale.getDefault(), "%.${decimalPlaces}f", this) // Single decimal
}

fun Double.isSingleUnit(): Boolean = this == 1.0

@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
fun Prefix.toString(): String = symbol