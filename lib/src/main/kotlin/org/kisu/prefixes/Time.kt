package org.kisu.prefixes

import org.kisu.Magnitude
import org.kisu.prefixes.primitives.LinearEnumSystem
import org.kisu.prefixes.primitives.Representation
import org.kisu.prefixes.primitives.Symbol
import org.kisu.prefixes.primitives.System
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
    override val factor: Magnitude,
    symbol: String,
) : LinearPrefix<Time>,
    System<Time> by LinearEnumSystem(Time::class),
    Symbol by Representation(symbol) {
    /** 10⁻³⁰ = 0.000000000000000000000000000001 */
    QUECTO(Magnitude(BigInteger("1"), 30), "q"),

    /** 10⁻²⁷ = 0.000000000000000000000000001 */
    RONTO(Magnitude(BigInteger("1"), 27), "r"),

    /** 10⁻²⁴ = 0.000000000000000000000001 */
    YOCTO(Magnitude(BigInteger("1"), 24), "y"),

    /** 10⁻²¹ = 0.000000000000000000001 */
    ZEPTO(Magnitude(BigInteger("1"), 21), "z"),

    /** 10⁻¹⁸ = 0.000000000000000001 */
    ATTO(Magnitude(BigInteger("1"), 18), "a"),

    /** 10⁻¹⁵ = 0.000000000000001 */
    FEMTO(Magnitude(BigInteger("1"), 15), "f"),

    /** 10⁻¹² = 0.000000000001 */
    PICO(Magnitude(BigInteger("1"), 12), "p"),

    /** 10⁻⁹ = 0.000000001 */
    NANO(Magnitude(BigInteger("1"), 9), "n"),

    /** 10⁻⁶ = 0.000001 */
    MICRO(Magnitude(BigInteger("1"), 6), "μ"),

    /** 10⁻³ = 0.001 */
    MILLI(Magnitude(BigInteger("1"), 3), "m"),

    /** 10⁻² = 0.01 */
    CENTI(Magnitude(BigInteger("1"), 2), "c"),

    /** 10⁻¹ = 0.1 */
    DECI(Magnitude(BigInteger("1"), 1), "d"),

    /** 1 second */
    SECOND(Magnitude.valueOf(1), "s"),

    /** 60 seconds */
    MINUTE(Magnitude.valueOf(60), "min"),

    /** 3,600 seconds */
    HOUR(Magnitude.valueOf(3_600), "h"),

    /** 86,400 seconds */
    DAY(Magnitude.valueOf(86_400), "day"),

    /** 604,800 seconds */
    WEEK(Magnitude.valueOf(604_800), "wk"),

    /** Average Gregorian month = 2,629,800 seconds */
    MONTH(Magnitude.valueOf(2_629_800), "mo"),

    /** Average Gregorian year = 31,557,600 seconds */
    YEAR(Magnitude.valueOf(31_557_600), "yr"),

    /** 10 average Gregorian years = 315,576,000 seconds */
    DECADE(Magnitude.valueOf(315_576_000), "dec"),

    /** 100 average Gregorian years = 3,155,760,000 seconds */
    CENTURY(Magnitude.valueOf(3_155_760_000), "century"),

    /** 1,000 average Gregorian years = 31,557,600,000 seconds */
    MILLENNIUM(Magnitude.valueOf(31_557_600_000), "millennium")
}
