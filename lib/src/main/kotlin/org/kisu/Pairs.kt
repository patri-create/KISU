package org.kisu

/**
 * Returns a new [Pair] with the components of this pair swapped.
 *
 * @return A [Pair] where the first element is the original second, and the second element is the original first.
 *
 * Example:
 * ```
 * val pair = 1 to "one"
 * val inverted = pair.inverted  // "one" to 1
 * ```
 */
val <A, B> Pair<A, B>.inverted: Pair<B, A>
    get() = component2() to component1()
