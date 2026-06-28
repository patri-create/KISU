@file:Suppress("TooManyFunctions")

package org.kisu.units.electromagnetic

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.electromagnetic.MagneticMoment.Companion.WeberMetre
import org.kisu.units.representation.Product
import org.kisu.units.special.Weber

/**
 * Represents magnetic moment in terms of [WeberMetre].
 *
 * In this API the quantity is modeled as magnetic flux multiplied by length. This is a library representation choice;
 * readers expecting the common `A·m²` form should interpret this type through its expressed units rather than by name
 * alone.
 *
 * @param magnitude Numerical value of the quantity.
 * @param expression Unit expression in weber metres (`Wb·m`).
 */
class MagneticMoment(
    magnitude: Magnitude,
    expression: WeberMetre
) : Measure<MagneticMoment.WeberMetre, MagneticMoment>(magnitude, expression, ::MagneticMoment) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, WeberMetre(prefix))

    /**
     * Represents the library unit **weber metre (Wb·m)** for [MagneticMoment].
     *
     * It is defined as the [Product] of [Weber] and [Metre].
     *
     * @see MagneticMoment
     */
    typealias WeberMetre = Product<Weber, Metre>

    companion object {
        /**
         * Creates a [WeberMetre] expression for **weber metre** (`Wb·m`).
         *
         * @param prefix Metric prefix applied to the weber unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [WeberMetre] expression for `Wb·m`.
         */
        @Suppress("FunctionNaming")
        internal fun WeberMetre(prefix: Metric = Metric.BASE): WeberMetre =
            Product(Weber(prefix), Metre())
    }

    // Dimension-aware arithmetic
    operator fun div(
        other: org.kisu.units.base.Length
    ): org.kisu.units.special.MagneticFlux =
        org.kisu.units.special.MagneticFlux(canonical.component1() / other.canonical.component1())

    operator fun div(
        other: org.kisu.units.special.MagneticFlux
    ): org.kisu.units.base.Length =
        org.kisu.units.base.Length(canonical.component1() / other.canonical.component1())
}
