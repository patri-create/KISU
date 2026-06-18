package org.kisu.units.electromagnetic

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.electromagnetic.Resistivity.Companion.OhmMetre
import org.kisu.units.representation.Product
import org.kisu.units.special.Ohm

/**
 * Represents the physical quantity of **electrical resistivity**, measured in
 * [OhmMetre].
 *
 * Resistivity quantifies the intrinsic opposition a material presents to electric
 * current, independent of the geometry of any particular sample. It is the material
 * property behind resistance once length and cross section are taken into account.
 *
 * Typical examples include comparing conductors, semiconductors, and insulators, and
 * estimating resistive losses in devices and materials.
 *
 * The associated unit representation is [OhmMetre] (`Ω·m`).
 */
class Resistivity(
    magnitude: Magnitude,
    expression: OhmMetre
) : Measure<Resistivity.OhmMetre, Resistivity>(magnitude, expression, ::Resistivity) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, OhmMetre(prefix))

    val electricConductivity: ElectricConductivity
        get() = ElectricConductivity(canonical.component1().inverted)

    /**
     * Represents the SI unit **ohm metre (Ω·m)**.
     *
     * This unit measures **electrical resistivity**, i.e., the intrinsic property
     * of a material that opposes the flow of electric current.
     * It is defined as the [Product] of [Ohm] (electrical resistance) and [Metre] (length).
     *
     * Example usages include:
     * - Characterizing conductors and insulators
     * - Designing electrical circuits and materials
     *
     * @see Resistivity for the physical quantity represented by this unit.
     */
    typealias OhmMetre = Product<Ohm, Metre>

    companion object {
        /**
         * Creates a [OhmMetre] expression for **ohm metre** (`Ω·m`).
         *
         * @param prefix Metric prefix applied to the ohm unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [OhmMetre] expression for `Ω·m`.
         */
        @Suppress("FunctionNaming")
        internal fun OhmMetre(prefix: Metric = Metric.BASE): OhmMetre =
            Product(Ohm(prefix), Metre())
    }
}
