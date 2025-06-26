package org.kisu.prefixes

import org.kisu.prefixes.primitives.InBase
import org.kisu.prefixes.primitives.Representation
import org.kisu.prefixes.primitives.StandardSystem
import org.kisu.prefixes.primitives.Symbol
import org.kisu.prefixes.primitives.System

/**
 * The `Binary` prefix system defines unit prefixes based on powers of 2 (2ⁿ).
 *
 * This system includes the standardized prefixes defined by the
 * [International Electrotechnical Commission (IEC)](https://www.iec.ch/homepage), such as kibi (Ki), mebi (Mi),
 * gibi (Gi), and their corresponding powers of 2, as well as the
 * [Joint Electron Device Engineering Council (JEDEC)](https://www.jedec.org/) prefixes like kilo (K), mega (M),
 * and giga (G), which are often used in industry but sometimes ambiguously.
 *
 * Binary prefixes were introduced to provide unambiguous names for multiples of units in computing, where quantities
 * are based on powers of 2 rather than powers of 10.
 *
 * This addresses the confusion between decimal prefixes (like kilo, mega) and binary-based quantities, especially in
 * contexts such as computer memory and data storage.
 *
 * Each binary prefix corresponds to a power of 2 that is a multiple of 10: for example, kibi (Ki) represents
 * 2^10 (1024), mebi (Mi) 2^20 (1,048,576), gibi (Gi) 2^30, and so on.
 *
 * The **IEC** standardized these binary prefixes in 1998 to clearly distinguish binary multiples from decimal ones.
 *
 * The binary prefix system helps to reduce ambiguity when specifying sizes of digital information, ensuring clarity
 * whether a value refers to, for instance, 1000 bytes (kilobyte) or 1024 bytes (kibibyte).
 *
 * This enum models the set of recognized binary prefixes, providing their symbolic representation and exponent.
 */
@Suppress("MagicNumber", "DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE")
enum class Binary(
    override val power: Int,
    symbol: String,
) : Prefix, InBase by InBase.Binary, System<Binary> by StandardSystem(Binary::class), Symbol by Representation(symbol) {
    /**
     * 2^0 = 1
     */
    BASE(0, ""),

    /** Symbol: "Ki" — 2¹⁰ = 1,024 */
    KIBI(10, "Ki"),

    /** Symbol: "K" — 2¹⁰ = 1,024 */
    KILO(10, "K"),

    /** Symbol: "Mi" — 2²⁰ = 1,048,576 */
    MEBI(20, "Mi"),

    /** Symbol: "M" — 2²⁰ = 1,048,576 */
    MEGA(20, "M"),

    /** Symbol: "Gi" — 2³⁰ = 1,073,741,824 */
    GIBI(30, "Gi"),

    /** Symbol: "G" — 2³⁰ = 1,073,741,824 */
    GIGA(30, "G"),

    /** Symbol: "Ti" — 2⁴⁰ = 1,099,511,627,776 */
    TEBI(40, "Ti"),

    /** Symbol: "Pi" — 2⁵⁰ = 1,125,899,906,842,624 */
    PEBI(50, "Pi"),

    /** Symbol: "Ei" — 2⁶⁰ = 1,152,921,504,606,846,976 */
    EXBI(60, "Ei"),

    /** Symbol: "Zi" — 2⁷⁰ = 1,180,591,620,717,411,303,424 */
    ZEBI(70, "Zi"),

    /** Symbol: "Yi" — 2⁸⁰ = 1,208,925,819,614,629,174,706,176 */
    YOBI(80, "Yi"),

    /** Symbol: "Ri" — 2⁹⁰ = 1,237,940,039,285,380,274,899,124,224 */
    ROBI(90, "Ri"),

    /** Symbol: "Qi" — 2¹⁰⁰ = 1,267,650,600,228,229,401,496,703,205,376 */
    QUEBI(100, "Qi"),
}
