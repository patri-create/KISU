package org.kisu.units.electromagnetic

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.electromagnetic.ElectricChargeDensity.Companion.CoulombPerCubicMetre
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Coulomb
import org.kisu.units.special.CubicMetre
import java.math.BigDecimal

/**
 * Represents **electric charge density** in the SI system.
 *
 * Electric charge density measures the amount of electric charge distributed
 * per unit volume. It is defined as the quotient of electric charge and volume:
 *
 *     ρ = Q / V
 *
 * where:
 * - ρ is the electric charge density,
 * - Q is the electric charge in coulombs (C),
 * - V is the volume in cubic metres (m³).
 *
 * The SI derived unit is **coulomb per cubic metre (C/m³)**.
 *
 * @constructor Creates a measure of electric charge density with a given [magnitude]
 * in terms of the unit expression [CoulombPerCubicMetre].
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
         * Creates a measure of **coulombs per cubic metre** (C/m³).
         *
         * This derived unit expresses **volume charge density** —
         * how much electric charge (in coulombs) is contained per unit volume.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Coulomb] (electric charge) with the specified [prefix]
         *  - divided by a [CubicMetre] (volume)
         *
         * @param prefix Metric prefix to apply to the coulomb unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [CoulombPerCubicMetre] representing C/m³.
         */
        @Suppress("FunctionNaming")
        internal fun CoulombPerCubicMetre(prefix: Metric = Metric.BASE): CoulombPerCubicMetre =
            Quotient(Coulomb(prefix), CubicMetre())
    }
}
