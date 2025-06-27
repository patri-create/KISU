package org.kisu.prefixes

import org.kisu.prefixes.primitives.Representation
import org.kisu.prefixes.primitives.Symbol
import java.math.BigDecimal

/**
 * Represents the standardized **binary prefixes** based on powers of 2, as defined by the
 * [International Electrotechnical Commission (IEC)](https://www.iec.ch/homepage).
 *
 * These prefixes are used primarily in computing and digital storage to denote multiples of bytes and bits
 * based on powers of 2 rather than powers of 10.
 *
 * Each prefix corresponds to an exact power of 2, for example:
 * - Kibi (Ki) = 2¹⁰ = 1,024
 * - Mebi (Mi) = 2²⁰ = 1,048,576
 * - Gibi (Gi) = 2³⁰ = 1,073,741,824
 * and so forth.
 *
 * This enum uses explicit [factor] values as [BigDecimal] to avoid ambiguity and rounding errors.
 *
 * @property factor The exact multiplication factor relative to the base unit (2⁰ = 1).
 * @property symbol The standard symbol representing the prefix.
 */
@Suppress("MagicNumber", "DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE")
enum class Binary(
    override val factor: BigDecimal,
    symbol: String,
) : Prefix<Binary>, Symbol by Representation(symbol) {

    /** Base unit with factor 1 (2^0). */
    BASE(BigDecimal.ONE, ""),

    /** Kibi — 2¹⁰ = 1,024 */
    KIBI(BigDecimal("1024"), "Ki"),

    /** Mebi — 2²⁰ = 1,048,576 */
    MEBI(BigDecimal("1048576"), "Mi"),

    /** Gibi — 2³⁰ = 1,073,741,824 */
    GIBI(BigDecimal("1073741824"), "Gi"),

    /** Tebi — 2⁴⁰ = 1,099,511,627,776 */
    TEBI(BigDecimal("1099511627776"), "Ti"),

    /** Pebi — 2⁵⁰ = 1,125,899,906,842,624 */
    PEBI(BigDecimal("1125899906842624"), "Pi"),

    /** Exbi — 2⁶⁰ = 1,152,921,504,606,846,976 */
    EXBI(BigDecimal("1152921504606846976"), "Ei"),

    /** Zebi — 2⁷⁰ = 1,180,591,620,717,411,303,424 */
    ZEBI(BigDecimal("1180591620717411303424"), "Zi"),

    /** Yobi — 2⁸⁰ = 1,208,925,819,614,629,174,706,176 */
    YOBI(BigDecimal("1208925819614629174706176"), "Yi"),

    /** Robi — 2⁹⁰ = 1,237,940,039,285,380,274,899,124,224 */
    ROBI(BigDecimal("1237940039285380274899124224"), "Ri"),

    /** Quebi — 2¹⁰⁰ = 1,267,650,600,228,229,401,496,703,205,376 */
    QUEBI(BigDecimal("1267650600228229401496703205376"), "Qi");
}
