package org.kisu.units.electromagnetic

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Coulomb
import org.kisu.units.special.SquareMetre
import java.math.BigDecimal

/**
 * Represents the **electric displacement field** `D`, measured in
 * [CoulombPerSquareMetre].
 *
 * Electric displacement field quantifies how electric fields and free charge are
 * related inside matter. It is especially useful in dielectric media, where it helps
 * separate externally supplied charge from polarization effects in the material.
 *
 * This quantity appears naturally in capacitor theory, dielectric modeling, and
 * Maxwell's equations in matter, particularly through Gauss's law for electric
 * displacement.
 *
 * @see CoulombPerSquareMetre
 * @see ElectricFieldStrength
 */
class ElectricDisplacementField(
    magnitude: BigDecimal,
    expression: CoulombPerSquareMetre
) : Measure<ElectricDisplacementField.CoulombPerSquareMetre, ElectricDisplacementField>(
    magnitude = magnitude,
    expression = expression,
    create = ::ElectricDisplacementField
) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, CoulombPerSquareMetre(prefix))

    /**
     * Represents the SI unit **coulomb per square metre (C/m²)**.
     *
     * This unit is used for electric displacement field and is also dimensionally
     * consistent with surface charge density. It is defined as the [Quotient] of
     * [Coulomb] and [SquareMetre].
     *
     * @see ElectricDisplacementField
     */
    typealias CoulombPerSquareMetre = Quotient<Coulomb, SquareMetre>

    companion object {
        /**
         * Creates a [CoulombPerSquareMetre] expression for **coulomb per square metre** (`C/m²`).
         *
         * @param prefix Metric prefix applied to the coulomb unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [CoulombPerSquareMetre] expression for `C/m²`.
         */
        @Suppress("FunctionNaming")
        internal fun CoulombPerSquareMetre(prefix: Metric = Metric.BASE): CoulombPerSquareMetre =
            Quotient(Coulomb(prefix), SquareMetre())
    }
}
