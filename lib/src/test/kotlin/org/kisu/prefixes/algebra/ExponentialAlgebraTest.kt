package org.kisu.prefixes.algebra

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.kisu.Magnitude
import org.kisu.prefixes.Binary
import org.kisu.prefixes.ExponentialPrefix
import org.kisu.prefixes.Metric
import org.kisu.prefixes.primitives.ExponentialEnumSystem
import org.kisu.prefixes.primitives.System

class ExponentialAlgebraTest : StringSpec({
    "rejects zero and negative bases" {
        shouldThrow<IllegalArgumentException> {
            ExponentialAlgebra<Metric>(Magnitude.ZERO)
        }
        shouldThrow<IllegalArgumentException> {
            ExponentialAlgebra<Metric>(Magnitude("-10"))
        }
    }

    "resolves factors from powers" {
        val algebra = ExponentialAlgebra<Metric>()

        algebra.factor(Metric.KILO) shouldBe Magnitude.TEN.pow(3)
    }

    "multiplies by adding powers" {
        val algebra = ExponentialAlgebra<Metric>()

        algebra.multiply(Metric.KILO, Metric.KILO) shouldBe (Metric.MEGA to Magnitude.ONE)
    }

    "divides by subtracting powers" {
        val algebra = ExponentialAlgebra<Metric>()

        algebra.divide(Metric.KILO, Metric.MEGA) shouldBe (Metric.MILLI to Magnitude.ONE)
    }

    "returns a multiplicative remainder when powers underflow" {
        val algebra = ExponentialAlgebra<Metric>()
        val (prefix, remainder) = algebra.divide(Metric.QUECTO, Metric.QUETTA)
        val expected = Magnitude.ONE / Magnitude.TEN.pow(30)

        prefix shouldBe Metric.QUECTO
        remainder.compareTo(expected) shouldBe 0
    }

    "uses its configured base for the remainder" {
        val algebra = ExponentialAlgebra<Binary>(2)
        val (prefix, remainder) = algebra.multiply(Binary.QUEBI, Binary.KIBI)

        prefix shouldBe Binary.QUEBI
        remainder.compareTo(Magnitude.valueOf(2).pow(10)) shouldBe 0
    }

    "fails when multiplication overflows the power coordinate" {
        val algebra = ExponentialAlgebra<ExtremePowerPrefix>()

        shouldThrow<ArithmeticException> {
            algebra.multiply(ExtremePowerPrefix.MAX, ExtremePowerPrefix.MAX)
        }
    }

    "fails when division overflows the power coordinate" {
        val algebra = ExponentialAlgebra<ExtremePowerPrefix>()

        shouldThrow<ArithmeticException> {
            algebra.divide(ExtremePowerPrefix.MIN, ExtremePowerPrefix.MAX)
        }
    }

    "rejects powers outside Magnitude exponent bounds" {
        val algebra = ExponentialAlgebra<ExtremePowerPrefix>()

        shouldThrow<IllegalArgumentException> {
            algebra.factor(ExtremePowerPrefix.MAX)
        }
    }
})

@Suppress("DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE")
private enum class ExtremePowerPrefix(
    override val power: Int,
    override val symbol: String,
) : ExponentialPrefix<ExtremePowerPrefix>,
    System<ExtremePowerPrefix> by ExponentialEnumSystem(ExtremePowerPrefix::class) {
    MIN(Int.MIN_VALUE, "min"),
    BASE(0, ""),
    MAX(Int.MAX_VALUE, "max"),
}
