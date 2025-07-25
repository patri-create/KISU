package org.kisu.prefixes.primitives

import org.kisu.prefixes.Prefix
import org.kisu.units.Scalar

/**
 * Represents a system of [Scalar] units derived from a given prefix system and unit symbol.
 *
 * This class adapts a [System] of prefixes (e.g., [Metric], [Binary]) into a system of full [Scalar] expressions,
 * by pairing each prefix with a specific unit (e.g., `"m"` for meters, `"B"` for bytes).
 *
 * It provides the canonical, smallest, largest, and full list of all scalar variants derived from the prefix system.
 * For example, if the prefix system is Metric and the unit is `"m"`, this system will generate:
 * - `"nm"` (nano meter)
 * - `"μm"` (micro meter)
 * - `"m"` (meter, canonical)
 * - `"km"` (kilometer)
 * - etc.
 *
 * @param A the type of prefix used in the system, which must also provide a [System] implementation.
 * @property prefix the base prefix instance (typically representing the current scalar).
 * @property unit the unit symbol to which all prefixes are applied (e.g., "m", "B", "J").
 */
class ScalarSystem<A>(private val prefix: A, private val unit: String) :
    System<Scalar<A>> where A : Prefix<A>, A : System<A> {
    override val canonical: Scalar<A> by lazy {
        Scalar(prefix.canonical, unit)
    }
    override val all: List<Scalar<A>> by lazy {
        prefix.all.map { prefix -> Scalar(prefix, unit) }
    }

    override val smallest: Scalar<A> by lazy {
        Scalar(prefix.smallest, unit)
    }
    override val largest: Scalar<A> by lazy {
        Scalar(prefix.largest, unit)
    }
}
