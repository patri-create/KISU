package org.kisu.prefixes.primitives

import org.kisu.prefixes.Prefix
import org.kisu.units.representation.Product
import org.kisu.units.representation.Quotient

/**
 * Represents a composite [System] formed by combining two underlying prefix systems.
 *
 * This class models systems for composite expressions like [Product] and [Quotient],
 * where each side (left and right) has its own prefix and system. It allows generating a full system of composite
 * expressions by applying each prefix from the left-hand side to the right-hand component.
 *
 * For example, given two scalar systems:
 * - Left: Metric prefixes with unit `"kg"`
 * - Right: Metric prefixes with unit `"m"`
 *
 * You could create a composite system for `"kg·m"` or `"kg/m"` by combining each prefix from the left
 * with the right-hand expression using the provided [create] function.
 *
 * @param T the resulting composite expression type (e.g., [Product], [Quotient]).
 * @param A the type of the left-hand component (must be a [Prefix] and provide a [System]).
 * @param B the type of the right-hand component (must be a [Prefix] and provide a [System]).
 * @property a the left-hand expression instance, serving as the source for the prefix system on the left.
 * @property b the right-hand expression instance, used as-is in all combinations.
 * @property create a factory function used to construct the resulting composite expression from a pair of components.
 */
class CompositeSystem<T : Prefix<T>, A, B>(
    private val a: A,
    private val b: B,
    private val create: (A, B) -> T
) : System<T> where A : Prefix<A>, A : System<A>, B : Prefix<B>, B : System<B> {

    override val canonical: T by lazy {
        create(a.canonical, b.canonical)
    }

    override val all: List<T> by lazy {
        a.all.map { prefix -> create(prefix, b) }.sorted()
    }

    override val smallest: T by lazy {
        create(a.smallest, b)
    }
    override val largest: T by lazy {
        create(a.largest, b)
    }
}
