package org.kisu

import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext
import java.math.RoundingMode
import kotlin.math.absoluteValue

private const val MAX_BIG_DECIMAL_POWER = 999_999_999

/**
 * Decimal magnitude used by KISU numeric operations.
 *
 * The current JVM implementation delegates storage and arithmetic to [BigDecimal]. Arithmetic results keep the left
 * operand's `config`, which lets a custom [MagnitudeConfig] travel with an instance while preserving [KisuConfig] as
 * the default behavior.
 *
 * @param value The decimal value represented by this magnitude.
 * @param config The arithmetic settings used by this magnitude.
 */
@Suppress("TooManyFunctions")
class Magnitude(
    private val value: BigDecimal,
    internal val config: MagnitudeConfig = KisuConfig
) : Number(), Comparable<Magnitude> {
    /**
     * Creates a magnitude from an unscaled integer and decimal `scale`.
     */
    constructor(
        unscaledValue: BigInteger,
        scale: Int,
        config: MagnitudeConfig = KisuConfig
    ) : this(BigDecimal(unscaledValue, scale), config)

    /**
     * Creates a magnitude from a [BigInteger].
     */
    constructor(value: BigInteger, config: MagnitudeConfig = KisuConfig) : this(value.toBigDecimal(), config)

    /**
     * Creates a magnitude from any supported [Number].
     */
    constructor(value: Number, config: MagnitudeConfig = KisuConfig) : this(value.toBigDecimalValue(), config)

    /**
     * Creates a magnitude by parsing `value` as a [BigDecimal].
     */
    constructor(value: String, config: MagnitudeConfig = KisuConfig) : this(BigDecimal(value), config)

    /**
     * Returns `true` when this magnitude is numerically zero, ignoring scale.
     */
    val zero: Boolean
        get() = value.zero

    /**
     * Returns `true` when this magnitude is numerically one, ignoring scale.
     */
    val one: Boolean
        get() = value.one

    /**
     * Returns `true` when this magnitude is less than zero.
     */
    val negative: Boolean
        get() = value.negative

    /**
     * Returns `true` when this magnitude has a non-zero fractional part.
     */
    val hasFraction: Boolean
        get() = value.hasFraction

    /**
     * Returns `true` when this magnitude represents an integer value.
     */
    val integer: Boolean
        get() = !hasFraction

    /**
     * Returns the absolute value while keeping this magnitude's `config`.
     */
    val abs: Magnitude
        get() = Magnitude(value.abs(), config)

    /**
     * Returns this magnitude with the sign inverted.
     */
    val negated: Magnitude
        get() = -this

    /**
     * Returns the sign of this magnitude.
     */
    val signum: Signum
        get() =
            when (value.signum()) {
                -1 -> Signum.NEGATIVE
                0 -> Signum.ZERO
                else -> Signum.POSITIVE
            }

    /**
     * Returns the decimal scale of this magnitude.
     */
    val scale: Int
        get() = value.scale()

    /**
     * Returns the reciprocal magnitude, `1 / this`, using this magnitude's arithmetic `config`.
     *
     * @throws ArithmeticException when this magnitude is zero.
     */
    val inverted: Magnitude
        get() = Magnitude(BigDecimal.ONE.divide(value, config.precision), config)

    /**
     * Adds [other] and keeps this magnitude's `config`.
     */
    operator fun plus(other: Magnitude): Magnitude =
        Magnitude(value + other.value, config)

    /**
     * Subtracts [other] and keeps this magnitude's `config`.
     */
    operator fun minus(other: Magnitude): Magnitude =
        Magnitude(value - other.value, config)

    /**
     * Multiplies by [other] and keeps this magnitude's `config`.
     */
    operator fun times(other: Magnitude): Magnitude =
        Magnitude(value * other.value, config)

    /**
     * Divides by [other] using [scale] and [roundingMode].
     */
    fun divide(
        other: Magnitude,
        scale: Int,
        roundingMode: RoundingMode
    ): Magnitude =
        Magnitude(value.divide(other.value, scale, roundingMode), config)

    /**
     * Divides by [other] using [precision].
     */
    fun divide(other: Magnitude, precision: MathContext): Magnitude =
        Magnitude(value.divide(other.value, precision), config)

    /**
     * Divides by [other] using this magnitude's [MagnitudeConfig.precision].
     */
    operator fun div(other: Magnitude): Magnitude =
        Magnitude(value.divide(other.value, config.precision), config)

    /**
     * Returns this magnitude raised to [exponent].
     *
     * Positive exponents delegate to the underlying decimal implementation. Negative exponents are resolved as
     * `1 / this.pow(abs(exponent))` using this magnitude's [MagnitudeConfig.precision].
     *
     * @throws IllegalArgumentException when the absolute exponent is too large for the decimal backend.
     * @throws ArithmeticException when a negative exponent requires division by zero.
     */
    fun pow(exponent: Int): Magnitude {
        val magnitude = exponent.toLong().absoluteValue
        require(magnitude <= MAX_BIG_DECIMAL_POWER) {
            "Power magnitude is too large: $exponent"
        }

        val factor = value.pow(magnitude.toInt())
        return if (exponent < 0) {
            Magnitude(BigDecimal.ONE.divide(factor, config.precision), config)
        } else {
            Magnitude(factor, config)
        }
    }

    /**
     * Returns this magnitude with the sign inverted.
     */
    operator fun unaryMinus(): Magnitude = Magnitude(value.negate(), config)

    /**
     * Returns a numerically equivalent magnitude with insignificant trailing zeros removed.
     */
    fun stripTrailingZeros(): Magnitude = Magnitude(value.stripTrailingZeros(), config)

    /**
     * Converts this magnitude to a [Byte] using the delegated decimal conversion.
     */
    override fun toByte(): Byte = value.toByte()

    /**
     * Converts this magnitude to a [Char] using the delegated decimal conversion.
     */
    @Deprecated(
        message = "Direct conversion to Char is deprecated. Convert to Int explicitly before converting to Char.",
        replaceWith = ReplaceWith("toInt().toChar()")
    )
    override fun toChar(): Char = value.toInt().toChar()

    /**
     * Converts this magnitude to a [Double] using the delegated decimal conversion.
     */
    override fun toDouble(): Double = value.toDouble()

    /**
     * Converts this magnitude to a [Float] using the delegated decimal conversion.
     */
    override fun toFloat(): Float = value.toFloat()

    /**
     * Converts this magnitude to an [Int] using the delegated decimal conversion.
     */
    override fun toInt(): Int = value.toInt()

    /**
     * Converts this magnitude to a [Long] using the delegated decimal conversion.
     */
    override fun toLong(): Long = value.toLong()

    /**
     * Converts this magnitude to a [Short] using the delegated decimal conversion.
     */
    override fun toShort(): Short = value.toShort()

    /**
     * Returns the delegated [BigDecimal] value.
     */
    fun toBigDecimal(): BigDecimal = value

    /**
     * Compares magnitudes numerically, ignoring [BigDecimal] scale.
     */
    override fun compareTo(other: Magnitude): Int = value.compareTo(other.value)

    /**
     * Returns `true` when [other] is a [Magnitude] with the same numeric value.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Magnitude) return false

        return compareTo(other) == 0
    }

    /**
     * Returns a hash code consistent with numeric equality.
     */
    override fun hashCode(): Int = value.stripTrailingZeros().hashCode()

    /**
     * Returns the delegated [BigDecimal] string representation.
     */
    override fun toString(): String = value.toString()

    /**
     * Sign categories for a [Magnitude].
     */
    enum class Signum {
        /**
         * The magnitude is less than zero.
         */
        NEGATIVE,

        /**
         * The magnitude is numerically zero.
         */
        ZERO,

        /**
         * The magnitude is greater than zero.
         */
        POSITIVE
    }

    companion object {
        /**
         * Numeric zero.
         */
        val ZERO: Magnitude = Magnitude(BigDecimal.ZERO)

        /**
         * Numeric one.
         */
        val ONE: Magnitude = Magnitude(BigDecimal.ONE)

        /**
         * Numeric ten.
         */
        val TEN: Magnitude = Magnitude(BigDecimal.TEN)

        /**
         * Numeric two.
         */
        val TWO: Magnitude = Magnitude(BigDecimal.TWO)

        /**
         * Creates a magnitude from a [Long] using the same semantics as [BigDecimal.valueOf].
         */
        fun valueOf(value: Long, config: MagnitudeConfig = KisuConfig): Magnitude =
            Magnitude(BigDecimal.valueOf(value), config)

        /**
         * Creates a magnitude from a [Double] using the same semantics as [BigDecimal.valueOf].
         */
        fun valueOf(value: Double, config: MagnitudeConfig = KisuConfig): Magnitude =
            Magnitude(BigDecimal.valueOf(value), config)
    }
}

private val BigDecimal.zero: Boolean
    get() = compareTo(BigDecimal.ZERO) == 0

private val BigDecimal.one: Boolean
    get() = compareTo(BigDecimal.ONE) == 0

private val BigDecimal.negative: Boolean
    get() = signum() == -1

private val BigDecimal.hasFraction: Boolean
    get() = stripTrailingZeros().scale() > 0

private fun Number.toBigDecimalValue(): BigDecimal =
    when (this) {
        is Magnitude -> toBigDecimal()
        is BigDecimal -> this
        is BigInteger -> toBigDecimal()
        is Long, is Int, is Short, is Byte -> BigDecimal.valueOf(toLong())
        is Double, is Float -> BigDecimal(toString())
        else -> BigDecimal(toString())
    }
