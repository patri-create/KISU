package org.kisu.units.electromagnetic

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.electromagnetic.ElectricFieldStrength.Companion.VoltPerMetre
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Volt

/**
 * Represents the physical quantity of **electric field strength**, measured in
 * [VoltPerMetre].
 *
 * Electric field strength quantifies the intensity of an electric field at a point. It
 * can be understood as force per unit charge or, equivalently, potential difference per
 * unit distance.
 *
 * Typical examples include capacitor fields, electrostatic environments, and breakdown
 * or insulation studies.
 *
 * The associated unit representation is [VoltPerMetre] (`V/m`).
 */
class ElectricFieldStrength(
    magnitude: Magnitude,
    expression: VoltPerMetre
) : Measure<ElectricFieldStrength.VoltPerMetre, ElectricFieldStrength>(
    magnitude = magnitude,
    expression = expression,
    create = ::ElectricFieldStrength
) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, VoltPerMetre(prefix))

    /**
     * Represents the SI unit **volt per metre (V/m)**.
     *
     * This unit measures **electric field strength**, i.e., force per unit positive charge.
     * Equivalently, it is the electric potential difference per unit distance.

     * It is defined as the [Quotient] of [Volt] (electric potential) and [Metre] (distance).
     *
     * Example usages include:
     * - Calculating the intensity of electric fields
     * - Modeling forces on charges in electrostatics
     *
     * @see ElectricFieldStrength for the physical quantity represented by this unit.
     */
    typealias VoltPerMetre = Quotient<Volt, Metre>

    companion object {
        /**
         * Creates a [VoltPerMetre] expression for **volt per metre** (`V/m`).
         *
         * @param prefix Metric prefix applied to the volt unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [VoltPerMetre] expression for `V/m`.
         */
        @Suppress("FunctionNaming")
        internal fun VoltPerMetre(prefix: Metric = Metric.BASE): VoltPerMetre =
            Quotient(Volt(prefix), Metre())
    }
}
