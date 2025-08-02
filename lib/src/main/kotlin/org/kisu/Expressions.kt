package org.kisu

import org.kisu.prefixes.Prefix
import org.kisu.units.representation.Expression
import org.kisu.units.representation.Product
import org.kisu.units.representation.Quotient
import org.kisu.units.representation.Scalar

/**
 * Returns a formatted representation of the expression's symbol, wrapping it in parentheses
 * if the expression is a composite ([Product] or [Quotient]).
 *
 * This is useful for building nested unit expressions where operator precedence must be preserved.
 * For example:
 * - A scalar like `"kg"` returns `"kg"`.
 * - A product like `"kg·K"` returns `"(kg·K)"`.
 * - A quotient like `"J/(mol·K)"` returns `"(J/(mol·K))"`.
 *
 * This formatting ensures that symbols in larger compositions (e.g., `"(kg·K)/m²"`)
 * are unambiguous and human-readable.
 */
val <A : Prefix<A>> Expression<A>.compositeRepresentation: String
    get() = when (this) {
        is Product<*, *>, is Quotient<*, *> -> "($symbol)"
        is Scalar<*> -> symbol
    }
