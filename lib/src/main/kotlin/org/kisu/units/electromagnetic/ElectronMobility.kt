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
 * Represents **electron mobility** (μ), which describes how quickly an electron
 * can move through a material under the influence of an electric field.
 *
 * - **Dimension**: area per voltage-time (m² / (V·s))
 * - **SI Unit**: square metre per volt-second (m²/(V·s))
 *
 * Electron mobility is a key property in **semiconductor physics**, as it determines
 * how efficiently charge carriers move through a material, directly impacting the
 * electrical conductivity and performance of electronic devices.
 *
 * @param magnitude numerical value of the measure
 * @param expression unit expression in square metre per volt-second (m²/(V·s))
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
         * Creates a measure of **square metres per volt-second** (m²/(V·s)).
         *
         * This derived unit expresses a quantity such as **electrical mobility**
         * (area per unit electric potential and time) used in various electromagnetic
         * and materials contexts.
         *
         * Internally this returns a [Quotient] of:
         *  - a [SquareMetre] (area) with the specified [prefix]
         *  - divided by a [Product] of a [Volt] (electric potential) and a [Second] (time)
         *
         * @param prefix Metric prefix to apply to the square metre unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [SquareMetrePerVoltSecond] representing m²/(V·s).
         */
        @Suppress("FunctionNaming")
        internal fun SquareMetrePerVoltSecond(prefix: Metric = Metric.BASE): SquareMetrePerVoltSecond =
            Quotient(
                SquareMetre(prefix),
                Product(Volt(), Second())
            )
    }
}
