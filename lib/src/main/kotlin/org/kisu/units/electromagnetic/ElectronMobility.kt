package org.kisu.units.electromagnetic

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.representation.Product
import org.kisu.units.representation.Quotient
import org.kisu.units.special.SquareMetre
import org.kisu.units.special.Volt
import java.math.BigDecimal

/**
 * Represents the physical quantity of **electron mobility**, measured in
 * [SquareMetrePerVoltSecond].
 *
 * Electron mobility quantifies how quickly electrons drift through a material in
 * response to an electric field. It is a central transport property in semiconductors
 * and conducting media.
 *
 * Typical examples include transistor materials, semiconductor characterization, and
 * carrier-transport modeling.
 *
 * The associated unit representation is [SquareMetrePerVoltSecond] (`m²/(V·s)`).
 */
class ElectronMobility(
    magnitude: BigDecimal,
    expression: SquareMetrePerVoltSecond
) : Measure<ElectronMobility.SquareMetrePerVoltSecond, ElectronMobility>(
    magnitude = magnitude,
    expression = expression,
    create = ::ElectronMobility
) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, SquareMetrePerVoltSecond(prefix))

    /**
     * Represents the SI unit **square metre per volt-second (m²/(V·s))**.
     *
     * This unit measures **electron mobility**, i.e., how quickly an electron
     * can move through a material under the influence of an electric field.
     * It is defined as the [Quotient] of [SquareMetre] (area) and the [Product] of
     * [Volt] (electric potential) and [Second] (time).
     *
     * Example usages include:
     * - Characterizing charge carrier mobility in semiconductors
     * - Modeling conductivity and drift velocity in materials
     *
     * @see ElectronMobility for the physical quantity represented by this unit.
     */
    typealias SquareMetrePerVoltSecond = Quotient<SquareMetre, Product<Volt, Second>>

    companion object {
        /**
         * Creates a [SquareMetrePerVoltSecond] expression for **square metre per volt second** (`m²/(V·s)`).
         *
         * @param prefix Metric prefix applied to the square metre unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [SquareMetrePerVoltSecond] expression for `m²/(V·s)`.
         */
        @Suppress("FunctionNaming")
        internal fun SquareMetrePerVoltSecond(prefix: Metric = Metric.BASE): SquareMetrePerVoltSecond =
            Quotient(
                SquareMetre(prefix),
                Product(Volt(), Second())
            )
    }
}
