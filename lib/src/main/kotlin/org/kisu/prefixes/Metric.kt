package org.kisu.prefixes

enum class Metric(
    override val base: Double,
    override val power: Int,
    override val symbol: String
) : Prefix {
    QUECTO(METRIC_BASE, -30, "q"),
    RONTO(METRIC_BASE, -27, "r"),
    YOCTO(METRIC_BASE, -24, "y"),
    ZEPTO(METRIC_BASE, -21, "z"),
    ATTO(METRIC_BASE, -18, "a"),
    FEMTO(METRIC_BASE, -15, "f"),
    PICO(METRIC_BASE, -12, "p"),
    NANO(METRIC_BASE, -9, "n"),
    MICRO(METRIC_BASE, -6, "μ"),
    MILLI(METRIC_BASE, -3, "m"),
    CENTI(METRIC_BASE, -2, "c"),
    DECI(METRIC_BASE, -1, "d"),
    BASE(METRIC_BASE, 0, ""),
    DECA(METRIC_BASE, 1, "da"),
    HECTO(METRIC_BASE, 2, "h"),
    KILO(METRIC_BASE, 3, "k"),
    MEGA(METRIC_BASE, 6, "M"),
    GIGA(METRIC_BASE, 9, "G"),
    TERA(METRIC_BASE, 12, "T"),
    PETA(METRIC_BASE, 15, "P"),
    EXA(METRIC_BASE, 18, "E"),
    ZETTA(METRIC_BASE, 21, "Z"),
    YOTTA(METRIC_BASE, 24, "Y"),
    RONNA(METRIC_BASE, 27, "R"),
    QUETTA(METRIC_BASE, 30, "Q");
}

private const val METRIC_BASE = 10.0