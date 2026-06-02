package org.kisu.units.chemistry

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Mole
import org.kisu.units.chemistry.MolarConductivity.Companion.SiemensSquareMetrePerMole
import org.kisu.units.representation.Product
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Siemens
import org.kisu.units.special.SquareMetre
import java.math.BigDecimal

/**
 * Represents the physical quantity of **molar conductivity**, measured in
 * [SiemensSquareMetrePerMole].
 *
 * Molar conductivity quantifies the electrical conductivity contributed by an
 * electrolyte when normalized by amount of substance. It helps describe how effectively
 * ions carry current in solution on a per-mole basis.
 *
 * This quantity is used in electrochemistry to compare electrolytes, study dissociation
 * behavior, and estimate ionic mobility.
 *
 * The associated SI unit representation is [SiemensSquareMetrePerMole] (`S·m²/mol`).
 */
class MolarConductivity(
    magnitude: BigDecimal,
    expression: SiemensSquareMetrePerMole
) : Measure<MolarConductivity.SiemensSquareMetrePerMole, MolarConductivity>(
    magnitude = magnitude,
    expression = expression,
    create = ::MolarConductivity
) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(
            magnitude,
            SiemensSquareMetrePerMole(prefix)
        )

    /**
     * Represents the SI unit **siemens square metre per mole (S·m²/mol)**.
     *
     * This unit is used to measure **molar conductivity**, i.e., the electrical
     * conductivity of an electrolyte solution normalized by its molar concentration.
     * It is defined as the [Quotient] of the [Product] of [Siemens] (conductance) and
     * [SquareMetre] (area) divided by [Mole] (amount of substance).
     *
     * Example usages include:
     * - Determining ion mobility in electrolyte solutions
     * - Characterising strong and weak electrolytes
     * - Electrochemistry and materials science applications
     *
     * @see MolarConductivity for the physical quantity represented by this unit.
     */
    typealias SiemensSquareMetrePerMole = Quotient<Product<Siemens, SquareMetre>, Mole>

    companion object {
        /**
         * Creates a [SiemensSquareMetrePerMole] expression for **siemens square metre per mole** (`S·m²/mol`).
         *
         * @param prefix Metric prefix applied to the siemens unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [SiemensSquareMetrePerMole] expression for `S·m²/mol`.
         */
        @Suppress("FunctionNaming")
        internal fun SiemensSquareMetrePerMole(
            prefix: Metric = Metric.BASE
        ): SiemensSquareMetrePerMole =
            Quotient(
                Product(Siemens(prefix), SquareMetre()),
                Mole()
            )
    }
}
