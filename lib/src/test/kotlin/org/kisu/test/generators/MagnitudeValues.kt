package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.bigInt
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.next
import org.kisu.Magnitude
import org.kisu.MagnitudeConfig
import java.math.BigInteger
import java.math.MathContext

fun Arb.Companion.magnitude(
    maxDigits: Int = 10,
    minFractionalDigits: Int = 1,
    maxFractionalDigits: Int = 5,
): Arb<Magnitude> {
    return arbitrary { random ->
        val integerPart =
            Arb.bigInt(0..BigInteger.TEN.pow(maxDigits).toInt())
                .filter { it % BigInteger.TEN != BigInteger.ZERO }
                .next(random)
        val scale = Arb.int(minFractionalDigits..maxFractionalDigits).next(random)
        val sign = if (random.random.nextBoolean()) BigInteger.ONE else BigInteger.ONE.negate()

        Magnitude(BigInteger(integerPart.toString()).multiply(sign), scale)
    }
}

fun Arb.Companion.reciprocalMagnitude(): Arb<Magnitude> {
    val config = MagnitudeConfig(MathContext.UNLIMITED)
    return arbitrary { random ->
        val twos = Arb.int(0..12).next(random)
        val fives = Arb.int(0..12).next(random)
        val scale = Arb.int(0..12).next(random)
        val sign = if (random.random.nextBoolean()) BigInteger.ONE else BigInteger.ONE.negate()
        val unscaledValue =
            BigInteger.valueOf(2).pow(twos)
                .multiply(BigInteger.valueOf(5).pow(fives))
                .multiply(sign)

        Magnitude(unscaledValue, scale, config)
    }
}
