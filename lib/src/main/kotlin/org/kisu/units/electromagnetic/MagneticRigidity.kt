package org.kisu.units.electromagnetic

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.representation.Product
import org.kisu.units.special.Tesla
import java.math.BigDecimal

/**
 * Represents the SI unit **tesla metre (T·m)**.
 *
 * This unit measures **magnetic rigidity**, i.e., the resistance of a charged
 * particle to deflection by a magnetic field.
 * It is defined as the [Product] of [Tesla] (magnetic flux density) and [Metre] (length).
 *
 * Example usages include:
 * - Calculating particle trajectory bending in magnetic fields
 * - Designing magnetic spectrometers and accelerator beamlines
 *
 * @see MagneticRigidity for the physical quantity represented by this unit.
 */
typealias TeslaMetre = Product<Tesla, Metre>

/**
 * Represents **magnetic rigidity**, which quantifies the resistance of a charged
 * particle to deflection by a magnetic field.
 *
 * - **Dimension**: magnetic flux density × length (T·m)
 * - **SI Unit**: tesla metre (T·m)
 *
 * Magnetic rigidity is particularly used in **particle physics** and
 * **accelerator physics** to describe how strongly a particle’s trajectory
 * bends in a magnetic field. It is related to the particle’s momentum (p) and
 * charge (q) by:
 *
 *     R = p / q
 *
 * Example usages include:
 * - Designing beamlines in particle accelerators
 * - Calculating deflection in magnetic spectrometers
 * - Analyzing charged particle trajectories in magnetic fields
 *
 * @param magnitude numerical value of the measure
 * @param expression unit expression in tesla metre (T·m)
 */
class MagneticRigidity(
    magnitude: BigDecimal,
    expression: TeslaMetre
) : Measure<TeslaMetre, MagneticRigidity>(magnitude, expression, ::MagneticRigidity) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Product(Tesla(prefix), Metre()))
}
