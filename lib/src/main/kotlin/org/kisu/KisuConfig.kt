package org.kisu

import java.math.MathContext

/**
 * Configuration object for global settings related to numeric precision.
 */
object KisuConfig {
    /**
     * The global [MathContext] used to control precision and rounding for
     * [BigDecimal] operations across the library.
     *
     * Defaults to [MathContext.DECIMAL128], which provides 34 digits of precision
     * and uses [RoundingMode.HALF_EVEN].
     *
     * You can modify this property to change the precision or rounding mode globally.
     *
     * Example:
     * ```
     * KisuConfig.precision = MathContext(20, RoundingMode.HALF_UP)
     * ```
     */
    var precision: MathContext = MathContext.DECIMAL128

    fun default() {
        precision = MathContext.DECIMAL128
    }
}
