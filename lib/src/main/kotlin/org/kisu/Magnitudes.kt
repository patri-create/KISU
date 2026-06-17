package org.kisu

/**
 * Converts this [Number] to a [Magnitude] instance.
 *
 * This property provides a convenient and consistent way to obtain a [Magnitude] from any [Number] subtype.
 *
 * Example:
 * ```
 * val x: Number = 42
 * val magnitude: Magnitude = x.magnitude
 * ```
 */
val Number.magnitude: Magnitude
    get() = this as? Magnitude ?: Magnitude(this)
