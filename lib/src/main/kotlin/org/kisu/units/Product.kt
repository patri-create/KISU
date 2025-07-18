package org.kisu.units

import org.kisu.compositeRepresentation
import org.kisu.prefixes.primitives.CompositeSystem
import org.kisu.prefixes.primitives.System
import java.math.BigDecimal

/**
 * Represents a composite [Expression] formed by the product of two other expressions,
 * such as "N·m" (newton times meter) or "kg·m²" (kilogram times square meter).
 *
 * This class models multiplicative unit relationships, combining two expressions and
 * delegating [System] behavior to a [CompositeSystem] that knows how to handle both sides.
 *
 * The resulting symbol is a dot-separated string of the left and right composite representations.
 * If either side is itself a composite (e.g., a [Product] or [Quotient]), its representation is wrapped
 * in parentheses to preserve grouping — e.g., "(kg·K)·m".
 *
 * @param A the type of the left-hand expression.
 * @param B the type of the right-hand expression.
 * @property left the left-hand component of the product.
 * @property right the right-hand component of the product.
 */
class Product<A, B>(
    private val left: A,
    private val right: B
) : Expression<Product<A, B>>(), System<Product<A, B>> by CompositeSystem(left, right, ::Product)
    where A : Expression<A>, A : System<A>, B : Expression<B>, B : System<B> {

    /** The combined factor of the product, equal to the product of the left and right factors. */
    override val factor: BigDecimal by lazy { left.factor * right.factor }

    /**
     * The combined symbol, rendered as "left·right". If either operand is a composite expression,
     * its representation will be wrapped in parentheses — e.g., "(kg·K)·m".
     */
    override val symbol: String by lazy { "${left.compositeRepresentation}·${right.compositeRepresentation}" }
}
