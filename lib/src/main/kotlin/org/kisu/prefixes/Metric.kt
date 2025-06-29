package org.kisu.prefixes

import org.kisu.prefixes.primitives.Representation
import org.kisu.prefixes.primitives.StandardSystem
import org.kisu.prefixes.primitives.Symbol
import org.kisu.prefixes.primitives.System
import java.math.BigDecimal
import java.math.BigInteger

/**
 * The `Metric` prefix system defines unit prefixes based on powers of 10 (10ⁿ), standardized by the International
 * System of Units (SI).
 *
 * These prefixes are used to express decimal multiples and submultiples of base units such as meters, grams, liters,
 * and more.
 *
 * They are essential for representing very large or very small quantities in science, engineering, and daily life.
 *
 * ### Use in Science, Engineering, and Everyday Measurements
 * Metric prefixes are foundational in the metric system and appear in countless contexts:
 *
 * - **Distance**: kilometers (km), centimeters (cm), nanometers (nm)
 * - **Mass**: milligrams (mg), kilograms (kg), megagrams (Mg)
 * - **Volume**: milliliters (mL), liters (L), megaliters (ML)
 * - **Electricity**: microamperes (μA), kilovolts (kV)
 *
 * The system provides a uniform, scalable, and intuitive way to denote values across magnitudes ranging from `10⁻³⁰`
 * (quecto) to `10³⁰` (quetta).
 *
 * ### Relationship to Other Systems
 * Unlike the **Decimal** prefix system used in some computing contexts, and the **Binary** system used in memory
 * representation, the **Metric** system is strictly decimal and applies across both scientific and non-scientific
 * domains.
 *
 * It should not be confused with the use of SI prefixes like "kilo" and "mega" to denote binary magnitudes (as in
 * `1 kilobyte = 1024 bytes`), which has led to confusion.
 *
 * For clarity, binary-specific prefixes like `kibi` (2¹⁰), `mebi` (2²⁰), etc., are standardized separately.
 */
@Suppress("MagicNumber", "DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE")
enum class Metric(
    override val factor: BigDecimal,
    symbol: String,
) : Prefix<Metric>,
    System<Metric> by StandardSystem(Metric::class),
    Symbol by Representation(symbol) {
    /** 10⁻³⁰ = 0.000000000000000000000000000001 */
    QUECTO(BigDecimal(BigInteger("1"), 30), "q"),

    /** 10⁻²⁷ = 0.000000000000000000000000001 */
    RONTO(BigDecimal(BigInteger("1"), 27), "r"),

    /** 10⁻²⁴ = 0.000000000000000000000001 */
    YOCTO(BigDecimal(BigInteger("1"), 24), "y"),

    /** 10⁻²¹ = 0.000000000000000000001 */
    ZEPTO(BigDecimal(BigInteger("1"), 21), "z"),

    /** 10⁻¹⁸ = 0.000000000000000001 */
    ATTO(BigDecimal(BigInteger("1"), 18), "a"),

    /** 10⁻¹⁵ = 0.000000000000001 */
    FEMTO(BigDecimal(BigInteger("1"), 15), "f"),

    /** 10⁻¹² = 0.000000000001 */
    PICO(BigDecimal(BigInteger("1"), 12), "p"),

    /** 10⁻⁹ = 0.000000001 */
    NANO(BigDecimal(BigInteger("1"), 9), "n"),

    /** 10⁻⁶ = 0.000001 */
    MICRO(BigDecimal(BigInteger("1"), 6), "μ"),

    /** 10⁻³ = 0.001 */
    MILLI(BigDecimal(BigInteger("1"), 3), "m"),

    /** 10⁻² = 0.01 */
    CENTI(BigDecimal(BigInteger("1"), 2), "c"),

    /** 10⁻¹ = 0.1 */
    DECI(BigDecimal(BigInteger("1"), 1), "d"),

    /** 10⁰ = 1 */
    BASE(BigDecimal.ONE, ""),

    /** 10¹ = 10 */
    DECA(1, "da"),

    /** 10² = 100 */
    HECTO(2, "h"),

    /** 10³ = 1,000 */
    KILO(3, "k"),

    /** 10⁶ = 1,000,000 */
    MEGA(6, "M"),

    /** 10⁹ = 1,000,000,000 */
    GIGA(9, "G"),

    /** 10¹² = 1,000,000,000,000 */
    TERA(12, "T"),

    /** 10¹⁵ = 1,000,000,000,000,000 */
    PETA(15, "P"),

    /** 10¹⁸ = 1,000,000,000,000,000,000 */
    EXA(18, "E"),

    /** 10²¹ = 1,000,000,000,000,000,000,000 */
    ZETTA(21, "Z"),

    /** 10²⁴ = 1,000,000,000,000,000,000,000,000 */
    YOTTA(24, "Y"),

    /** 10²⁷ = 1,000,000,000,000,000,000,000,000,000 */
    RONNA(27, "R"),

    /** 10³⁰ = 1,000,000,000,000,000,000,000,000,000,000 */
    QUETTA(30, "Q"),
    ;

    constructor(power: Int, symbol: String) : this(factor = BigDecimal.TEN.pow(power), symbol)
}
