package org.kisu.units.mechanics

import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Newton
import java.math.BigDecimal

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
) : Measure<NewtonPerMetre, SurfaceTension>(magnitude, expression, ::SurfaceTension)
