package org.kisu.units.electromagnetic

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.electromagnetic.MagneticPermittivity.Companion.HenryPerMetre
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Henry

/**
 * Represents magnetic permeability, here exposed under the type name
 * `MagneticPermittivity`.
 *
 * Magnetic permeability quantifies how readily a medium supports magnetic flux in
 * response to an applied magnetic field. In linear materials it appears in the relation
 * `B = μH`, linking magnetic flux density to magnetic field strength.
 *
 * This quantity is used in material characterization, magnetic circuit analysis,
 * transformer and inductor design, and electromagnetic simulation.
 *
 * The associated unit representation is [HenryPerMetre] (`H/m`).
 */
class MagneticPermittivity(
    magnitude: Magnitude,
    expression: HenryPerMetre
) : Measure<MagneticPermittivity.HenryPerMetre, MagneticPermittivity>(
    magnitude = magnitude,
    expression = expression,
    create = ::MagneticPermittivity
) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, HenryPerMetre(prefix))

    val magneticSusceptibility: MagneticSusceptibility
        get() = MagneticSusceptibility(canonical.component1().inverted)

    /**
     * Represents the SI unit **henry per metre (H/m)**.
     *
     * This unit is used for magnetic permeability: inductive response normalized by
     * length. It is defined as the [Quotient] of [Henry] and [Metre].
     *
     * @see MagneticPermittivity
     */
    typealias HenryPerMetre = Quotient<Henry, Metre>

    companion object {
        /**
         * Creates a [HenryPerMetre] expression for **henry per metre** (`H/m`).
         *
         * @param prefix Metric prefix applied to the henry unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [HenryPerMetre] expression for `H/m`.
         */
        @Suppress("FunctionNaming")
        internal fun HenryPerMetre(prefix: Metric = Metric.BASE): HenryPerMetre =
            Quotient(Henry(prefix), Metre())
    }
}
