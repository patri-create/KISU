package org.kisu.units.electromagnetic

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.representation.Product
import org.kisu.units.special.Ohm
import java.math.BigDecimal

/**
 * Represents **electrical resistivity** (ρ), a measure of how strongly a material
 * opposes the flow of electric current.
 *
 * - **Dimension**: electric resistance × length (Ω·m)
 * - **SI Unit**: ohm metre (Ω·m)
 *
 * Resistivity is the intrinsic property of a material and is related to the
 * resistance (R) of a uniform conductor by:
 *
 *     R = ρ * (L / A)
 *
 * where:
 * - L is the length of the conductor,
 * - A is the cross-sectional area.
 *
 * Example usages include:
 * - Characterizing conductors and insulators
 * - Designing electrical circuits and materials
 * - Modeling heat generation due to resistive losses
 *
 * @param magnitude numerical value of the measure
 * @param expression unit expression in ohm metre (Ω·m)
 */
class Resistivity(
    magnitude: BigDecimal,
    expression: OhmMetre
) : Measure<Resistivity.OhmMetre, Resistivity>(magnitude, expression, ::Resistivity) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, OhmMetre(prefix))

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
         * Creates a measure of **ohm-metres** (Ω·m).
         *
         * This compound unit represents the product of:
         *  - an [Ohm] (electrical resistance) with the specified [prefix]
         *  - multiplied by a [Metre] (length)
         *
         * It is commonly used to express **resistivity** of materials.
         *
         * @param prefix Metric prefix to apply to the ohm unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return An [OhmMetre] representing Ω·m.
         */
        @Suppress("FunctionNaming")
        internal fun OhmMetre(prefix: Metric = Metric.BASE): OhmMetre =
            Product(Ohm(prefix), Metre())
    }
}
