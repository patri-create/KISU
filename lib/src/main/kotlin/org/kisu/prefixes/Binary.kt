package org.kisu.prefixes

import org.kisu.prefixes.primitives.EnumSystem
import org.kisu.prefixes.primitives.Representation
import org.kisu.prefixes.primitives.Symbol
import org.kisu.prefixes.primitives.System
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
) : Prefix<Binary>, System<Binary> by EnumSystem(Binary::class), Symbol by Representation(symbol) {
    /** Base unit with factor 1 (2^0). */
    BASE(BigDecimal.ONE, ""),

    /** Kibi — 2¹⁰ = 1,024 */
    KIBI(10, "Ki"),

    /** Mebi — 2²⁰ = 1,048,576 */
    MEBI(20, "Mi"),

    /** Gibi — 2³⁰ = 1,073,741,824 */
    GIBI(30, "Gi"),

    /** Tebi — 2⁴⁰ = 1,099,511,627,776 */
    TEBI(40, "Ti"),

    /** Pebi — 2⁵⁰ = 1,125,899,906,842,624 */
    PEBI(50, "Pi"),

    /** Exbi — 2⁶⁰ = 1,152,921,504,606,846,976 */
    EXBI(60, "Ei"),

    /** Zebi — 2⁷⁰ = 1,180,591,620,717,411,303,424 */
    ZEBI(70, "Zi"),

    /** Yobi — 2⁸⁰ = 1,208,925,819,614,629,174,706,176 */
    YOBI(80, "Yi"),

    /** Robi — 2⁹⁰ = 1,237,940,039,285,380,274,899,124,224 */
    ROBI(90, "Ri"),

    /** Quebi — 2¹⁰⁰ = 1,267,650,600,228,229,401,496,703,205,376 */
    QUEBI(100, "Qi"),
    ;

    constructor(power: Int, symbol: String) : this(factor = BigDecimal.TWO.pow(power), symbol)
}
