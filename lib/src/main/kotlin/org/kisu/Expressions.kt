package org.kisu

import org.kisu.units.representation.Expression
import org.kisu.units.representation.Scalar

/**
 * Returns a list of [Scalar]s sorted by their unit.
 *
 * This is used to produce a consistent ordering of scalars for formatting,
 * comparison, or canonicalization purposes. The sorting is based on the
 * natural ordering of the [unit] property of each scalar.
 *
 * @receiver An iterable collection of [Scalar]s to be sorted.
 * @return A list of [Scalar]s ordered by unit.
 */
val Iterable<Scalar<*>>.sortByUnit: List<Scalar<*>>
    get() = sortedBy { it.unit }

/**
 * Builds a symbolic representation of the product of scalar expressions.
 *
 * The scalars are first sorted by unit using [sortByUnit] to ensure deterministic ordering.
 * Each scalar is then converted to its string representation via [Expression.toString],
 * and the results are joined with a middle dot (`·`) to form a product-like notation
 * (e.g., `"kg·m/s²"`).
 *
 * This is primarily used for generating display-friendly representations of
 * compound units or dimensional expressions.
 *
 * @receiver An iterable collection of [Scalar]s to represent symbolically.
 * @return A dot-separated string representing the product of scalars.
 */
val Iterable<Scalar<*>>.productSymbol
    get() = sortByUnit.joinToString("·", transform = Expression<*>::toString)
