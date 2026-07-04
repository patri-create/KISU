package org.kisu.units.builders

import org.kisu.Magnitude
import org.kisu.prefixes.Binary
import org.kisu.units.base.Information

/**
 * Builder interface for IEC-prefixed quantities.
 *
 * This interface is implemented by all IEC prefix builders, such as [KibiBuilder], [MebiBuilder], and [GibiBuilder].
 * It provides access to the numeric magnitude and the corresponding [Binary] prefix.
 */
interface BinaryUnitBuilder {
    /** The numeric value of the quantity before applying the IEC prefix. */
    val magnitude: Magnitude

    /** The IEC prefix associated with this builder (for example, [Binary.KIBI] or [Binary.MEBI]). */
    val binary: Binary
}

/**
 * Creates an [Information] quantity in bit units using the current builder's IEC prefix.
 *
 * For example, `25.kibi.bits` produces `25 Kibit`.
 */
val BinaryUnitBuilder.bits: Information
    get() = Information(magnitude, binary)
