package org.kisu.units.electromagnetic

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Volt
import java.math.BigDecimal

/**
 * Represents the SI unit **volt per metre (V/m)**.
 *
 * This unit measures **electric field strength**, i.e., the force experienced by
 * a unit positive charge per unit distance.
 * It is defined as the [Quotient] of [Volt] (electric potential) and [Metre] (distance).
 *
 * Example usages include:
 * - Calculating the intensity of electric fields
 * - Modeling forces on charges in electrostatics
 *
 * @see ElectricFieldStrength for the physical quantity represented by this unit.
 */
typealias VoltPerMetre = Quotient<Volt, Metre>

/**
 * Represents **electric field strength** in the SI system.
 *
 * Electric field strength describes the force experienced by a unit positive charge
 * placed in an electric field. It quantifies the intensity of the electric field at
 * a given point in space.
 *
 * Defined as:
 *
 *     E = F / q
 *
 * where:
 * - E is the electric field strength,
 * - F is the electric force (N),
 * - q is the electric charge (C).
 *
 * It can also be expressed as the potential difference per unit distance:
 *
 *     E = V / d
 *
 * The SI derived unit is **volt per metre (V/m)**:
 *
 *     1 V/m = 1 volt / 1 metre
 *
 * @constructor Creates a measure of electric field strength with a given [magnitude]
 * in terms of the unit expression [VoltPerMetre].
 */
class ElectricFieldStrength(
    magnitude: BigDecimal,
    expression: VoltPerMetre
) : Measure<VoltPerMetre, ElectricFieldStrength>(magnitude, expression, ::ElectricFieldStrength) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Quotient(Volt(prefix), Metre()))
}
