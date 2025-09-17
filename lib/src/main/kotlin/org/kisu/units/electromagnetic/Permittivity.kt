package org.kisu.units.electromagnetic

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Farad
import java.math.BigDecimal

/**
 * Represents **electric permittivity** (ε), a measure of a material's ability
 * to permit the formation of an electric field within it.
 *
 * - **Dimension**: capacitance per length (F/m)
 * - **SI Unit**: farad per metre (F/m)
 *
 * Permittivity relates the electric displacement field (D) to the electric
 * field strength (E) in a medium:
 *
 *     D = ε * E
 *
 * Example usages include:
 * - Characterizing dielectric materials
 * - Designing capacitors and insulating materials
 * - Modeling electromagnetic wave propagation in media
 *
 * @param magnitude numerical value of the measure
 * @param expression unit expression in farad per metre (F/m)
 */
class Permittivity(
    magnitude: BigDecimal,
    expression: FaradPerMetre
) : Measure<Permittivity.FaradPerMetre, Permittivity>(magnitude, expression, ::Permittivity) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
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
         * Creates a measure of **farads per metre** (F/m).
         *
         * This derived unit expresses **capacitance per unit length** —
         * how much capacitance is present along a given length, commonly used in
         * transmission lines and electromagnetic contexts.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Farad] (capacitance) with the specified [prefix]
         *  - divided by a [Metre] (length)
         *
         * @param prefix Metric prefix to apply to the farad unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [FaradPerMetre] representing F/m.
         */
        @Suppress("FunctionNaming")
        internal fun FaradPerMetre(prefix: Metric = Metric.BASE): FaradPerMetre =
            Quotient(Farad(prefix), Metre())
    }
}
