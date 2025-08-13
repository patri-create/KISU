package org.kisu

import java.math.BigInteger

/**
 * Returns `true` if this [BigInteger] is exactly zero.
 *
 * This is a convenience property equivalent to:
 * ```kotlin
 * this.compareTo(BigInteger.ZERO) == 0
 * ```
 *
 * @receiver the [BigInteger] to check
 * @return `true` if the value is zero, `false` otherwise
 */
val BigInteger.zero: Boolean
    get() = this.compareTo(BigInteger.ZERO) == 0
