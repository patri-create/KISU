package org.kisu.units.special

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import org.kisu.units.scales.ExponentialScale
import org.kisu.units.scales.Scale
import java.math.BigDecimal

/**
 * Represents the physical quantity of **catalytic activity**, measured in [Katal].
 *
 * Catalytic activity quantifies how rapidly a catalyst converts reactants into
 * products, expressed as amount of substance transformed per unit time.
 *
 * This quantity is used in enzymology, clinical chemistry, and biochemical process
 * analysis, for example when reporting the activity of an enzyme in blood serum or the
 * performance of a catalyst in an industrial reactor.
 *
 * The canonical SI unit is the [Katal] (`kat`), with smaller metric forms often used
 * in laboratory work.
 */
class CatalyticActivity internal constructor(magnitude: BigDecimal, expression: Katal) :
    Measure<Katal, CatalyticActivity>(magnitude, expression, ::CatalyticActivity) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, Katal(prefix))
}

/**
 * Represents the unit **katal** (`kat`), used to express [CatalyticActivity].
 *
 * A katal quantifies how quickly a catalyst converts reactants into products. One
 * katal corresponds to a catalytic process that transforms one mole of substance per
 * second.
 *
 * This unit appears in enzymology, clinical chemistry, and biochemical process
 * analysis, where reaction rate is tied to the amount of substance transformed over
 * time.
 *
 * In unit form, `kat = mol·s⁻¹`.
 *
 * @see CatalyticActivity
 */
class Katal private constructor(
    scale: Scale<Metric> = ExponentialScale(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, Katal>(scale, prefix, unit, ::Katal) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        /** The canonical symbol for katal: "kat". */
        internal val UNIT = Unit("kat", 1)
    }
}
