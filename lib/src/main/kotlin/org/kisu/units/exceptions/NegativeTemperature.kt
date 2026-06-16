package org.kisu.units.exceptions

import org.kisu.Magnitude
import org.kisu.prefixes.Metric

/**
 * Exception thrown when an attempt is made to create or represent
 * a temperature below absolute zero.
 *
 * Temperatures below absolute zero are physically impossible, as absolute zero
 * (0 K) is the lowest limit of thermodynamic temperature.
 *
 * @param value The invalid temperature magnitude provided.
 * @param prefix The metric prefix associated with the value.
 * @param symbol The unit symbol (e.g., "K").
 */
class NegativeTemperature(value: Magnitude, prefix: Metric, symbol: String) : ArithmeticException(
    "Temperature cannot be below absolute zero: received $value $prefix$symbol",
)
