package org.kisu.prefixes.algebra

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.kisu.Magnitude
import org.kisu.prefixes.Time

class LinearAlgebraTest : StringSpec({
    "resolves concrete factors" {
        val algebra = LinearAlgebra<Time>()

        algebra.factor(Time.HOUR) shouldBe Magnitude.valueOf(3_600)
    }

    "multiplies concrete factors" {
        val algebra = LinearAlgebra<Time>()

        algebra.multiply(Time.MINUTE, Time.MINUTE) shouldBe (Time.HOUR to Magnitude.ONE)
    }

    "divides concrete factors" {
        val algebra = LinearAlgebra<Time>()

        algebra.divide(Time.HOUR, Time.MINUTE) shouldBe (Time.MINUTE to Magnitude.ONE)
    }
})
