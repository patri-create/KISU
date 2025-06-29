package org.kisu.units.exceptions

import org.kisu.prefixes.Metric
import java.math.BigDecimal

/**
 * Exception thrown when an attempt is made to create or represent
 * a luminous intensity with a negative value.
 *
 * Negative luminous intensity is physically impossible, as it would imply
 * a light source emitting a negative amount of light.
 *
 * @param value The invalid negative magnitude provided.
 * @param prefix The metric prefix associated with the value.
 * @param symbol The unit symbol (e.g., "cd").
 */
class NegativeLuminousIntensity(value: BigDecimal, prefix: Metric, symbol: String) : ArithmeticException(
    "Luminous intensity cannot be negative: received $value $prefix$symbol",
)
