package org.kisu.units.special

import org.kisu.Magnitude
import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit

/**
 * Represents the physical quantity of **electric charge**, measured in [Coulomb].
 *
 * Electric charge quantifies how much electrically active matter or imbalance is
 * present. It is the conserved quantity behind electrostatics, current flow, and the
 * behavior of capacitors, batteries, and charged particles.
 *
 * Typical examples include the charge stored on a capacitor plate, the charge moved
 * through a circuit during a pulse, or the charge associated with ions in an
 * electrochemical process.
 *
 * The canonical SI unit is the [Coulomb] (`C`), often written as `mC` or `µC` for
 * laboratory-scale quantities.
 */
class ElectricCharge internal constructor(magnitude: Magnitude, expression: Coulomb) :
    Measure<Coulomb, ElectricCharge>(magnitude, expression, ::ElectricCharge) {

    internal constructor(magnitude: Magnitude, prefix: Metric = Metric.BASE) :
        this(magnitude, Coulomb(prefix))
}

/**
 * Represents the unit **coulomb** (`C`), used to express [ElectricCharge].
 *
 * A coulomb quantifies the amount of electric charge. One coulomb is the charge
 * transported by a current of one ampere flowing for one second.
 *
 * This unit appears in electrostatics, capacitor calculations, battery analysis, and
 * any context where the quantity of charge itself matters rather than only current or
 * voltage.
 *
 * In unit form, `C = A·s`.
 *
 * @see ElectricCharge
 * @see Volt
 * @see Farad
 */
class Coulomb private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Coulomb>(algebra, prefix, unit, ::Coulomb) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for coulomb: "C". */
        internal val UNIT = Unit("C", 1)
    }
}
