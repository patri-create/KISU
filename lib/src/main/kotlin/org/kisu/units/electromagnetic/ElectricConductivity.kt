package org.kisu.units.electromagnetic

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Siemens
import java.math.BigDecimal

/**
 * Represents **electric conductivity** in the SI system.
 *
 * Electric conductivity quantifies a material's ability to conduct an electric current.
 * It is defined as the reciprocal of resistivity and can be expressed as:
 *
 *     σ = 1 / ρ
 *
 * where:
 * - σ is the electric conductivity,
 * - ρ is the electric resistivity (Ω·m).
 *
 * The SI derived unit is **siemens per metre (S/m)**,
 * which corresponds to the reciprocal ohm per metre:
 *
 *     1 S/m = 1 / (Ω·m)
 *
 * @constructor Creates a measure of electric conductivity with a given [magnitude]
 * in terms of the unit expression [SiemensPerMetre].
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
        this(magnitude, Quotient(Siemens(prefix), Metre()))

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
}
