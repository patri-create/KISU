@file:Suppress("TooManyFunctions")

package org.kisu.units.electromagnetic

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.electromagnetic.ElectricConductivity.Companion.SiemensPerMetre
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Siemens

/**
 * Represents the physical quantity of **electric conductivity**, measured in
 * [SiemensPerMetre].
 *
 * Electric conductivity quantifies how readily a material carries electric current. It
 * is the reciprocal perspective of resistivity and is central to electronics,
 * electrochemistry, and materials characterization.
 *
 * The associated unit representation is [SiemensPerMetre] (`S/m`).
 */
class ElectricConductivity(
    magnitude: Magnitude,
    expression: SiemensPerMetre
) : Measure<ElectricConductivity.SiemensPerMetre, ElectricConductivity>(
    magnitude = magnitude,
    expression = expression,
    create = ::ElectricConductivity
) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, SiemensPerMetre(prefix))

    /**
     * Returns the electrical resistivity associated with this conductivity by inverting its canonical magnitude.
     */
    val resistivity: Resistivity
        get() = Resistivity(canonical.component1().inverted)

    /**
     * Represents the SI unit **siemens per metre (S/m)**.
     *
     * This unit measures **electric conductivity**, i.e., the ability of a material
     * to conduct electric current per unit length.
     * It is defined as the [Quotient] of [Siemens] (conductance) and [Metre] (length).
     *
     * Example usages include:
     * - Characterizing the conductivity of metals, semiconductors, and electrolytes
     * - Electrical engineering and materials science calculations
     *
     * @see ElectricConductivity for the physical quantity represented by this unit.
     */
    typealias SiemensPerMetre = Quotient<Siemens, Metre>

    companion object {
        /**
         * Creates a [SiemensPerMetre] expression for **siemens per metre** (`S/m`).
         *
         * @param prefix Metric prefix applied to the siemens unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [SiemensPerMetre] expression for `S/m`.
         */
        @Suppress("FunctionNaming")
        internal fun SiemensPerMetre(prefix: Metric = Metric.BASE): SiemensPerMetre =
            Quotient(Siemens(prefix), Metre())
    }

    // Dimension-aware arithmetic
    /**
     * Divides this [ElectricConductivity] by [MolarConductivity][org.kisu.units.chemistry.MolarConductivity],
     * yielding [Molarity][org.kisu.units.chemistry.Molarity].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.chemistry.MolarConductivity
    ): org.kisu.units.chemistry.Molarity =
        org.kisu.units.chemistry.Molarity(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [ElectricConductivity] by [Molarity][org.kisu.units.chemistry.Molarity],
     * yielding [MolarConductivity][org.kisu.units.chemistry.MolarConductivity].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.chemistry.Molarity
    ): org.kisu.units.chemistry.MolarConductivity =
        org.kisu.units.chemistry.MolarConductivity(canonical.component1() / other.canonical.component1())

    /**
     * Multiplies this [ElectricConductivity] by [Length][org.kisu.units.base.Length],
     * yielding [Conductance][org.kisu.units.special.Conductance].
     *
     * Both operands are converted to their canonical units before the multiplication result is calculated.
     */
    operator fun times(
        other: org.kisu.units.base.Length
    ): org.kisu.units.special.Conductance =
        org.kisu.units.special.Conductance(canonical.component1() * other.canonical.component1())
}
