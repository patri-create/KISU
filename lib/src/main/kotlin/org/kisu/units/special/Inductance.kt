package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **inductance**, measured in henries (H).
 *
 * One henry is the inductance of a circuit in which a current change of one ampere per second induces
 * an electromotive force of one volt. In SI base units, it is kg·m²·s⁻²·A⁻².
 *
 * Henries are used to describe the inductive properties of coils and circuits.
 *
 * This class expresses inductance as a combination of a [magnitude] and an [expression], supporting values such as
 * millihenries (mH), microhenries (µH), or kilohenries (kH).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Inductance internal constructor(magnitude: BigDecimal, expression: Henry) :
    Measure<Henry, Inductance>(magnitude, expression, ::Inductance) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Henry(prefix))
}

/**
 * Represents the SI derived unit of inductance: **henry** (H).
 *
 * One henry is the inductance of a closed circuit in which an electromotive force
 * of one volt is produced when the electric current changes uniformly at one ampere per second.
 *
 * SI definition: `H = m²·kg·s⁻²·A⁻²`.
 */
class Henry private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, Henry>(prefix, overflow, unit, ::Henry) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The canonical symbol for henry: "H". */
        internal val UNIT = Unit("H", 1)
    }
}
