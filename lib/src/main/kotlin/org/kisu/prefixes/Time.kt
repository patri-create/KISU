package org.kisu.prefixes

import org.kisu.prefixes.primitives.EnumSystem
import org.kisu.prefixes.primitives.Representation
import org.kisu.prefixes.primitives.Symbol
import org.kisu.prefixes.primitives.System
import java.math.BigDecimal
import java.math.BigInteger

/**
 * Human-readable time prefixes expressed as direct factors of one second.
 *
 * Unlike [Metric], these values are not exponents. They are linear multipliers:
 * `MINUTE.factor == 60`, `HOUR.factor == 3600`, and so on.
 *
 * Month and year use fixed average Gregorian factors so time arithmetic remains deterministic.
 */
@Suppress("MagicNumber", "DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE")
enum class Time(
    override val factor: BigDecimal,
    symbol: String,
) : Prefix<Time>,
    System<Time> by EnumSystem(Time::class),
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

    /** 1 second */
    SECOND(BigDecimal.valueOf(1), "s"),

    /** 60 seconds */
    MINUTE(BigDecimal.valueOf(60), "min"),

    /** 3,600 seconds */
    HOUR(BigDecimal.valueOf(3_600), "h"),

    /** 86,400 seconds */
    DAY(BigDecimal.valueOf(86_400), "day"),

    /** 604,800 seconds */
    WEEK(BigDecimal.valueOf(604_800), "wk"),

    /** Average Gregorian month = 2,629,800 seconds */
    MONTH(BigDecimal.valueOf(2_629_800), "mo"),

    /** Average Gregorian year = 31,557,600 seconds */
    YEAR(BigDecimal.valueOf(31_557_600), "yr"),

    /** 10 average Gregorian years = 315,576,000 seconds */
    DECADE(BigDecimal.valueOf(315_576_000), "dec"),

    /** 100 average Gregorian years = 3,155,760,000 seconds */
    CENTURY(BigDecimal.valueOf(3_155_760_000), "century"),

    /** 1,000 average Gregorian years = 31,557,600,000 seconds */
    MILLENNIUM(BigDecimal.valueOf(31_557_600_000), "millennium")
}
