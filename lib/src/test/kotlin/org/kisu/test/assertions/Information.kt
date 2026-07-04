package org.kisu.test.assertions

import io.kotest.matchers.shouldBe
import org.kisu.Magnitude
import org.kisu.units.base.Bit
import org.kisu.units.base.Byte
import org.kisu.units.base.Information
import org.kisu.units.base.InformationUnit
import org.kisu.prefixes.Binary as Iec
import org.kisu.prefixes.Decimal as Si

fun Information.shouldEqualCanonicalBits(bits: Magnitude) {
    this shouldBe Information(bits, Bit(Iec.BASE))
}

fun InformationUnit.shouldUseIecBitScale() {
    val bit = this as? Bit<*> ?: error("Expected a bit unit using the IEC scale, but was $this")

    (bit.prefix is Iec) shouldBe true
}

fun InformationUnit.shouldUseIecByteScale() {
    val byte = this as? Byte<*> ?: error("Expected a byte unit using the IEC scale, but was $this")

    (byte.prefix is Iec) shouldBe true
}

fun InformationUnit.shouldUseSiBitScale() {
    val bit = this as? Bit<*> ?: error("Expected a bit unit using the SI scale, but was $this")

    (bit.prefix is Si) shouldBe true
}

fun InformationUnit.shouldUseSiByteScale() {
    val byte = this as? Byte<*> ?: error("Expected a byte unit using the SI scale, but was $this")

    (byte.prefix is Si) shouldBe true
}
