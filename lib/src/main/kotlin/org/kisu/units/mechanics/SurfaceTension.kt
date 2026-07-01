@file:Suppress("TooManyFunctions")

package org.kisu.units.mechanics

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.mechanics.SurfaceTension.Companion.NewtonPerMetre
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Newton

/**
 * Represents the physical quantity of **surface tension**, measured in
 * [NewtonPerMetre].
 *
 * Surface tension quantifies the contractive tendency of a liquid surface caused by
 * intermolecular cohesion. It governs capillarity, droplet shape, wetting, and many
 * interface phenomena.
 *
 * Typical examples include water droplets, detergency, coating processes, and
 * microfluidic behavior.
 *
 * The associated unit representation is [NewtonPerMetre] (`N/m`).
 */
class SurfaceTension(
    magnitude: Magnitude,
    expression: NewtonPerMetre
) : Measure<SurfaceTension.NewtonPerMetre, SurfaceTension>(magnitude, expression, ::SurfaceTension) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, NewtonPerMetre(prefix))

    /**
     * Unit of [SurfaceTension].
     *
     * Represents the unit of **surface tension**, i.e., the physical quantity measuring
     * force per unit length along a liquid surface.
     *
     * Symbol: `N/m`
     * SI: `kg·s⁻²`
     *
     * @see SurfaceTension
     */
    typealias NewtonPerMetre = Quotient<Newton, Metre>

    companion object {
        /**
         * Creates a [NewtonPerMetre] expression for **newton per metre** (`N/m`).
         *
         * @param prefix Metric prefix applied to the newton unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [NewtonPerMetre] expression for `N/m`.
         */
        @Suppress("FunctionNaming")
        internal fun NewtonPerMetre(prefix: Metric = Metric.BASE): NewtonPerMetre =
            Quotient(Newton(prefix), Metre())
    }

    // Dimension-aware arithmetic
    /**
     * Multiplies this [SurfaceTension] by [Length][org.kisu.units.base.Length],
     * yielding [Force][org.kisu.units.special.Force].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Length
    ): org.kisu.units.special.Force =
        org.kisu.units.special.Force(canonical.component1() * other.canonical.component1())
}
