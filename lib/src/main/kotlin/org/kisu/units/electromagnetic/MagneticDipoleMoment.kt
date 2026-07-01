@file:Suppress("TooManyFunctions")

package org.kisu.units.electromagnetic

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.electromagnetic.MagneticDipoleMoment.Companion.JoulePerTesla
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Joule
import org.kisu.units.special.Tesla

/**
 * Represents the physical quantity of **magnetic dipole moment**, measured in
 * [JoulePerTesla].
 *
 * Magnetic dipole moment quantifies the strength and orientation of a magnetic source.
 * It describes how strongly a current loop, permanent magnet, or microscopic magnetic
 * dipole interacts with an external magnetic field.
 *
 * This quantity is central in magnetostatics, materials science, and quantum physics.
 *
 * The associated unit representation is [JoulePerTesla] (`J/T`), equivalent to `A·m²`.
 */
class MagneticDipoleMoment(
    magnitude: Magnitude,
    expression: JoulePerTesla
) : Measure<MagneticDipoleMoment.JoulePerTesla, MagneticDipoleMoment>(
    magnitude = magnitude,
    expression = expression,
    create = ::MagneticDipoleMoment
) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, JoulePerTesla(prefix))

    /**
     * Represents the SI unit **joule per tesla (J/T)**.
     *
     * This unit measures **magnetic dipole moment**, i.e., the torque a magnetic
     * source experiences in a magnetic field per unit field strength.
     * It is defined as the [Quotient] of [Joule] (energy) and [Tesla] (magnetic flux density).
     *
     * Example usages include:
     * - Quantifying the magnetic dipole moment of magnets or current loops
     * - Modeling interactions of magnetic moments with external magnetic fields
     *
     * @see MagneticDipoleMoment for the physical quantity represented by this unit.
     */
    typealias JoulePerTesla = Quotient<Joule, Tesla>

    companion object {
        /**
         * Creates a [JoulePerTesla] expression for **joule per tesla** (`J/T`).
         *
         * @param prefix Metric prefix applied to the joule unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [JoulePerTesla] expression for `J/T`.
         */
        @Suppress("FunctionNaming")
        internal fun JoulePerTesla(prefix: Metric = Metric.BASE): JoulePerTesla =
            Quotient(Joule(prefix), Tesla())
    }

    // Dimension-aware arithmetic
    /**
     * Divides this [MagneticDipoleMoment] by [Current][org.kisu.units.base.Current],
     * yielding [Area][org.kisu.units.special.Area].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.base.Current
    ): org.kisu.units.special.Area =
        org.kisu.units.special.Area(canonical.component1() / other.canonical.component1())

    /**
     * Divides this [MagneticDipoleMoment] by [Area][org.kisu.units.special.Area],
     * yielding [Current][org.kisu.units.base.Current].
     *
     * Both operands are converted to their canonical units before the division result is calculated.
     */
    operator fun div(
        other: org.kisu.units.special.Area
    ): org.kisu.units.base.Current =
        org.kisu.units.base.Current(canonical.component1() / other.canonical.component1())
}
