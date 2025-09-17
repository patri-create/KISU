package org.kisu.units.electromagnetic

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Joule
import org.kisu.units.special.Tesla
import java.math.BigDecimal

/**
 * Represents the **magnetic dipole moment** (m), a vector quantity that measures
 * the strength and orientation of a magnetic source.
 *
 * - **Dimension**: energy per magnetic flux density (J/T)
 * - **SI Unit**: joule per tesla (J/T), which is equivalent to ampere·square metre (A·m²)
 *
 * The magnetic dipole moment describes how strongly an object (such as a current loop,
 * magnet, or particle with spin) interacts with an external magnetic field.
 * It is central to the study of **magnetostatics, electromagnetism, and quantum physics**.
 *
 * @param magnitude numerical value of the measure
 * @param expression unit expression in joule per tesla (J/T)
 */
class MagneticDipoleMoment(
    magnitude: BigDecimal,
    expression: JoulePerTesla
) : Measure<MagneticDipoleMoment.JoulePerTesla, MagneticDipoleMoment>(
    magnitude = magnitude,
    expression = expression,
    create = ::MagneticDipoleMoment
) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
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
         * Creates a measure of **joules per tesla** (J/T).
         *
         * This derived unit expresses **magnetic moment** —
         * the amount of torque a magnetic object experiences per unit magnetic flux density.
         *
         * Internally this returns a [Quotient] of:
         *  - a [Joule] (energy) with the specified [prefix]
         *  - divided by a [Tesla] (magnetic flux density)
         *
         * @param prefix Metric prefix to apply to the joule unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return A [JoulePerTesla] representing J/T.
         */
        @Suppress("FunctionNaming")
        internal fun JoulePerTesla(prefix: Metric = Metric.BASE): JoulePerTesla =
            Quotient(Joule(prefix), Tesla())
    }
}
