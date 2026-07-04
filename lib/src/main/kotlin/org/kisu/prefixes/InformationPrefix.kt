package org.kisu.prefixes

import org.kisu.Magnitude
import org.kisu.prefixes.primitives.System

/**
 * Prefix family that can scale information units such as bits and bytes.
 *
 * The built-in information prefix families are [Decimal], used as the SI scale with powers of 1000, and [Binary],
 * used as the IEC scale with powers of 2. They remain separate systems; this marker lets information units accept
 * either scale without merging their prefix lists.
 */
interface InformationPrefix<Self> : ExponentialPrefix<Self>, System<Self>
    where Self : InformationPrefix<Self> {
    /**
     * Numeric base used to resolve this prefix scale's exponent coordinates.
     */
    val radix: Magnitude
}
