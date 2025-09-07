package org.kisu.units.electromagnetic

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Henry
import java.math.BigDecimal

/**
 * Represents **magnetic susceptibility** (χ), a dimensionless quantity that
 * describes how much a material becomes magnetized in response to an applied
 * magnetic field.
 *
 * - **Dimension**: length per inductance (m/H)
 * - **SI Unit**: metre per henry (m/H)
 *
 * Magnetic susceptibility quantifies the ease with which a material can be
 * magnetized and is used to classify materials as **diamagnetic, paramagnetic, or ferromagnetic**.
 *
 * Example usages include:
 * - Determining the response of materials in magnetic fields
 * - Calculating magnetic polarization and permeability
 * - Material characterization in physics and engineering
 *
 * @param magnitude numerical value of the measure
 * @param expression unit expression in metre per henry (m/H)
 */
class MagneticSusceptibility(
    magnitude: BigDecimal,
    expression: MetrePerHenry
) : Measure<MagneticSusceptibility.MetrePerHenry, MagneticSusceptibility>(
    magnitude = magnitude,
    expression = expression,
    create = ::MagneticSusceptibility
) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Quotient(Metre(prefix), Henry()))

    /**
     * Represents the SI unit **metre per henry (m/H)**.
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
}
