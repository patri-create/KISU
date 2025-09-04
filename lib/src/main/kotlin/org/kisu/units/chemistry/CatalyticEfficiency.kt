package org.kisu.units.chemistry

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Mole
import org.kisu.units.base.Second
import org.kisu.units.representation.Product
import org.kisu.units.representation.Quotient
import org.kisu.units.special.CubicMetre
import org.kisu.units.special.SquareMetre
import java.math.BigDecimal

/**
 * Represents the SI unit **cubic metre per mole second (m³/(mol·s))**.
 *
 * This unit is used to measure **catalytic efficiency**, i.e., the volume of
 * substrate converted per mole of catalyst per second.
 * It is defined as the [Quotient] of [SquareMetre] (area) divided by the [Product]
 * of [Mole] (amount of substance) and [Second] (time).
 *
 * Example usages include:
 * - Characterising enzyme or catalyst performance in chemical reactions
 * - Determining turnover rates in biochemistry and industrial catalysis
 *
 * @see CatalyticEfficiency for the physical quantity represented by this unit.
 */
typealias CubicMetrePerMoleSecond = Quotient<CubicMetre, Product<Mole, Second>>

/**
 * Creates a measure of **cubic metres per mole-second** (m³/(mol·s)).
 *
 * This is a derived unit commonly used in chemistry and related
 * disciplines for expressing volumetric quantities per substance
 * amount per unit of time.
 *
 * Internally this returns a [Quotient] of:
 *  - a [CubicMetre] (volume) with the specified [prefix]
 *  - divided by a [Product] of [Mole] (amount of substance)
 *    and [Second] (time)
 *
 * @param prefix Metric prefix to apply to the cubic metre unit.
 * Defaults to [Metric.BASE] (no prefix).
 *
 * @return A [CubicMetrePerMoleSecond] representing m³/(mol·s).
 */
@Suppress("FunctionNaming")
internal fun CubicMetrePerMoleSecond(prefix: Metric = Metric.BASE): CubicMetrePerMoleSecond = Quotient(
    CubicMetre(prefix),
    Product(Mole(), Second())
)

/**
 * Represents the physical quantity of **catalytic efficiency**.
 *
 * Catalytic efficiency quantifies the **efficiency of an enzyme or catalyst
 * in converting substrate molecules into product**.
 * It is often expressed as the ratio *kcat/Km* in enzyme kinetics, where:
 * - *kcat* is the catalytic turnover number
 * - *Km* is the Michaelis–Menten constant
 *
 * Its SI unit is the **cubic metre per mole second (m³/(mol·s))**, represented here
 * by [CubicMetrePerMoleSecond].
 *
 * Example usages include:
 * - Characterising enzyme kinetics in biochemistry
 * - Comparing catalytic effectiveness between different enzymes
 * - Biotechnological and pharmaceutical applications
 *
 * The magnitude is stored as a [BigDecimal] to ensure high precision.
 * Instances of [CatalyticEfficiency] are immutable, and the [expression] parameter ties
 * the measurement to its unit representation ([CubicMetrePerMoleSecond]).
 *
 * @property magnitude The numeric value of the catalytic efficiency.
 * @property expression The unit expression of the catalytic efficiency, always [CubicMetrePerMoleSecond].
 */
class CatalyticEfficiency(
    magnitude: BigDecimal,
    expression: CubicMetrePerMoleSecond
) : Measure<CubicMetrePerMoleSecond, CatalyticEfficiency>(magnitude, expression, ::CatalyticEfficiency) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(
            magnitude,
            CubicMetrePerMoleSecond(prefix)
        )
}
