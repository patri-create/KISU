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
