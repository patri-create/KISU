package org.kisu.units.builders

import org.kisu.prefixes.Metric
import java.math.BigDecimal

/** Builder for quantities with the 'quecto' (10⁻³⁰) metric prefix. */
@JvmInline
value class QuectoBuilder(override val magnitude: BigDecimal) : MetricUnitBuilder {
    override val metric: Metric
        get() = Metric.QUECTO
}

/** Builder for quantities with the 'ronto' (10⁻²⁷) metric prefix. */
@JvmInline
value class RontoBuilder(override val magnitude: BigDecimal) : MetricUnitBuilder {
    override val metric: Metric
        get() = Metric.RONTO
}

/** Builder for quantities with the 'yocto' (10⁻²⁴) metric prefix. */
@JvmInline
value class YoctoBuilder(override val magnitude: BigDecimal) : MetricUnitBuilder {
    override val metric: Metric
        get() = Metric.YOCTO
}

/** Builder for quantities with the 'zepto' (10⁻²¹) metric prefix. */
@JvmInline
value class ZeptoBuilder(override val magnitude: BigDecimal) : MetricUnitBuilder {
    override val metric: Metric
        get() = Metric.ZEPTO
}

/** Builder for quantities with the 'atto' (10⁻¹⁸) metric prefix. */
@JvmInline
value class AttoBuilder(override val magnitude: BigDecimal) : MetricUnitBuilder {
    override val metric: Metric
        get() = Metric.ATTO
}

/** Builder for quantities with the 'femto' (10⁻¹⁵) metric prefix. */
@JvmInline
value class FemtoBuilder(override val magnitude: BigDecimal) : MetricUnitBuilder {
    override val metric: Metric
        get() = Metric.FEMTO
}

/** Builder for quantities with the 'pico' (10⁻¹²) metric prefix. */
@JvmInline
value class PicoBuilder(override val magnitude: BigDecimal) : MetricUnitBuilder {
    override val metric: Metric
        get() = Metric.PICO
}

/** Builder for quantities with the 'nano' (10⁻⁹) metric prefix. */
@JvmInline
value class NanoBuilder(override val magnitude: BigDecimal) : MetricUnitBuilder {
    override val metric: Metric
        get() = Metric.NANO
}

/** Builder for quantities with the 'micro' (10⁻⁶) metric prefix. */
@JvmInline
value class MicroBuilder(override val magnitude: BigDecimal) : MetricUnitBuilder {
    override val metric: Metric
        get() = Metric.MICRO
}

/** Builder for quantities with the 'milli' (10⁻³) metric prefix. */
@JvmInline
value class MilliBuilder(override val magnitude: BigDecimal) : MetricUnitBuilder {
    override val metric: Metric
        get() = Metric.MILLI
}

/** Builder for quantities with the 'centi' (10⁻²) metric prefix. */
@JvmInline
value class CentiBuilder(override val magnitude: BigDecimal) : MetricUnitBuilder {
    override val metric: Metric
        get() = Metric.CENTI
}

/** Builder for quantities with the 'deci' (10⁻¹) metric prefix. */
@JvmInline
value class DeciBuilder(override val magnitude: BigDecimal) : MetricUnitBuilder {
    override val metric: Metric
        get() = Metric.DECI
}

/** Builder for quantities with the 'deca' (10¹) metric prefix. */
@JvmInline
value class DecaBuilder(override val magnitude: BigDecimal) : MetricUnitBuilder {
    override val metric: Metric
        get() = Metric.DECA
}

/** Builder for quantities with the 'hecto' (10²) metric prefix. */
@JvmInline
value class HectoBuilder(override val magnitude: BigDecimal) : MetricUnitBuilder {
    override val metric: Metric
        get() = Metric.HECTO
}

/** Builder for quantities with the 'kilo' (10³) metric prefix. */
@JvmInline
value class KiloBuilder(override val magnitude: BigDecimal) : MetricUnitBuilder {
    override val metric: Metric
        get() = Metric.KILO
}

/** Builder for quantities with the 'mega' (10⁶) metric prefix. */
@JvmInline
value class MegaBuilder(override val magnitude: BigDecimal) : MetricUnitBuilder {
    override val metric: Metric
        get() = Metric.MEGA
}

/** Builder for quantities with the 'giga' (10⁹) metric prefix. */
@JvmInline
value class GigaBuilder(override val magnitude: BigDecimal) : MetricUnitBuilder {
    override val metric: Metric
        get() = Metric.GIGA
}

/** Builder for quantities with the 'tera' (10¹²) metric prefix. */
@JvmInline
value class TeraBuilder(override val magnitude: BigDecimal) : MetricUnitBuilder {
    override val metric: Metric
        get() = Metric.TERA
}

/** Builder for quantities with the 'peta' (10¹⁵) metric prefix. */
@JvmInline
value class PetaBuilder(override val magnitude: BigDecimal) : MetricUnitBuilder {
    override val metric: Metric
        get() = Metric.PETA
}

/** Builder for quantities with the 'exa' (10¹⁸) metric prefix. */
@JvmInline
value class ExaBuilder(override val magnitude: BigDecimal) : MetricUnitBuilder {
    override val metric: Metric
        get() = Metric.EXA
}

/** Builder for quantities with the 'zetta' (10²¹) metric prefix. */
@JvmInline
value class ZettaBuilder(override val magnitude: BigDecimal) : MetricUnitBuilder {
    override val metric: Metric
        get() = Metric.ZETTA
}

/** Builder for quantities with the 'yotta' (10²⁴) metric prefix. */
@JvmInline
value class YottaBuilder(override val magnitude: BigDecimal) : MetricUnitBuilder {
    override val metric: Metric
        get() = Metric.YOTTA
}

/** Builder for quantities with the 'ronna' (10²⁷) metric prefix. */
@JvmInline
value class RonnaBuilder(override val magnitude: BigDecimal) : MetricUnitBuilder {
    override val metric: Metric
        get() = Metric.RONNA
}

/** Builder for quantities with the 'quetta' (10³⁰) metric prefix. */
@JvmInline
value class QuettaBuilder(override val magnitude: BigDecimal) : MetricUnitBuilder {
    override val metric: Metric
        get() = Metric.QUETTA
}
