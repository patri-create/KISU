package org.kisu.units.exceptions

import org.kisu.Magnitude

/**
 * Exception thrown when an attempt is made to create or represent
 * an information quantity smaller than one bit in canonical form.
 *
 * Information quantities must be whole numbers of bits when represented
 * without a binary prefix, as fractional bits are not physically meaningful.
 *
 * @param value The invalid fractional magnitude provided.
 * @param symbol The unit symbol (e.g., "bit").
 */
class SubBitInformation(value: Magnitude, symbol: String) : ArithmeticException(
    "Information values must be whole. Received a non-quantized value: $value $symbol",
)
