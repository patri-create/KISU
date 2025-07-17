package org.kisu.prefixes

import org.kisu.prefixes.primitives.EnumSystem
import org.kisu.prefixes.primitives.Representation
import org.kisu.prefixes.primitives.Symbol
import org.kisu.prefixes.primitives.System
import java.math.BigDecimal

/**
 * The `Decimal` prefix system defines unit prefixes based on powers of 1000 (10³).
 *
 * ### Use in Information Technology
 * In information technology, decimal (SI) prefixes are commonly used to describe data transfer rates, storage device
 * capacities, and other measurements where consistency with the metric system is expected:
 *
 * - **Storage devices**: A "500 GB" hard drive typically refers to 500 × 10⁹ bytes (500,000,000,000 bytes).
 * - **Network bandwidth**: "100 Mbps" usually means 100 × 10⁶ bits per second.
 *
 * ### Discrepancy Between Decimal and [Binary] Prefixes
 * When SI prefixes like "kilo" or "mega" are interpreted as binary (e.g., 2¹⁰ instead of 10³), the difference in value
 * increases with the size of the prefix:
 *
 * - For `kilo` (10³) vs. `kibi` (2¹⁰), the relative difference is approximately **2.4%**.
 * - For `quetta` (10³⁰) vs. `quebi` (2¹⁰⁰), the discrepancy grows to nearly **27%**.
 *
 * This growing gap reinforces the need to use the correct system—**decimal for standard measurements**, and **binary
 * for addressing and memory**—to avoid confusion.
 */
@Suppress("MagicNumber", "DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE")
enum class Decimal(
    override val factor: BigDecimal,
    symbol: String,
) : Prefix<Decimal>,
    System<Decimal> by EnumSystem(Decimal::class),
    Symbol by Representation(symbol) {
    /** 1000⁰ = 1 */
    BASE(BigDecimal.ONE, ""),

    /** 1000¹ = 1,000 */
    KILO(3, "k"),

    /** 1000² = 1,000,000 */
    MEGA(6, "M"),

    /** 1000³ = 1,000,000,000 */
    GIGA(9, "G"),

    /** 1000⁴ = 1,000,000,000,000 */
    TERA(12, "T"),

    /** 1000⁵ = 1,000,000,000,000,000 */
    PETA(15, "P"),

    /** 1000⁶ = 1,000,000,000,000,000,000 */
    EXA(18, "E"),

    /** 1000⁷ = 1,000,000,000,000,000,000,000 */
    ZETTA(21, "Z"),

    /** 1000⁸ = 1,000,000,000,000,000,000,000,000 */
    YOTTA(24, "Y"),

    /** 1000⁹ = 1,000,000,000,000,000,000,000,000,000 */
    RONNA(27, "R"),

    /** 1000¹⁰ = 1,000,000,000,000,000,000,000,000,000,000 */
    QUETTA(30, "Q"),
    ;

    constructor(power: Int, symbol: String) : this(factor = BigDecimal.TEN.pow(power), symbol)
}
