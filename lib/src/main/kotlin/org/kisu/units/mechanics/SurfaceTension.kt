package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Newton
import java.math.BigDecimal

/**
 * Measure of surface tension expressed in [NewtonPerMetre].
 *
 * Surface tension quantifies the cohesive force at the interface of a liquid,
 * reflecting how molecules resist deformation of the surface.
 *
 * Common applications include:
 * - Fluid mechanics (capillarity, droplet formation)
 * - Material science (wetting and adhesion studies)
 * - Chemistry (surface phenomena, detergency)
 *
 * @property magnitude Numerical value of the surface tension.
 * @property expression Unit of the surface tension, here [NewtonPerMetre].
 *
 * @see NewtonPerMetre
 */
class SurfaceTension(
    magnitude: BigDecimal,
    expression: NewtonPerMetre
) : Measure<SurfaceTension.NewtonPerMetre, SurfaceTension>(magnitude, expression, ::SurfaceTension) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
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
         * Creates a measure of **newtons per metre** (N/m).
         *
         * This derived unit expresses **linear force density or stiffness** —
         * how much force is applied per unit length, commonly used in springs and tension calculations.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Newton] (force) with the specified [prefix]
         *  - divided by a [Metre] (length)
         *
         * @param prefix Metric prefix to apply to the newton unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [NewtonPerMetre] representing N/m.
         */
        @Suppress("FunctionNaming")
        internal fun NewtonPerMetre(prefix: Metric = Metric.BASE): NewtonPerMetre =
            Quotient(Newton(prefix), Metre())
    }
}
