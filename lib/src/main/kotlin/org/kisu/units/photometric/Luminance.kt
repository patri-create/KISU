package org.kisu.units.photometric

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Candela
import org.kisu.units.representation.Quotient
import org.kisu.units.special.SquareMetre

/**
 * Represents the physical quantity of **luminance**, measured in
 * [CandelaPerSquareMetre].
 *
 * Luminance quantifies the directional brightness of a surface as seen by an observer.
 * It describes emitted, transmitted, or reflected visible light per unit projected area
 * in a given direction.
 *
 * Typical examples include display brightness, roadway glare, illuminated signage, and
 * perceived brightness of reflective surfaces.
 *
 * The associated unit representation is [CandelaPerSquareMetre] (`cd/m²`), often known
 * in display contexts as nits.
 */
class Luminance(
    magnitude: Magnitude,
    expression: CandelaPerSquareMetre
) : Measure<Luminance.CandelaPerSquareMetre, Luminance>(magnitude, expression, ::Luminance) {
    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, CandelaPerSquareMetre(prefix))

    /**
     * Represents the SI unit **candela per square metre (cd/m²)**.
     *
     * This unit is used to measure **luminance**,
     * i.e., the luminous intensity emitted per unit area in a given direction.
     * It is defined as the [Quotient] of [Candela] (luminous intensity) divided by [SquareMetre] (area).
     *
     * Example usages include:
     * - Specifying the brightness of screens, displays, or monitors
     * - Measuring surface luminance in lighting design or photometry
     * - Analysing visual comfort and visibility in architectural lighting
     *
     * @see Luminance
     */
    typealias CandelaPerSquareMetre = Quotient<Candela, SquareMetre>

    companion object {
        /**
         * Creates a [CandelaPerSquareMetre] expression for **candela per square metre** (`cd/m²`).
         *
         * @param prefix Metric prefix applied to the candela unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [CandelaPerSquareMetre] expression for `cd/m²`.
         */
        @Suppress("FunctionNaming")
        internal fun CandelaPerSquareMetre(prefix: Metric = Metric.BASE): CandelaPerSquareMetre =
            Quotient(Candela(prefix), SquareMetre())
    }
}
