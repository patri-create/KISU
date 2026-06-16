package org.kisu

import java.math.MathContext

/**
 * Arithmetic settings used by decimal magnitude implementations.
 *
 * This contract is intentionally small: KISU currently needs a [MathContext] for operations that may round, and future
 * decimal abstractions can depend on this interface instead of the mutable global [KisuConfig] object.
 *
 * @property precision The [MathContext] used by operations that need rounding, such as division.
 */
interface MagnitudeConfig {
    /**
     * Precision and rounding rules for operations that cannot be represented exactly.
     */
    val precision: MathContext

    /**
     * Factory for immutable [MagnitudeConfig] instances.
     */
    companion object {
        /**
         * Creates a configuration using [precision].
         *
         * When no value is provided, the current [KisuConfig.precision] is captured.
         */
        operator fun invoke(
            precision: MathContext = KisuConfig.precision,
        ) = object : MagnitudeConfig {
            override val precision: MathContext = precision
        }
    }
}
