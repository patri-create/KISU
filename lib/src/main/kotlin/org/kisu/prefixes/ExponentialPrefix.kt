package org.kisu.prefixes

import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.prefixes.primitives.System

/**
 * Prefix whose position in a scale is represented by an integer power.
 *
 * The [power] is not a concrete multiplier by itself. It is an exponent coordinate that an
 * [ExponentialAlgebra] resolves against a base, such as `10^3` for kilo in the metric system
 * or `2^10` for kibi in the binary system.
 */
interface ExponentialPrefix<Self : ExponentialPrefix<Self>> : Prefix<Self> {

    /**
     * Exponent coordinate for this prefix within its exponential system.
     *
     * For example, metric kilo uses power `3`, metric milli uses power `-3`, and binary kibi uses power `10`.
     */
    val power: Int

    /**
     * Compares this prefix to another exponential prefix by [power].
     *
     * @param other the other exponential prefix to compare
     * @return a negative integer if this prefix is smaller, zero if equal, or a positive integer if larger
     */
    override fun compareTo(other: Self): Int = power.compareTo(other.power)
}

operator fun <P> P.plus(other: P): Pair<P, Int> where P : ExponentialPrefix<P>, P : System<P> {
    val power = Math.addExact(this.power, other.power)
    val newPrefix = find(power.toBigDecimal())
    return newPrefix to Math.subtractExact(power, newPrefix.power)
}

operator fun <P> P.minus(other: P): Pair<P, Int> where P : ExponentialPrefix<P>, P : System<P> {
    val power = Math.subtractExact(this.power, other.power)
    val newPrefix = find(power.toBigDecimal())
    return newPrefix to Math.subtractExact(power, newPrefix.power)
}
