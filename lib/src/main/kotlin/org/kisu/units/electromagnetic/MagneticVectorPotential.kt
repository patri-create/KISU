package org.kisu.units.electromagnetic

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Weber
import java.math.BigDecimal

/**
 * Represents the **magnetic vector potential** (A), a vector field used in
 * electromagnetism to describe the magnetic field in terms of a potential.
 *
 * - **Dimension**: magnetic flux per length (Wb/m)
 * - **SI Unit**: weber per metre (Wb/m)
 *
 * The magnetic field (B) can be derived from the vector potential via the curl:
 *
 *     B = ∇ × A
 *
 * Magnetic vector potential is widely used in **theoretical electromagnetism,
 * quantum mechanics, and electrodynamics calculations**.
 *
 * Example usages include:
 * - Computing magnetic fields in complex geometries
 * - Solving Maxwell’s equations
 * - Analyzing inductors and magnetic circuits
 *
 * @param magnitude numerical value of the measure
 * @param expression unit expression in weber per metre (Wb/m)
 */
class MagneticVectorPotential(
    magnitude: BigDecimal,
    expression: WeberPerMetre
) : Measure<MagneticVectorPotential.WeberPerMetre, MagneticVectorPotential>(
    magnitude = magnitude,
    expression = expression,
    create = ::MagneticVectorPotential
) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
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
         * Creates a measure of **webers per metre** (Wb/m).
         *
         * This derived unit expresses **magnetic flux per unit length** —
         * how much magnetic flux is present along a given length.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Weber] (magnetic flux) with the specified [prefix]
         *  - divided by a [Metre] (length)
         *
         * @param prefix Metric prefix to apply to the weber unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [WeberPerMetre] representing Wb/m.
         */
        @Suppress("FunctionNaming")
        internal fun WeberPerMetre(prefix: Metric = Metric.BASE): WeberPerMetre =
            Quotient(Weber(prefix), Metre())
    }
}
