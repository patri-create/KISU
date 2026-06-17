package org.kisu.test.utils

import org.kisu.Magnitude
import org.kisu.magnitude
import org.kisu.prefixes.Binary
import org.kisu.prefixes.Decimal
import org.kisu.prefixes.LinearPrefix
import org.kisu.prefixes.Metric
import org.kisu.prefixes.Prefix
import org.kisu.prefixes.algebra.ExponentialAlgebra
import java.math.BigInteger

fun Magnitude.optimalPrefixFrom(base: Magnitude = Magnitude.TEN, original: Metric = Metric.BASE): Metric {
    return original.all
        .filter { ExponentialAlgebra<Metric>(base).factor(it) <= this }
        .maxByOrNull { it.power }!!
}

val Magnitude.magnitude: Int
    get() {
        val str = stripTrailingZeros().toBigDecimal().toPlainString()
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

val <T> List<Pair<BigInteger, T>>.magnitude: Magnitude where T : Prefix<T>
    get() = fold(Magnitude.ZERO) { magnitude, (number, prefix) ->
        magnitude + number.magnitude * prefix.concreteFactor
    }

private val Prefix<*>.concreteFactor: Magnitude
    get() =
        when (this) {
            is Binary -> ExponentialAlgebra<Binary>(2).factor(this)
            is Decimal -> ExponentialAlgebra<Decimal>().factor(this)
            is Metric -> ExponentialAlgebra<Metric>().factor(this)
            is LinearPrefix<*> -> factor
            else -> error("Unsupported prefix type: ${this::class.qualifiedName}")
        }
