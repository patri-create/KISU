package org.kisu.units.exceptions

import org.kisu.prefixes.Metric
import java.math.BigDecimal

/**
 * Exception thrown when an attempt is made to create or represent
 * a mass with a negative value.
 *
 * Negative mass is physically invalid, as it would imply the presence
 * of “negative matter,” which does not exist in known physics.
 *
 * @param value The invalid negative magnitude provided.
 * @param prefix The metric prefix associated with the value.
 * @param symbol The unit symbol (e.g., "g").
 */
class NegativeMass(value: BigDecimal, prefix: Metric, symbol: String) : ArithmeticException(
    "Mass cannot be negative: received $value $prefix$symbol",
)
