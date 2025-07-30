package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **illuminance**, measured in lux (lx).
 *
 * One lux is equal to one lumen per square meter. It measures the amount of visible light falling on a surface.
 * In SI base units, it is cd·sr·m⁻².
 *
 * Lux are commonly used to quantify lighting conditions in indoor and outdoor environments.
 *
 * This class expresses illuminance as a combination of a [magnitude] and a [prefix], supporting values such as
 * millilux (mlx), kilolux (klx), or megalux (Mlx).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class Illuminance internal constructor(magnitude: BigDecimal, expression: Scalar<Metric>) :
    Measure<Scalar<Metric>, Illuminance>(magnitude, expression, ::Illuminance) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Scalar(prefix, unit = UNIT))

    companion object {
        /** The SI symbol for illuminance: "lx" (lux). */
        internal val UNIT = Unit("lx", 1)
    }
}
