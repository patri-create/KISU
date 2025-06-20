package org.kisu.prefixes.primitives

import java.math.BigDecimal

/**
 * Represents the numeric base component used in a unit prefix.
 *
 * Prefix multipliers are typically calculated as `base^power`, where `base` is defined by this interface and `power` is
 * the exponent associate with the specific prefix (e.g. 3 for kilo, -2 for centi).
 *
 * This interface allows for different base systems to be used depending on the context, such as binary (base 2),
 * decadic (base 10), or decimal (base 1000, often used for storage capacities).
 */
@Suppress("MagicNumber")
interface InBase {
    /**
     * The numeric base used for exponentiation in prefix calculations.
     *
     * For example, in the metric prefix "kilo", the base is 10 and the power is 3, meaning 1 kilo = 10^3 = 1,000 units.
     */
    val base: BigDecimal

    /**
     * Represents the binary base (2.0), commonly used in computing contexts.
     *
     * For example: `kibi` = 2^10 = 1024.
     */
    object Binary : InBase {
        override val base: BigDecimal = BigDecimal.valueOf(2)
    }

    /**
     * Represents the decadic base (10.0), used for standard SI metric prefixes.
     *
     * For example: `kilo` = 10^3 = 1000.
     */
    object Decadic : InBase {
        override val base: BigDecimal = BigDecimal.valueOf(10)
    }

    /**
     * Represents the decimal base (1000.0), often used in storage or marketing contexts.
     *
     * For example: 1 kilobyte = 1000 bytes (as opposed to 1024 in binary).
     */
    object Decimal : InBase {
        override val base: BigDecimal = BigDecimal.valueOf(1000)
    }
}
