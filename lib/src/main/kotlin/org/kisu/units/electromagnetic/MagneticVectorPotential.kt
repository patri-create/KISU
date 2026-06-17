package org.kisu.units.electromagnetic

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Weber

/**
 * Represents the physical quantity of **magnetic vector potential**, measured in
 * [WeberPerMetre].
 *
 * Magnetic vector potential is a field quantity from which magnetic flux density can be
 * derived. It is especially important in theoretical electromagnetism, gauge-based
 * formulations, and quantum contexts.
 *
 * Typical examples include field computation in complex geometries, inductive systems,
 * and electromagnetic simulations.
 *
 * The associated unit representation is [WeberPerMetre] (`Wb/m`).
 */
class MagneticVectorPotential(
    magnitude: Magnitude,
    expression: WeberPerMetre
) : Measure<MagneticVectorPotential.WeberPerMetre, MagneticVectorPotential>(
    magnitude = magnitude,
    expression = expression,
    create = ::MagneticVectorPotential
) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, WeberPerMetre(prefix))

    /**
     * Represents the SI unit **weber per metre (Wb/m)**.
     *
     * This unit measures **magnetic vector potential**, i.e., the magnetic flux
     * per unit length.
     * It is defined as the [Quotient] of [Weber] (magnetic flux) and [Metre] (length).
     *
     * Example usages include:
     * - Calculating magnetic fields from vector potentials
     * - Modeling inductors and electromagnetic devices
     *
     * @see MagneticVectorPotential for the physical quantity represented by this unit.
     */
    typealias WeberPerMetre = Quotient<Weber, Metre>

    companion object {
        /**
         * Creates a [WeberPerMetre] expression for **weber per metre** (`Wb/m`).
         *
         * @param prefix Metric prefix applied to the weber unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [WeberPerMetre] expression for `Wb/m`.
         */
        @Suppress("FunctionNaming")
        internal fun WeberPerMetre(prefix: Metric = Metric.BASE): WeberPerMetre =
            Quotient(Weber(prefix), Metre())
    }
}
