package org.kisu.units.exceptions

import org.kisu.Magnitude
import org.kisu.prefixes.Binary

/**
 * Exception thrown when an attempt is made to create or represent
 * an information quantity with a negative value.
 *
 * Negative information quantities are invalid, as information content
 * cannot be less than zero bits.
 *
 * @param value The invalid negative magnitude provided.
 * @param prefix The IEC prefix associated with the value.
 * @param symbol The unit symbol (e.g., "bit").
 */
class NegativeInformation(value: Magnitude, prefix: Binary, symbol: String) : ArithmeticException(
    "Information quantity cannot be negative: received $value $prefix$symbol",
)
