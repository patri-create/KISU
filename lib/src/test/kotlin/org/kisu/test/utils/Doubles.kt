package org.kisu.test.utils

import org.kisu.prefixes.Metric
import java.math.BigDecimal

fun BigDecimal.optimalPrefixFrom(original: Metric = Metric.BASE): Metric {
    return original.all.findLast { prefix -> prefix.power <= original.power + magnitude }!!
}

val BigDecimal.magnitude: Int
    get() {
        val str = stripTrailingZeros().toPlainString()
        return if (str.contains('.')) {
            val parts = str.split('.')
            val intPart = parts[0]
            val decPart = parts[1]

            if (intPart != "0" && intPart.isNotEmpty()) {
                // Number >= 1, order = length of intPart - 1
                intPart.length - 1
            } else {
                // Number < 1, count leading zeros in decimal part
                val leadingZeros = decPart.takeWhile { it == '0' }.length
                -(leadingZeros + 1)
            }
        } else {
            // Integer without decimal point
            str.length - 1
        }
    }
