package org.kisu.units.exceptions

import org.kisu.prefixes.Binary
import java.math.BigDecimal

/**
 * Exception thrown when an attempt is made to create or represent
 * an information quantity with a negative value.
 *
 * Negative information quantities are invalid, as information content
 * cannot be less than zero bits.
 *
 * @param value The invalid negative magnitude provided.
 * @param prefix The binary prefix associated with the value.
 * @param symbol The unit symbol (e.g., "bit").
 */
class NegativeInformation(value: BigDecimal, prefix: Binary, symbol: String) : ArithmeticException(
    "Information quantity cannot be negative: received $value $prefix$symbol",
)
