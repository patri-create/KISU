package org.kisu

import java.math.BigDecimal

/**
 * Returns `true` if this [BigDecimal] is exactly zero.
 *
 * This is determined using [compareTo] to ensure scale differences
 * (e.g., `0.0` vs `0.000`) do not affect the comparison.
 */
val BigDecimal.zero: Boolean
    get() = compareTo(BigDecimal.ZERO) == 0

/**
 * Returns `true` if this [BigDecimal] is exactly one.
 *
 * This is determined using [compareTo] to ensure scale differences
 * (e.g., `1.0` vs `1.000`) do not affect the comparison.
 */
val BigDecimal.one: Boolean
    get() = compareTo(BigDecimal.ONE) == 0

/**
 * Returns `true` if this [BigDecimal] is strictly less than zero.
 *
 * Uses [BigDecimal.signum] to determine the sign of the number.
 */
val BigDecimal.negative: Boolean
    get() = signum() == -1

/**
 * Returns `true` if this [BigDecimal] has a non-zero fractional part.
 *
 * Trailing zeros are stripped before checking the [java.math.BigDecimal.scale], so values like `5.0` or `2.000` are
 * treated as integers.
 *
 * A positive scale after stripping indicates digits exist after the decimal point.
 */
val BigDecimal.hasFraction: Boolean
    get() = stripTrailingZeros().scale() > 0

/**
 * Converts this [Number] to a [BigDecimal] instance.
 *
 * Handles common numeric types efficiently:
 * - Returns itself directly if already a [BigDecimal].
 * - Converts integral types ([Long], [Int], [Short]) using [BigDecimal.valueOf] with [toLong].
 * - Converts floating-point types ([Double], [Float]) using [BigDecimal.valueOf] with [toDouble].
 * - For other numeric types, converts to string and parses as [BigDecimal].
 *
 * This property provides a convenient and consistent way to obtain a [BigDecimal] from any [Number] subtype.
 *
 * Example:
 * ```
 * val x: Number = 42
 * val bd: BigDecimal = x.bigDecimal
 * ```
 */
val Number.bigDecimal: BigDecimal
    get() =
        when (this) {
            is BigDecimal -> this
            is Long, is Int, is Short -> BigDecimal.valueOf(this.toLong())
            is Double, is Float -> BigDecimal(this.toString())
            else -> BigDecimal(this.toString())
        }
