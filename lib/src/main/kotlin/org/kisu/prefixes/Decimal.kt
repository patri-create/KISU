package org.kisu.prefixes

import org.kisu.prefixes.primitives.*
import org.kisu.prefixes.primitives.InBase.Binary

/**
 * The `Decimal` prefix system defines unit prefixes based on powers of 1000 (10³).
 *
 * ### Use in Information Technology
 * In information technology, decimal (SI) prefixes are commonly used to describe data transfer rates, storage device
 * capacities, and other measurementswhere consistency with the metric system is expected:
 *
 * - **Storage devices**: A "500 GB" hard drive typically refers to 500 × 10⁹ bytes (500,000,000,000 bytes).
 * - **Network bandwidth**: "100 Mbps" usually means 100 × 10⁶ bits per second.
 *
 * This usage differs from [Binary], which are based on powers of 2 and are more appropriate for memory sizes and
 * precise data representation in computing systems.
 *
 * ### Discrepancy Between Decimal and [Binary] Prefixes
 * When SI prefixes like "kilo" or "mega" are interpreted as binary (e.g., 2¹⁰ instead of 10³), the difference in value
 * increases with the size of the prefix:
 * - For `kilo` (10³) vs. `kibi` (2¹⁰), the relative difference is approximately **2.4%**.
 * - For `quetta` (10³⁰) vs. `quebi` (2¹⁰⁰), the discrepancy grows to nearly **27%**.
 *
 * This growing gap reinforces the need to use the correct system—**decimal for standard measurements**, and **binary
 * for addressing and memory**—to avoid confusion.
 */
@Suppress("MagicNumber", "DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE")
enum class Decimal(
    override val power: Int,
    symbol: String,
) : Prefix,
    InBase by InBase.Decimal,
    System<Decimal> by StandardSystem(Decimal::class),
    Symbol by Representation(symbol) {

    /** Base unit */
    BASE(0, ""),

    /** Symbol: "k" — 1000¹ = 1,000 */
    KILO(1, "k"),

    /** Symbol: "M" — 1000² = 1,000,000 */
    MEGA(2, "M"),

    /** Symbol: "G" — 1000³ = 1,000,000,000 */
    GIGA(3, "G"),

    /** Symbol: "T" — 1000⁴ = 1,000,000,000,000 */
    TERA(4, "T"),

    /** Symbol: "P" — 1000⁵ = 1,000,000,000,000,000 */
    PETA(5, "P"),

    /** Symbol: "E" — 1000⁶ = 1,000,000,000,000,000,000 */
    EXA(6, "E"),

    /** Symbol: "Z" — 1000⁷ = 1,000,000,000,000,000,000,000 */
    ZETTA(7, "Z"),

    /** Symbol: "Y" — 1000⁸ = 1,000,000,000,000,000,000,000,000 */
    YOTTA(8, "Y"),

    /** Symbol: "R" — 1000⁹ = 1,000,000,000,000,000,000,000,000,000 */
    RONNA(9, "R"),

    /** Symbol: "Q" — 1000¹⁰ = 1,000,000,000,000,000,000,000,000,000,000 */
    QUETTA(10, "Q"),
}
