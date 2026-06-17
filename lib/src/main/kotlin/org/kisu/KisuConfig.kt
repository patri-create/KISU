package org.kisu

import org.kisu.Magnitude
import java.math.MathContext
import java.math.RoundingMode

/**
 * Process-wide numeric configuration used by KISU.
 *
 * `KisuConfig` remains the existing global entry point for precision settings. It also implements [MagnitudeConfig],
 * so decimal abstractions can use it as their default configuration while still accepting a separate
 * [MagnitudeConfig] when a caller needs per-instance arithmetic settings.
 *
 * Mutating [precision] changes the global default for operations that keep a reference to this object.
 */
object KisuConfig : MagnitudeConfig {
    /**
     * The global [MathContext] used to control precision and rounding for
     * [Magnitude] operations across the library.
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
    override var precision: MathContext = MathContext.DECIMAL128

    /**
     * Restores the global numeric settings to their defaults.
     *
     * After this call, [precision] is [MathContext.DECIMAL128].
     */
    fun default() {
        precision = MathContext.DECIMAL128
    }
}
