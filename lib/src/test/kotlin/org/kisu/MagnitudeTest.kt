package org.kisu

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.double
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.long
import io.kotest.property.arbitrary.map
import io.kotest.property.checkAll
import org.kisu.test.generators.magnitude
import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext
import java.math.RoundingMode

class MagnitudeTest : StringSpec({
    "provides common decimal constants" {
        Magnitude.ZERO.toBigDecimal() shouldBe BigDecimal.ZERO
        Magnitude.ONE.toBigDecimal() shouldBe BigDecimal.ONE
        Magnitude.TEN.toBigDecimal() shouldBe BigDecimal.TEN
    }

    "keeps the delegated decimal value" {
        checkAll(Arb.bigDecimal()) { number ->
            Magnitude(number).toBigDecimal() shouldBe number
        }
    }

    "parses string values like BigDecimal" {
        checkAll(Arb.bigDecimal()) { number ->
            Magnitude(number.toString()).toBigDecimal() shouldBe BigDecimal(number.toString())
        }
    }

    "creates scaled integer values like BigDecimal" {
        checkAll(Arb.int(), Arb.int(range = 0..30)) { unscaledValue, scale ->
            Magnitude(BigInteger.valueOf(unscaledValue.toLong()), scale).toBigDecimal() shouldBe
                BigDecimal(BigInteger.valueOf(unscaledValue.toLong()), scale)
        }
    }

    "creates long values like BigDecimal valueOf" {
        checkAll(Arb.long()) { number ->
            Magnitude.valueOf(number).toBigDecimal() shouldBe BigDecimal.valueOf(number)
        }
    }

    "creates double values like BigDecimal valueOf" {
        checkAll(Arb.double().filter { number -> number.isFinite() && !number.isNaN() }) { number ->
            Magnitude.valueOf(number).toBigDecimal() shouldBe BigDecimal.valueOf(number)
        }
    }

    "uses KisuConfig as the default arithmetic config" {
        checkAll(Arb.bigDecimal()) { number ->
            Magnitude(number).config shouldBe KisuConfig
        }
    }

    "accepts a custom arithmetic config per instance" {
        checkAll(Arb.bigDecimal(), Arb.int(range = 1..34)) { number, precision ->
            val config = MagnitudeConfig(MathContext(precision, RoundingMode.DOWN))

            Magnitude(number, config).config shouldBe config
        }
    }

    "detects zero values independent of scale" {
        checkAll(
            Arb.int(range = 0..40)
                .map { scale -> BigDecimal(BigInteger.ZERO, scale) },
        ) { number ->
            Magnitude(number).zero.shouldBeTrue()
        }
    }

    "detects non-zero values" {
        checkAll(Arb.bigDecimal().filter { number -> number.compareTo(BigDecimal.ZERO) != 0 }) { number ->
            Magnitude(number).zero.shouldBeFalse()
        }
    }

    "detects one values independent of scale" {
        checkAll(
            Arb.int(range = 0..40)
                .map { scale -> BigDecimal.ONE.setScale(scale) },
        ) { number ->
            Magnitude(number).one.shouldBeTrue()
        }
    }

    "detects values that are not one" {
        checkAll(Arb.bigDecimal().filter { number -> number.compareTo(BigDecimal.ONE) != 0 }) { number ->
            Magnitude(number).one.shouldBeFalse()
        }
    }

    "detects negative values" {
        checkAll(Arb.bigDecimal().filter { number -> number.signum() == -1 }) { number ->
            Magnitude(number).negative.shouldBeTrue()
        }
    }

    "detects positive and zero values as not negative" {
        checkAll(Arb.bigDecimal().filter { number -> number.signum() != -1 }) { number ->
            Magnitude(number).negative.shouldBeFalse()
        }
    }

    "detects fractional values" {
        checkAll(Arb.bigDecimal(minFractionalDigits = 1, maxDigits = 5)) { number ->
            Magnitude(number).hasFraction.shouldBeTrue()
        }
    }

    "detects integer values" {
        checkAll(Arb.int().map { number -> BigDecimal.valueOf(number.toLong()) }) { number ->
            Magnitude(number).integer.shouldBeTrue()
            Magnitude(number).hasFraction.shouldBeFalse()
        }
    }

    "adds by delegating to BigDecimal" {
        checkAll(Arb.bigDecimal(), Arb.bigDecimal()) { left, right ->
            (Magnitude(left) + Magnitude(right)).toBigDecimal() shouldBe left + right
        }
    }

    "subtracts by delegating to BigDecimal" {
        checkAll(Arb.bigDecimal(), Arb.bigDecimal()) { left, right ->
            (Magnitude(left) - Magnitude(right)).toBigDecimal() shouldBe left - right
        }
    }

    "multiplies by delegating to BigDecimal" {
        checkAll(Arb.bigDecimal(), Arb.bigDecimal()) { left, right ->
            (Magnitude(left) * Magnitude(right)).toBigDecimal() shouldBe left * right
        }
    }

    "divides with the instance arithmetic config" {
        checkAll(
            Arb.bigDecimal().filter { number -> number.compareTo(BigDecimal.ZERO) != 0 },
            Arb.int(range = 1..34),
        ) { divisor, precision ->
            val config = MagnitudeConfig(MathContext(precision, RoundingMode.DOWN))

            (Magnitude(BigDecimal.ONE, config) / Magnitude(divisor)).toBigDecimal() shouldBe
                BigDecimal.ONE.divide(divisor, config.precision)
        }
    }

    "divides with scale and rounding mode" {
        checkAll(
            Arb.bigDecimal().filter { number -> number.compareTo(BigDecimal.ZERO) != 0 },
            Arb.int(range = 0..30),
        ) { divisor, scale ->
            Magnitude(BigDecimal.ONE).divide(Magnitude(divisor), scale, RoundingMode.DOWN).toBigDecimal() shouldBe
                BigDecimal.ONE.divide(divisor, scale, RoundingMode.DOWN)
        }
    }

    "raises values to integer powers like BigDecimal" {
        checkAll(Arb.int(range = -100..100), Arb.int(range = 0..12)) { number, exponent ->
            val base = BigDecimal.valueOf(number.toLong())
            Magnitude(base).pow(exponent).toBigDecimal() shouldBe base.pow(exponent)
        }
    }

    "raises values to negative integer powers with configured precision" {
        checkAll(
            Arb.int(range = -100..100).filter { number -> number != 0 },
            Arb.int(range = -12..-1),
            Arb.int(range = 1..34),
        ) { number, exponent, precision ->
            val config = MagnitudeConfig(MathContext(precision, RoundingMode.DOWN))
            val base = BigDecimal.valueOf(number.toLong())

            Magnitude(base, config).pow(exponent).toBigDecimal() shouldBe
                BigDecimal.ONE.divide(base.pow(-exponent), config.precision)
        }
    }

    "inverts values with configured precision" {
        checkAll(
            Arb.int(range = -100..100).filter { number -> number != 0 },
            Arb.int(range = 1..34),
        ) { number, precision ->
            val config = MagnitudeConfig(MathContext(precision, RoundingMode.DOWN))
            val value = BigDecimal.valueOf(number.toLong())

            Magnitude(value, config).inverted.toBigDecimal() shouldBe BigDecimal.ONE.divide(value, config.precision)
            Magnitude(value, config).inverted.config shouldBe config
        }
    }

    "fails when inverting zero" {
        shouldThrow<ArithmeticException> {
            Magnitude.ZERO.inverted
        }
    }

    "fails when raising zero to a negative exponent" {
        checkAll(Arb.int(range = -12..-1)) { exponent ->
            shouldThrow<ArithmeticException> {
                Magnitude.ZERO.pow(exponent)
            }
        }
    }

    "negates values like BigDecimal" {
        checkAll(Arb.bigDecimal()) { number ->
            (-Magnitude(number)).toBigDecimal() shouldBe number.negate()
            Magnitude(number).negated.toBigDecimal() shouldBe number.negate()
        }
    }

    "returns scale like BigDecimal" {
        checkAll(Arb.bigDecimal()) { number ->
            Magnitude(number).scale shouldBe number.scale()
        }
    }

    "returns sign categories from BigDecimal sign" {
        checkAll(Arb.bigDecimal()) { number ->
            Magnitude(number).signum shouldBe
                when (number.signum()) {
                    -1 -> Magnitude.Signum.NEGATIVE
                    0 -> Magnitude.Signum.ZERO
                    else -> Magnitude.Signum.POSITIVE
                }
        }
    }

    "returns absolute values like BigDecimal" {
        checkAll(Arb.bigDecimal()) { number ->
            Magnitude(number).abs.toBigDecimal() shouldBe number.abs()
        }
    }

    "arithmetic results keep the left operand config" {
        checkAll(Arb.bigDecimal(), Arb.bigDecimal(), Arb.int(range = 1..34)) { left, right, precision ->
            val leftConfig = MagnitudeConfig(MathContext(precision, RoundingMode.HALF_UP))
            val rightConfig = MagnitudeConfig(MathContext(precision, RoundingMode.DOWN))

            (Magnitude(left, leftConfig) + Magnitude(right, rightConfig)).config shouldBe leftConfig
            (Magnitude(left, leftConfig) - Magnitude(right, rightConfig)).config shouldBe leftConfig
            (Magnitude(left, leftConfig) * Magnitude(right, rightConfig)).config shouldBe leftConfig
        }
    }

    "compares by numeric value independent of scale" {
        checkAll(Arb.bigDecimal(), Arb.bigDecimal()) { left, right ->
            Magnitude(left).compareTo(Magnitude(right)) shouldBe left.compareTo(right)
        }
    }

    "equality ignores decimal scale" {
        checkAll(Arb.int(range = -1_000_000..1_000_000), Arb.int(range = 0..20)) { value, scale ->
            val number = BigDecimal.valueOf(value.toLong())
            Magnitude(number) shouldBe Magnitude(number.setScale(scale))
        }
    }

    "equal numeric values have the same hash code" {
        checkAll(Arb.int(range = -1_000_000..1_000_000), Arb.int(range = 0..20)) { value, scale ->
            val number = BigDecimal.valueOf(value.toLong())
            Magnitude(number).hashCode() shouldBe Magnitude(number.setScale(scale)).hashCode()
        }
    }
})

private fun Arb.Companion.bigDecimal(
    maxDigits: Int = 10,
    minFractionalDigits: Int = 1,
    maxFractionalDigits: Int = 5,
) = magnitude(maxDigits, minFractionalDigits, maxFractionalDigits).map(Magnitude::toBigDecimal)
