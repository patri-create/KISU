package org.kisu.units.electromagnetic

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Siemens
import java.math.BigDecimal

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
    magnitude: BigDecimal,
    expression: SiemensPerMetre
) : Measure<ElectricConductivity.SiemensPerMetre, ElectricConductivity>(
    magnitude = magnitude,
    expression = expression,
    create = ::ElectricConductivity
) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, SiemensPerMetre(prefix))

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
}
