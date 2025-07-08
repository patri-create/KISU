package org.kisu.test.utils

import org.kisu.bigDecimal
import org.kisu.prefixes.Metric
import org.kisu.prefixes.Prefix
import java.math.BigDecimal
import java.math.BigInteger

fun BigDecimal.optimalPrefixFrom(original: Metric = Metric.BASE): Metric {
    return original.all
        .filter { it.factor <= this }
        .maxByOrNull { it.factor }!!
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

val <T> List<Pair<BigInteger, T>>.magnitude: BigDecimal where T : Prefix<T>
    get() = fold(BigDecimal.ZERO) { magnitude, (number, prefix) ->
        magnitude + (number.bigDecimal * prefix.factor).bigDecimal
    }
