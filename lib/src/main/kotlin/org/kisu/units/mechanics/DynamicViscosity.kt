@file:Suppress("TooManyFunctions")

package org.kisu.units.mechanics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.mechanics.DynamicViscosity.Companion.PascalSecond
import org.kisu.units.representation.Product
import org.kisu.units.special.Pascal

/**
 * Represents the physical quantity of **dynamic viscosity**, measured in
 * [PascalSecond].
 *
 * Dynamic viscosity quantifies a fluid's internal resistance to shear and flow. It is
 * the quantity used when relating shear stress to velocity gradients in continuum
 * mechanics.
 *
 * Typical examples include comparing oils, gases, polymer melts, and process fluids.
 *
 * The associated unit representation is [PascalSecond] (`Pa·s`).
 */
class DynamicViscosity(
    magnitude: Magnitude,
    expression: PascalSecond
) : Measure<DynamicViscosity.PascalSecond, DynamicViscosity>(magnitude, expression, ::DynamicViscosity) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, PascalSecond(prefix))

    /**
     * Unit of [DynamicViscosity].
     *
     * Represents the unit of **dynamic viscosity**, i.e., the physical quantity measuring
     * a fluid's resistance to flow under an applied shear stress.
     *
     * Symbol: `Pa·s`
     * SI: `kg·m⁻¹·s⁻¹`
     *
     * @see DynamicViscosity
     */
    typealias PascalSecond = Product<Pascal, Second>

    companion object {
        /**
         * Creates a [PascalSecond] expression for **pascal second** (`Pa·s`).
         *
         * @param prefix Metric prefix applied to the pascal unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [PascalSecond] expression for `Pa·s`.
         */
        @Suppress("FunctionNaming")
        internal fun PascalSecond(prefix: Metric = Metric.BASE): PascalSecond =
            Product(Pascal(prefix), Second())
    }

    // Dimension-aware arithmetic
    operator fun div(
        other: org.kisu.units.base.Time
    ): org.kisu.units.special.Pressure =
        org.kisu.units.special.Pressure(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.Pressure
    ): org.kisu.units.base.Time =
        org.kisu.units.base.Time(canonical.component1() / other.canonical.component1())
}
