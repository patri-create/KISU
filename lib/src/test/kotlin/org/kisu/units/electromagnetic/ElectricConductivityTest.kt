package org.kisu.units.electromagnetic

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.test.generators.MetricBuilders
import org.kisu.test.generators.Metrics
import org.kisu.test.generators.magnitude
import org.kisu.test.generators.reciprocalMagnitude
import org.kisu.units.base.Length
import org.kisu.units.builders.siemensPerMetre
import org.kisu.units.chemistry.MolarConductivity
import org.kisu.units.chemistry.Molarity
import org.kisu.units.electromagnetic.ElectricConductivity.Companion.SiemensPerMetre
import org.kisu.units.special.Conductance

class ElectricConductivityTest : StringSpec({
    "creates an ElectricConductivity" {
        checkAll(Arb.magnitude(), MetricBuilders.generator) { magnitude, builder ->
            magnitude.builder().siemensPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe SiemensPerMetre(magnitude.builder().metric)
                symbol shouldBe SiemensPerMetre().toString()
            }
        }
    }

    "creates a base ElectricConductivity" {
        checkAll(Arb.magnitude()) { magnitude ->
            magnitude.siemensPerMetre.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe SiemensPerMetre()
                symbol shouldBe SiemensPerMetre().toString()
            }
        }
    }

    "converts to Resistivity" {
        checkAll(Arb.reciprocalMagnitude()) { magnitude ->
            magnitude.siemensPerMetre.resistivity.electricConductivity.should { (amount, expression, symbol) ->
                amount shouldBe magnitude
                expression shouldBe SiemensPerMetre()
                symbol shouldBe SiemensPerMetre().toString()
            }
        }
    }

    "fails when converting zero ElectricConductivity to Resistivity" {
        shouldThrow<ArithmeticException> {
            0.siemensPerMetre.resistivity
        }
    }
    // Dimension-aware arithmetic properties
    "dividing an ElectricConductivity by a MolarConductivity returns a Molarity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = ElectricConductivity(leftMagnitude, leftPrefix)
            val right = MolarConductivity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = Molarity(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "dividing an ElectricConductivity by a Molarity returns a MolarConductivity" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.reciprocalMagnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = ElectricConductivity(leftMagnitude, leftPrefix)
            val right = Molarity(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() / right.canonical.component1()
            val expected = MolarConductivity(expectedMagnitude)

            (left / right) shouldBe expected
        }
    }
    "multiplying an ElectricConductivity by a Length returns a Conductance" {
        checkAll(
            50,
            Arb.magnitude(),
            Arb.magnitude(),
            Metrics.generator,
            Metrics.generator,
        ) { leftMagnitude, rightMagnitude, leftPrefix, rightPrefix ->
            val left = ElectricConductivity(leftMagnitude, leftPrefix)
            val right = Length(rightMagnitude, rightPrefix)
            val expectedMagnitude = left.canonical.component1() * right.canonical.component1()
            val expected = Conductance(expectedMagnitude)

            (left * right) shouldBe expected
        }
    }
    // End dimension-aware arithmetic properties
})
