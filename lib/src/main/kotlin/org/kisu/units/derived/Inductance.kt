package org.kisu.units.derived

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import java.math.BigDecimal

/**
 * Represents the physical quantity of **inductance**, measured in henries (H).
 *
 * One henry is the inductance of a circuit in which a current change of one ampere per second induces
 * an electromotive force of one volt. In SI base units, it is kg·m²·s⁻²·A⁻².
 *
 * Henries are used to describe the inductive properties of coils and circuits.
 *
 * This class expresses inductance as a combination of a [magnitude] and a [prefix], supporting values such as
 * millihenries (mH), microhenries (µH), or kilohenries (kH).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Inductance internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
    Measure<Metric, Inductance>(magnitude, prefix, SYMBOL, ::Inductance) {

    companion object {
        /** The SI symbol for inductance: "H" (henry). */
        private const val SYMBOL = "H"
    }
}
