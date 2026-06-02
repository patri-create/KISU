package org.kisu.units.photometric

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.representation.Product
import org.kisu.units.special.Lumen
import java.math.BigDecimal

/**
 * Represents the physical quantity of **luminous energy**, measured in [LumenSecond].
 *
 * Luminous energy, also called quantity of light, quantifies the total visible light
 * emitted over a duration. It is the time-integrated form of luminous flux.
 *
 * This quantity is useful when evaluating how much visible light a source delivers
 * during a pulse, exposure, or finite operating interval.
 *
 * The associated unit representation is [LumenSecond] (`lm·s`).
 */
class LuminousEnergy(
    magnitude: BigDecimal,
    expression: LumenSecond
) : Measure<LuminousEnergy.LumenSecond, LuminousEnergy>(magnitude, expression, ::LuminousEnergy) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, LumenSecond(prefix))

    /**
     * Represents the SI unit **lumen second (lm·s)**.
     *
     * This unit is used to measure **luminous energy**,
     * i.e., the total amount of visible light emitted over a period of time.
     * It is defined as the [Product] of [Lumen] (luminous flux) and [Second] (time).
     *
     * Example usages include:
     * - Quantifying total light output of a source during a measurement period
     * - Evaluating exposure in photometry experiments
     * - Analysing energy delivered by light sources in lighting engineering
     *
     * @see LuminousEnergy
     */
    typealias LumenSecond = Product<Lumen, Second>

    companion object {
        /**
         * Creates a [LumenSecond] expression for **lumen second** (`lm·s`).
         *
         * @param prefix Metric prefix applied to the lumen unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [LumenSecond] expression for `lm·s`.
         */
        @Suppress("FunctionNaming")
        internal fun LumenSecond(prefix: Metric = Metric.BASE): LumenSecond =
            Product(Lumen(prefix), Second())
    }
}
