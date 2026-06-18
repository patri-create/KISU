package org.kisu.units.electromagnetic

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.electromagnetic.MagneticSusceptibility.Companion.MetrePerHenry
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Henry

/**
 * Represents the physical quantity of **magnetic susceptibility**, measured in
 * [MetrePerHenry] in this library.
 *
 * Magnetic susceptibility quantifies how strongly a material becomes magnetized when an
 * external magnetic field is applied. It is widely used to compare diamagnetic,
 * paramagnetic, and ferromagnetic responses.
 *
 * The associated unit representation is [MetrePerHenry] (`m/H`) as modeled by this API.
 */
class MagneticSusceptibility(
    magnitude: Magnitude,
    expression: MetrePerHenry
) : Measure<MagneticSusceptibility.MetrePerHenry, MagneticSusceptibility>(
    magnitude = magnitude,
    expression = expression,
    create = ::MagneticSusceptibility
) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, MetrePerHenry(prefix))

    /**
     * Returns the magnetic permeability associated with this susceptibility by inverting its canonical magnitude.
     */
    val magneticPermittivity: MagneticPermittivity
        get() = MagneticPermittivity(canonical.component1().inverted)

    /**
     * Represents the library unit form **metre per henry (m/H)** used by this type.
     *
     * This unit measures **magnetic susceptibility**, i.e., the degree to which
     * a material can be magnetized in response to an applied magnetic field.
     * It is defined as the [Quotient] of [Metre] (length) and [Henry] (inductance).
     *
     * Example usages include:
     * - Characterizing diamagnetic, paramagnetic, or ferromagnetic materials
     * - Modeling magnetic response in materials and circuits
     *
     * @see MagneticSusceptibility for the physical quantity represented by this unit.
     */
    typealias MetrePerHenry = Quotient<Metre, Henry>

    companion object {
        /**
         * Creates a [MetrePerHenry] expression for **metre per henry** (`m/H`).
         *
         * @param prefix Metric prefix applied to the metre unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [MetrePerHenry] expression for `m/H`.
         */
        @Suppress("FunctionNaming")
        internal fun MetrePerHenry(prefix: Metric = Metric.BASE): MetrePerHenry =
            Quotient(Metre(prefix), Henry())
    }
}
