package org.kisu.units.photometric

import org.kisu.units.Measure
import org.kisu.units.base.Candela
import org.kisu.units.representation.Quotient
import org.kisu.units.special.SquareMetre
import java.math.BigDecimal

typealias CandelaPerSquareMetre = Quotient<Candela, SquareMetre>

/**
 * Represents **luminance**.
 *
 * Luminance is the measure of the luminous intensity emitted,
 * transmitted, or reflected from a surface in a given direction
 * per unit area.
 *
 * Expressed in **candelas per square metre (cd/m²)**, also known as
 * **nits** in display technology.
 *
 * It quantifies how bright a surface will appear to the human eye,
 * making it a central concept in **optics, lighting engineering, and
 * display calibration**.
 *
 * @constructor Creates a luminance measure with the given [magnitude]
 * and [expression].
 * @param magnitude The numeric value of the measure.
 * @param expression The unit expression in candelas per square metre.
 */
class Luminance(
    magnitude: BigDecimal,
    expression: CandelaPerSquareMetre
) : Measure<CandelaPerSquareMetre, Luminance>(magnitude, expression, ::Luminance)
