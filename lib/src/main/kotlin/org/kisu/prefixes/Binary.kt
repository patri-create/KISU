package org.kisu.prefixes

import java.math.BigDecimal

enum class Binary(
    override val base: BigDecimal,
    override val power: Int,
    override val symbol: String
) : Prefix {
    BASE(BINARY_BASE, 0, ""),
    KIBI(BINARY_BASE, 10, "Ki"),
    KILO(BINARY_BASE, 10, "K"),
    MEBI(BINARY_BASE, 20, "Mi"),
    MEGA(BINARY_BASE, 20, "M"),
    GIBI(BINARY_BASE, 30, "Gi"),
    GIGA(BINARY_BASE, 30, "G"),
    TEBI(BINARY_BASE, 40, "Ti"),
    PEBI(BINARY_BASE, 50, "Pi"),
    EXBI(BINARY_BASE, 60, "Ei"),
    ZEBI(BINARY_BASE, 70, "Zi"),
    YOBI(BINARY_BASE, 80, "Yi"),
    ROBI(BINARY_BASE, 90, "Ri"),
    QUEBI(BINARY_BASE, 100, "Qi");
}

private val BINARY_BASE = BigDecimal.valueOf(2.0)