package org.kisu.units.electromagnetic

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.representation.Quotient
import org.kisu.units.special.Farad
import java.math.BigDecimal

/**
 * Represents the SI unit **farad per metre (F/m)**.
 *
 * This unit measures **electric permittivity**, i.e., the ability of a material
 * to permit the formation of an electric field within it.
 * It is defined as the [Quotient] of [Farad] (capacitance) and [Metre] (length).
 *
 * Example usages include:
 * - Characterizing dielectric materials
 * - Designing capacitors and insulating systems
 *
 * @see Permittivity for the physical quantity represented by this unit.
 */
typealias FaradPerMetre = Quotient<Farad, Metre>

/**
 * Represents **electric permittivity** (ε), a measure of a material's ability
 * to permit the formation of an electric field within it.
 *
 * - **Dimension**: capacitance per length (F/m)
 * - **SI Unit**: farad per metre (F/m)
 *
 * Permittivity relates the electric displacement field (D) to the electric
 * field strength (E) in a medium:
 *
 *     D = ε * E
 *
 * Example usages include:
 * - Characterizing dielectric materials
 * - Designing capacitors and insulating materials
 * - Modeling electromagnetic wave propagation in media
 *
 * @param magnitude numerical value of the measure
 * @param expression unit expression in farad per metre (F/m)
 */
class Permittivity(
    magnitude: BigDecimal,
    expression: FaradPerMetre
) : Measure<FaradPerMetre, Permittivity>(magnitude, expression, ::Permittivity) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Quotient(Farad(prefix), Metre()))
}
