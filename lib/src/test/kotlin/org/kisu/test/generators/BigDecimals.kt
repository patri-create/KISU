package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.bigInt
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.next
import java.math.BigDecimal
import java.math.BigInteger

fun Arb.Companion.bigDecimal(
    maxDigits: Int = 10,
    minFractionalDigits: Int = 1,
    maxFractionalDigits: Int = 5,
): Arb<BigDecimal> {
    return arbitrary { random ->
        val integerPart =
            Arb.bigInt(0..BigInteger.TEN.pow(maxDigits).toInt())
                .filter { it % BigInteger.TEN != BigInteger.ZERO }
                .next(random)
        val scale = Arb.int(minFractionalDigits..maxFractionalDigits).next(random)
        val sign = if (random.random.nextBoolean()) BigDecimal.ONE else BigDecimal.ONE.negate()

        BigDecimal(BigInteger(integerPart.toString()), scale).multiply(sign)
    }
}
