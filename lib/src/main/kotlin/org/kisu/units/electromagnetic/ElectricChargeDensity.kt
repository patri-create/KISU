package org.kisu.units.electromagnetic

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.electromagnetic.ElectricChargeDensity.Companion.CoulombPerCubicMetre
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Coulomb
import org.kisu.units.special.CubicMetre
import java.math.BigDecimal

/**
 * Represents the physical quantity of **electric charge density**, measured in
 * [CoulombPerCubicMetre].
 *
 * Electric charge density quantifies how much electric charge is distributed through a
 * unit volume. It is the natural description for charged media, plasmas, electrolytes,
 * and continuum electrostatics.
 *
 * The associated unit representation is [CoulombPerCubicMetre] (`C/m³`).
 */
class ElectricChargeDensity(
    magnitude: BigDecimal,
    expression: CoulombPerCubicMetre
) : Measure<ElectricChargeDensity.CoulombPerCubicMetre, ElectricChargeDensity>(
    magnitude = magnitude,
    expression = expression,
    create = ::ElectricChargeDensity
) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, CoulombPerCubicMetre(prefix))

    /**
     * Represents the SI unit **coulomb per cubic metre (C/m³)**.
     *
     * This unit measures **electric charge density**, i.e., the amount of electric
     * charge per unit volume.
     * It is defined as the [Quotient] of [Coulomb] (electric charge) and [CubicMetre] (volume).
     *
     * Example usages include:
     * - Describing the distribution of charge in a volume
     * - Calculating electric fields from volumetric charge distributions
     *
     * @see ElectricChargeDensity for the physical quantity represented by this unit.
     */
    typealias CoulombPerCubicMetre = Quotient<Coulomb, CubicMetre>

    companion object {
        /**
         * Creates a [CoulombPerCubicMetre] expression for **coulomb per cubic metre** (`C/m³`).
         *
         * @param prefix Metric prefix applied to the coulomb unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [CoulombPerCubicMetre] expression for `C/m³`.
         */
        @Suppress("FunctionNaming")
        internal fun CoulombPerCubicMetre(prefix: Metric = Metric.BASE): CoulombPerCubicMetre =
            Quotient(Coulomb(prefix), CubicMetre())
    }
}
