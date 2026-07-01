@file:Suppress("TooManyFunctions")

package org.kisu.units.electromagnetic

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.electromagnetic.Permittivity.Companion.FaradPerMetre
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Farad

/**
 * Represents the physical quantity of **electric permittivity**, measured in
 * [FaradPerMetre].
 *
 * Electric permittivity quantifies how a medium responds to an electric field. It links
 * electric displacement to electric field strength and is one of the core constitutive
 * properties of dielectric materials.
 *
 * Typical examples include capacitor dielectrics, insulating materials, and wave
 * propagation in media.
 *
 * The associated unit representation is [FaradPerMetre] (`F/m`).
 */
class Permittivity(
    magnitude: Magnitude,
    expression: FaradPerMetre
) : Measure<Permittivity.FaradPerMetre, Permittivity>(magnitude, expression, ::Permittivity) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, FaradPerMetre(prefix))

    /**
     * Represents the SI unit **farad per metre (F/m)**.
     *
     * This unit measures **electric permittivity**, i.e., the ability of a material
     * to permit the formation of an electric field within it.
     * It is defined as the [Quotient] of [Farad] (capacitance) and [Metre] (length).
     *
     * Example usages include:
     * - Characterizing dielectric materials
     * - Designing capacitors and insulating systems
     *
     * @see Permittivity for the physical quantity represented by this unit.
     */
    typealias FaradPerMetre = Quotient<Farad, Metre>

    companion object {
        /**
         * Creates a [FaradPerMetre] expression for **farad per metre** (`F/m`).
         *
         * @param prefix Metric prefix applied to the farad unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [FaradPerMetre] expression for `F/m`.
         */
        @Suppress("FunctionNaming")
        internal fun FaradPerMetre(prefix: Metric = Metric.BASE): FaradPerMetre =
            Quotient(Farad(prefix), Metre())
    }

    // Dimension-aware arithmetic
    /**
     * Multiplies this [Permittivity] by [Length][org.kisu.units.base.Length],
     * yielding [Capacitance][org.kisu.units.special.Capacitance].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Length
    ): org.kisu.units.special.Capacitance =
        org.kisu.units.special.Capacitance(canonical.component1() * other.canonical.component1())
}
