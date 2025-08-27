package org.kisu.units.mechanics

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Second
import org.kisu.units.representation.Product
import org.kisu.units.special.Pascal
import java.math.BigDecimal

/**
 * Unit of [DynamicViscosity].
 *
 * Represents the unit of **dynamic viscosity**, i.e., the physical quantity measuring
 * a fluid's resistance to flow under an applied shear stress.
 *
 * Symbol: `Pa·s`
 * SI: `kg·m⁻¹·s⁻¹`
 *
 * @see DynamicViscosity
 */
typealias PascalSecond = Product<Pascal, Second>

/**
 * Measure of dynamic viscosity expressed in [PascalSecond].
 *
 * Dynamic viscosity quantifies a fluid's internal resistance to motion
 * when subjected to a shear force.
 *
 * Common applications include:
 * - Fluid mechanics (laminar and turbulent flow calculations)
 * - Chemical engineering (viscosity of liquids and gases)
 * - Lubrication studies (engine oils and greases)
 *
 * @property magnitude Numerical value of the dynamic viscosity.
 * @property expression Unit of the dynamic viscosity, here [PascalSecond].
 *
 * @see PascalSecond
 */
class DynamicViscosity(
    magnitude: BigDecimal,
    expression: PascalSecond
) : Measure<PascalSecond, DynamicViscosity>(magnitude, expression, ::DynamicViscosity) {
    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Product(Pascal(prefix), Second()))
}
