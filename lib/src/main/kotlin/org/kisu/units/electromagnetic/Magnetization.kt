@file:Suppress("TooManyFunctions")

package org.kisu.units.electromagnetic

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Ampere
import org.kisu.units.base.Metre
import org.kisu.units.electromagnetic.Magnetization.Companion.AmperePerMetre
import org.kisu.units.representation.Quotient

/**
 * Represents the physical quantity of **magnetization**, measured in
 * [AmperePerMetre].
 *
 * Magnetization quantifies magnetic dipole moment per unit volume in a material. It
 * describes how strongly a substance responds internally to an applied magnetic field.
 *
 * Typical examples include ferromagnetic materials, magnetic media, and constitutive
 * modeling in electromagnetism.
 *
 * The associated unit representation is [AmperePerMetre] (`A/m`).
 */
class Magnetization(
    magnitude: Magnitude,
    expression: AmperePerMetre
) : Measure<Magnetization.AmperePerMetre, Magnetization>(magnitude, expression, ::Magnetization) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, AmperePerMetre(prefix))

    /**
     * Represents the SI unit **ampere per metre (A/m)**.
     *
     * This unit is used for magnetization, commonly interpreted as magnetic dipole
     * moment per unit volume. It is defined here as the [Quotient] of [Ampere] and
     * [Metre].
     *
     * @see Magnetization
     */
    typealias AmperePerMetre = Quotient<Ampere, Metre>

    companion object {
        /**
         * Creates a [AmperePerMetre] expression for **ampere per metre** (`A/m`).
         *
         * @param prefix Metric prefix applied to the ampere unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [AmperePerMetre] expression for `A/m`.
         */
        @Suppress("FunctionNaming")
        internal fun AmperePerMetre(prefix: Metric = Metric.BASE): AmperePerMetre =
            Quotient(Ampere(prefix), Metre())
    }

    // Dimension-aware arithmetic
    operator fun times(
        other: org.kisu.units.base.Length
    ): org.kisu.units.base.Current =
        org.kisu.units.base.Current(canonical.component1() * other.canonical.component1())
}
