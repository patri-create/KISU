package org.kisu.units.electromagnetic

import org.kisu.units.Measure
import org.kisu.units.base.Metre
import org.kisu.units.representation.Product
import org.kisu.units.special.Weber
import java.math.BigDecimal

/**
 * Represents the SI unit **weber metre (Wb·m)**.
 *
 * This unit measures **magnetic moment**, i.e., the strength of a magnet or
 * current loop in terms of magnetic flux times length.
 * It is defined as the [Product] of [Weber] (magnetic flux) and [Metre] (length).
 *
 * Example usages include:
 * - Calculating magnetic moments of coils or permanent magnets
 * - Modeling torque and energy in magnetic systems
 *
 * @see MagneticMoment for the physical quantity represented by this unit.
 */
typealias WeberMetre = Product<Weber, Metre>

/**
 * Represents the **magnetic moment** (μ), a vector quantity that measures
 * the strength and orientation of a magnet or current loop.
 *
 * - **Dimension**: magnetic flux × length (Wb·m)
 * - **SI Unit**: weber metre (Wb·m)
 *
 * The magnetic moment quantifies how an object interacts with a magnetic field,
 * including torque and potential energy in the field.
 * It is widely used in **magnetostatics, electromagnetism, and materials science**.
 *
 * Example usages include:
 * - Calculating torque on a current loop in a magnetic field
 * - Describing the magnetic properties of materials
 * - Modeling atomic or molecular magnetic moments
 *
 * @param magnitude numerical value of the measure
 * @param expression unit expression in weber metre (Wb·m)
 */
class MagneticMoment(
    magnitude: BigDecimal,
    expression: WeberMetre
) : Measure<WeberMetre, MagneticMoment>(magnitude, expression, ::MagneticMoment)
