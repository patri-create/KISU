package org.kisu.prefixes.algebra

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.kisu.prefixes.Time
import java.math.BigDecimal

class LinearAlgebraTest : StringSpec({
    "resolves concrete factors" {
        val algebra = LinearAlgebra<Time>()

        algebra.factor(Time.HOUR) shouldBe BigDecimal.valueOf(3_600)
    }

    "multiplies concrete factors" {
        val algebra = LinearAlgebra<Time>()

        algebra.multiply(Time.MINUTE, Time.MINUTE) shouldBe (Time.HOUR to BigDecimal.ONE)
    }

    "divides concrete factors" {
        val algebra = LinearAlgebra<Time>()

        algebra.divide(Time.HOUR, Time.MINUTE) shouldBe (Time.MINUTE to BigDecimal.ONE)
    }
})
