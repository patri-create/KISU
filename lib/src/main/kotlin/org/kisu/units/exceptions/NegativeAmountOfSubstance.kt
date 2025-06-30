package org.kisu.units.exceptions

import org.kisu.prefixes.Metric
import java.math.BigDecimal

/**
 * Exception thrown when an attempt is made to create or represent
 * an amount of substance with a negative value.
 *
 * Negative amounts of substance are physically invalid, as they would
 * imply a negative quantity of entities, which is impossible.
 *
 * @param value The invalid negative magnitude provided.
 * @param prefix The metric prefix associated with the value.
 * @param symbol The unit symbol (e.g., "mol").
 */
class NegativeAmountOfSubstance(value: BigDecimal, prefix: Metric, symbol: String) : ArithmeticException(
    "An amount of substance cannot be negative: received $value $prefix$symbol",
)
