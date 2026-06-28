@file:Suppress("TooManyFunctions")

package org.kisu.units.electromagnetic

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.electromagnetic.LinearChargeDensity.Companion.CoulombPerMetre
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Coulomb

/**
 * Represents the physical quantity of **linear charge density**, measured in
 * [CoulombPerMetre].
 *
 * Linear charge density quantifies how much electric charge is distributed along a unit
 * length. It is the natural description for charged wires, rods, filaments, and other
 * effectively one-dimensional charge distributions.
 *
 * The associated unit representation is [CoulombPerMetre] (`C/m`).
 */
class LinearChargeDensity(
    magnitude: Magnitude,
    expression: CoulombPerMetre
) : Measure<LinearChargeDensity.CoulombPerMetre, LinearChargeDensity>(
    magnitude = magnitude,
    expression = expression,
    create = ::LinearChargeDensity
) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, CoulombPerMetre(prefix))

    /**
     * Represents the SI unit **coulomb per metre (C/m)**.
     *
     * This unit measures **linear charge density**, i.e., the amount of electric
     * charge distributed per unit length.
     * It is defined as the [Quotient] of [Coulomb] (electric charge) and [Metre] (length).
     *
     * Example usages include:
     * - Describing charge along wires, filaments, or rods
     * - Calculating electric fields from line charge distributions
     *
     * @see LinearChargeDensity for the physical quantity represented by this unit.
     */
    typealias CoulombPerMetre = Quotient<Coulomb, Metre>

    companion object {
        /**
         * Creates a [CoulombPerMetre] expression for **coulomb per metre** (`C/m`).
         *
         * @param prefix Metric prefix applied to the coulomb unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [CoulombPerMetre] expression for `C/m`.
         */
        @Suppress("FunctionNaming")
        internal fun CoulombPerMetre(prefix: Metric = Metric.BASE): CoulombPerMetre =
            Quotient(Coulomb(prefix), Metre())
    }

    // Dimension-aware arithmetic
    operator fun times(
        other: org.kisu.units.base.Length
    ): org.kisu.units.special.ElectricCharge =
        org.kisu.units.special.ElectricCharge(canonical.component1() * other.canonical.component1())
}
