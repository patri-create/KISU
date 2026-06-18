package org.kisu.units.chemistry

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Mole
import org.kisu.units.chemistry.MolarVolume.Companion.CubicMetrePerMole
import org.kisu.units.representation.Quotient
import org.kisu.units.special.CubicMetre

/**
 * Represents the physical quantity of **molar volume**, measured in
 * [CubicMetrePerMole].
 *
 * Molar volume quantifies how much volume is occupied by one mole of substance. It is
 * especially useful when comparing gases, liquids, or solids on a chemically natural
 * amount-of-substance basis.
 *
 * Typical examples include the molar volume of an ideal gas under stated conditions or
 * the volume occupied by one mole of a condensed phase.
 *
 * The associated SI unit representation is [CubicMetrePerMole] (`m³/mol`).
 */
class MolarVolume(
    magnitude: Magnitude,
    expression: CubicMetrePerMole
) : Measure<MolarVolume.CubicMetrePerMole, MolarVolume>(magnitude, expression, ::MolarVolume) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, CubicMetrePerMole(prefix))

    val molarity: Molarity
        get() = Molarity(canonical.component1().inverted)

    /**
     * Represents the SI unit **cubic metre per mole (m³/mol)**.
     *
     * This unit measures **molar volume**, i.e., the volume occupied by one mole
     * of a substance.
     * It is defined as the [Quotient] of [CubicMetre] (volume) divided by [Mole] (amount of substance).
     *
     * Example usages include:
     * - Calculating the volume of gases using the ideal gas law
     * - Determining molar volumes of liquids and solids
     * - Chemical and thermodynamic calculations
     *
     * @see MolarVolume for the physical quantity represented by this unit.
     */
    typealias CubicMetrePerMole = Quotient<CubicMetre, Mole>

    companion object {
        /**
         * Creates a [CubicMetrePerMole] expression for **cubic metre per mole** (`m³/mol`).
         *
         * @param prefix Metric prefix applied to the cubic metre unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [CubicMetrePerMole] expression for `m³/mol`.
         */
        @Suppress("FunctionNaming")
        internal fun CubicMetrePerMole(prefix: Metric = Metric.BASE): CubicMetrePerMole =
            Quotient(CubicMetre(prefix), Mole())
    }
}
