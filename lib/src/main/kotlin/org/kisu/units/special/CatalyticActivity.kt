package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents the physical quantity of **catalytic activity**, measured in katals (kat).
 *
 * One katal corresponds to the catalytic activity that converts one mole of substrate per second.
 * In SI base units, it is mol·s⁻¹.
 *
 * Katals are used in biochemistry and enzymology to quantify the rate of enzymatic reactions.
 *
 * This class expresses catalytic activity as a combination of a [magnitude] and an [expression], supporting values
 * such as millikatals (mkat), microkatals (µkat), or kilokatals (kkat).
 *
 * Instances of this class are immutable and use [BigDecimal] for precision.
 */
class CatalyticActivity internal constructor(magnitude: BigDecimal, expression: Katal) :
    Measure<Katal, CatalyticActivity>(magnitude, expression, ::CatalyticActivity) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Katal(prefix))
}

/**
 * Represents the SI derived unit of catalytic activity: **katal** (kat).
 *
 * One katal is equal to one mole per second.
 *
 * SI definition: `kat = mol·s⁻¹`.
 */
class Katal private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, Katal>(prefix, overflow, unit, ::Katal) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        /** The canonical symbol for katal: "kat". */
        internal val UNIT = Unit("kat", 1)
    }
}
