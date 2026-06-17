package org.kisu.units.electromagnetic

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.representation.Product
import org.kisu.units.special.Tesla

/**
 * Represents the physical quantity of **magnetic rigidity**, measured in [TeslaMetre].
 *
 * Magnetic rigidity quantifies how resistant a charged particle (or beam) is to bending by a
 * magnetic field. Larger rigidity means the trajectory is harder to deflect.
 *
 * This quantity is widely used in accelerator physics, beam transport, and magnetic
 * spectrometry.
 *
 * The associated unit representation is [TeslaMetre] (`T·m`).
 */
class MagneticRigidity(
    magnitude: Magnitude,
    expression: TeslaMetre
) : Measure<MagneticRigidity.TeslaMetre, MagneticRigidity>(magnitude, expression, ::MagneticRigidity) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, TeslaMetre(prefix))

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

    companion object {
        /**
         * Creates a [TeslaMetre] expression for **tesla metre** (`T·m`).
         *
         * @param prefix Metric prefix applied to the tesla unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [TeslaMetre] expression for `T·m`.
         */
        @Suppress("FunctionNaming")
        internal fun TeslaMetre(prefix: Metric = Metric.BASE): TeslaMetre =
            Product(Tesla(prefix), Metre())
    }
}
