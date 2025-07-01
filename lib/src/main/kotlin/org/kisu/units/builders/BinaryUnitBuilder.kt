package org.kisu.units.builders

import org.kisu.prefixes.Binary
import org.kisu.units.base.Information
import java.math.BigDecimal

/**
 * Builder interface for binary-prefixed quantities.
 *
 * This interface is implemented by all binary prefix builders
 * (e.g., [KibiBuilder], [MebiBuilder], [GibiBuilder], etc.).
 * It provides access to the numeric magnitude and the corresponding binary prefix.
 */
interface BinaryUnitBuilder {
    /** The numeric value of the quantity, before applying the binary prefix. */
    val magnitude: BigDecimal

    /** The binary prefix associated with this builder (e.g., KIBI, MEBI). */
    val binary: Binary
}

/**
 * Creates an [Information] quantity using the current builder's magnitude and binary prefix.
 *
 * For example, `25.kibi.bits` will produce 25 Ki (2¹⁰) bits of information.
 */
val BinaryUnitBuilder.bits: Information
    get() = Information(magnitude, binary)
