package org.kisu.prefixes

import org.kisu.Magnitude
import org.kisu.prefixes.primitives.System

/**
 * Prefix family that can scale information units such as bits and bytes.
 *
 * Decimal and binary information prefixes remain separate systems. This marker only lets information units accept
 * either family without merging their prefix lists.
 */
interface InformationPrefix<Self> : ExponentialPrefix<Self>, System<Self>
    where Self : InformationPrefix<Self> {
    /**
     * The numeric base used to resolve this prefix family's exponent coordinates.
     */
    val radix: Magnitude
}
