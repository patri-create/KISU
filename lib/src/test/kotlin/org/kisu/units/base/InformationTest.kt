package org.kisu.units.base

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.positiveLong
import io.kotest.property.checkAll
import org.kisu.Magnitude
import org.kisu.magnitude
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.test.assertions.shouldEqualCanonicalBits
import org.kisu.test.assertions.shouldUseIecBitScale
import org.kisu.test.assertions.shouldUseIecByteScale
import org.kisu.test.assertions.shouldUseSiBitScale
import org.kisu.test.assertions.shouldUseSiByteScale
import org.kisu.test.generators.magnitude
import org.kisu.units.builders.bits
import org.kisu.units.exceptions.SubBitInformation
import org.kisu.prefixes.Binary as Iec
import org.kisu.prefixes.Decimal as Si
import org.kisu.test.generators.Binaries as IecPrefixes
import org.kisu.test.generators.BinaryBuilders as IecBuilders
import org.kisu.test.generators.Decimals as SiPrefixes

class InformationTest : StringSpec({
    val iecAlgebra = ExponentialAlgebra<Iec>(Magnitude.TWO)
    val siAlgebra = ExponentialAlgebra<Si>()
    val bitsPerByte = Magnitude(8)

    "fractional information is physically meaningless" {
        checkAll(
            Arb.magnitude(minFractionalDigits = 1).map { it.abs },
            IecBuilders.generator
        ) { magnitude, builder ->
            val scaled = magnitude / iecAlgebra.factor(magnitude.builder().binary)
            shouldThrow<SubBitInformation> { scaled.builder().bits }
        }
    }

    "creates Information" {
        checkAll(Arb.positiveLong(), IecBuilders.generator) { magnitude, builder ->
            magnitude.builder().bits.should { (amount, expression, symbol) ->
                amount shouldBe magnitude.magnitude
                expression shouldBe Bit(magnitude.builder().binary)
                symbol shouldBe Bit.UNIT.toString()
            }
        }
    }

    "creates a base Information" {
        checkAll(Arb.positiveLong()) { magnitude ->
            magnitude.bits.should { (amount, expression, symbol) ->
                amount shouldBe magnitude.magnitude
                expression shouldBe Bit(Iec.BASE)
                symbol shouldBe Bit.UNIT.toString()
            }
        }
    }

    "keeps optimal information representation in its original scale" {
        checkAll(SiPrefixes.generator) { prefix ->
            val factor = siAlgebra.factor(prefix)

            Information(factor, Bit(Si.BASE)).optimal.should { (amount, expression) ->
                amount shouldBe Magnitude.ONE
                expression shouldBe Bit(prefix)
            }
            Information(factor, Byte(Si.BASE)).optimal.should { (amount, expression) ->
                amount shouldBe Magnitude.ONE
                expression shouldBe Byte(prefix)
            }
        }

        checkAll(IecPrefixes.generator) { prefix ->
            val factor = iecAlgebra.factor(prefix)

            Information(factor, Bit(Iec.BASE)).optimal.should { (amount, expression) ->
                amount shouldBe Magnitude.ONE
                expression shouldBe Bit(prefix)
            }
            Information(factor, Byte(Iec.BASE)).optimal.should { (amount, expression) ->
                amount shouldBe Magnitude.ONE
                expression shouldBe Byte(prefix)
            }
        }
    }

    "canonicalizes byte information to bits" {
        checkAll(Arb.positiveLong(), SiPrefixes.generator) { magnitude, prefix ->
            val amount = magnitude.magnitude

            Information(amount, Byte(prefix)).canonical.should { (canonicalAmount, expression, symbol) ->
                canonicalAmount shouldBe amount * bitsPerByte * siAlgebra.factor(prefix)
                expression shouldBe Bit(Iec.BASE)
                symbol shouldBe Bit.UNIT.toString()
            }
        }

        checkAll(Arb.positiveLong(), IecPrefixes.generator) { magnitude, prefix ->
            val amount = magnitude.magnitude

            Information(amount, Byte(prefix)).canonical.should { (canonicalAmount, expression, symbol) ->
                canonicalAmount shouldBe amount * bitsPerByte * iecAlgebra.factor(prefix)
                expression shouldBe Bit(Iec.BASE)
                symbol shouldBe Bit.UNIT.toString()
            }
        }
    }

    "matches SI and IEC bit and byte units by canonical bits" {
        checkAll(Arb.positiveLong(), SiPrefixes.generator) { magnitude, prefix ->
            val amount = magnitude.magnitude
            val factor = siAlgebra.factor(prefix)

            Information(amount, Bit(prefix)).shouldEqualCanonicalBits(amount * factor)
            Information(amount, Byte(prefix)).shouldEqualCanonicalBits(amount * bitsPerByte * factor)
            Information(amount, Byte(prefix)) shouldBe Information(amount * bitsPerByte, Bit(prefix))
        }

        checkAll(Arb.positiveLong(), IecPrefixes.generator) { magnitude, prefix ->
            val amount = magnitude.magnitude
            val factor = iecAlgebra.factor(prefix)

            Information(amount, Bit(prefix)).shouldEqualCanonicalBits(amount * factor)
            Information(amount, Byte(prefix)).shouldEqualCanonicalBits(amount * bitsPerByte * factor)
            Information(amount, Byte(prefix)) shouldBe Information(amount * bitsPerByte, Bit(prefix))
        }
    }

    "converts between bit and byte units" {
        checkAll(SiPrefixes.generator) { prefix ->
            Information(bitsPerByte, Bit(prefix)).bytes.should { (amount, expression) ->
                amount shouldBe Magnitude.ONE
                expression shouldBe Byte(prefix)
            }
            Information(Magnitude.ONE, Byte(prefix)).bits.should { (amount, expression) ->
                amount shouldBe bitsPerByte
                expression shouldBe Bit(prefix)
            }
        }

        checkAll(IecPrefixes.generator) { prefix ->
            Information(bitsPerByte, Bit(prefix)).bytes.should { (amount, expression) ->
                amount shouldBe Magnitude.ONE
                expression shouldBe Byte(prefix)
            }
            Information(Magnitude.ONE, Byte(prefix)).bits.should { (amount, expression) ->
                amount shouldBe bitsPerByte
                expression shouldBe Bit(prefix)
            }
        }
    }

    "converts between SI and IEC scales" {
        checkAll(Arb.positiveLong(), SiPrefixes.generator) { magnitude, prefix ->
            val amount = magnitude.magnitude
            val bits = Information(amount, Bit(prefix))
            val bytes = Information(amount, Byte(prefix))

            bits.iec.should { converted ->
                converted shouldBe bits
                converted.component2().shouldUseIecBitScale()
            }
            bytes.iec.should { converted ->
                converted shouldBe bytes
                converted.component2().shouldUseIecByteScale()
            }
        }

        checkAll(Arb.positiveLong(), IecPrefixes.generator) { magnitude, prefix ->
            val amount = magnitude.magnitude
            val bits = Information(amount, Bit(prefix))
            val bytes = Information(amount, Byte(prefix))

            bits.decimal.should { converted ->
                converted shouldBe bits
                converted.component2().shouldUseSiBitScale()
            }
            bytes.decimal.should { converted ->
                converted shouldBe bytes
                converted.component2().shouldUseSiByteScale()
            }
        }
    }
})
