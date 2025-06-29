package org.kisu.units.exceptions

import org.kisu.prefixes.Metric
import java.math.BigDecimal

/**
 * Exception thrown when an attempt is made to create or represent
 * a time duration with a negative value.
 *
 * Negative time durations are invalid because time intervals
 * cannot be less than zero — negative durations have no physical meaning.
 *
 * @param value The invalid negative magnitude provided.
 * @param prefix The metric prefix associated with the value.
 * @param symbol The unit symbol (e.g., "s").
 */
class NegativeTime(value: BigDecimal, prefix: Metric, symbol: String) : ArithmeticException(
    "Time duration cannot be negative: received $value $prefix$symbol",
)
