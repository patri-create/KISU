package org.kisu.prefixes

enum class Decimal(
    override val base: Double,
    override val power: Int,
    override val symbol: String
) : Prefix {
    BASE(DECIMAL_BASE, 0, ""),
    KILO(DECIMAL_BASE, 1, "k"),
    MEGA(DECIMAL_BASE, 2, "M"),
    GIGA(DECIMAL_BASE, 3, "G"),
    TERA(DECIMAL_BASE, 4, "T"),
    PETA(DECIMAL_BASE, 5, "P"),
    EXA(DECIMAL_BASE, 6, "E"),
    ZETTA(DECIMAL_BASE, 7, "Z"),
    YOTTA(DECIMAL_BASE, 8, "Y"),
    RONNA(DECIMAL_BASE, 9, "R"),
    QUETTA(DECIMAL_BASE, 10, "Q");
}

private const val DECIMAL_BASE = 1000.0