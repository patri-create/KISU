package org.kisu.units.chemistry

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Mole
import org.kisu.units.base.Second
import org.kisu.units.chemistry.CatalyticEfficiency.Companion.CubicMetrePerMoleSecond
import org.kisu.units.representation.Product
import org.kisu.units.representation.Quotient
import org.kisu.units.special.CubicMetre
import java.math.BigDecimal

/**
 * Represents the physical quantity of **catalytic efficiency**, measured in
 * [CubicMetrePerMoleSecond].
 *
 * Catalytic efficiency quantifies how effectively an enzyme or catalyst converts
 * substrate into product once both reaction speed and substrate affinity are taken into
 * account. In biochemistry it is closely associated with the ratio `kcat/Km`.
 *
 * This quantity is used to compare enzymes, characterize catalytic mechanisms, and
 * evaluate reaction performance in biochemical, pharmaceutical, and industrial
 * contexts.
 *
 * The associated SI unit representation is [CubicMetrePerMoleSecond] (`m³/(mol·s)`).
 */
class CatalyticEfficiency(
    magnitude: BigDecimal,
    expression: CubicMetrePerMoleSecond
) : Measure<CatalyticEfficiency.CubicMetrePerMoleSecond, CatalyticEfficiency>(
    magnitude = magnitude,
    expression = expression,
    create = ::CatalyticEfficiency
) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(
            magnitude,
            CubicMetrePerMoleSecond(prefix)
        )

    /**
     * Represents the SI unit **cubic metre per mole second (m³/(mol·s))**.
     *
     * This unit is used to measure **catalytic efficiency**, i.e., the volume of
     * substrate converted per mole of catalyst per second.
     *
     * It is defined as the [Quotient] of [CubicMetre] (volume) divided by the [Product] of [Mole] (amount of
     * substance) and [Second] (time).
     *
     * Example usages include:
     * - Characterizing enzyme or catalyst performance in biochemistry and industrial catalysis
     *
     * @see CatalyticEfficiency for the physical quantity represented by this unit.
     */
    typealias CubicMetrePerMoleSecond = Quotient<CubicMetre, Product<Mole, Second>>

    companion object {
        /**
         * Creates a [CubicMetrePerMoleSecond] expression for **cubic metre per mole second** (`m³/(mol·s)`).
         *
         * @param prefix Metric prefix applied to the cubic metre unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [CubicMetrePerMoleSecond] expression for `m³/(mol·s)`.
         */
        @Suppress("FunctionNaming")
        internal fun CubicMetrePerMoleSecond(prefix: Metric = Metric.BASE): CubicMetrePerMoleSecond = Quotient(
            CubicMetre(prefix),
            Product(Mole(), Second())
        )
    }
}
