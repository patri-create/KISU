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