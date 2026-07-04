package org.kisu.units.exceptions

import org.kisu.Magnitude

/**
 * Exception thrown when an information quantity does not resolve to a whole number of canonical bits.
 *
 * Fractional SI or IEC prefixed values are accepted only when conversion to unprefixed bits produces an integer amount.
 *
 * @param value The invalid fractional bit magnitude.
 * @param symbol The unit symbol (e.g., "bit").
 */
class SubBitInformation(value: Magnitude, symbol: String) : ArithmeticException(
    "Information values must be whole. Received a non-quantized value: $value $symbol",
)
