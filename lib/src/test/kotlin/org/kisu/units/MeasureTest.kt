package org.kisu.units

import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import net.jqwik.api.Arbitraries
import net.jqwik.api.Arbitrary
import net.jqwik.api.Combinators
import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.Provide
import org.kisu.prefixes.Metric
import org.kisu.test.fakes.TestUnit
import org.kisu.test.utils.optimalPrefixFrom
import org.kisu.zero
import java.math.BigDecimal
import java.math.MathContext

class MeasureTest {

    @Property
    fun `rescales correctly`(
        @ForAll("measures") measure: Measure<Metric>,
        @ForAll("prefixes") newPrefix: Metric
    ) {
        measure.to(newPrefix)
    }

    @Property
    fun `renders literal when the magnitude is not zero`(
        @ForAll("nonZeroMagnitudes") magnitude: BigDecimal,
        @ForAll("prefixes") prefix: Metric
    ) {
        TestUnit(magnitude, prefix).representation shouldBe "$magnitude $prefix${TestUnit.SYMBOL}"
    }

    @Property
    fun `renders literal when the magnitude is zero`(
        @ForAll("prefixes") prefix: Metric
    ) {
        TestUnit(BigDecimal.ZERO, prefix).representation shouldBe "0 ${TestUnit.SYMBOL}"
    }

    @Property
    fun `renders canonical`(
        @ForAll("magnitudes") magnitude: BigDecimal,
        @ForAll("prefixes") prefix: Metric
    ) {
        TestUnit(magnitude, prefix).canonical.representation shouldBe TestUnit(
            magnitude,
            prefix
        ).to(prefix.canonical).representation
    }

    @Property
    fun `renders optimal correctly when magnitude is 0`(@ForAll("prefixes") prefix: Metric) {
        val measure = TestUnit(BigDecimal.ZERO, prefix)

        measure.optimal shouldBe measure.canonical
    }

    @Property
    fun `renders optimal correctly when magnitude is beyond the largest prefix`(
        @ForAll("greaterThanZero") magnitude: BigDecimal
    ) {
        val measure = TestUnit(magnitude, Metric.QUETTA)

        measure.optimal.representation shouldBe measure.representation
    }

    @Property
    fun `renders optimal correctly when magnitude is beyond the smallest prefix`(
        @ForAll("lesserThanOne") magnitude: BigDecimal
    ) {
        val measure = TestUnit(magnitude, Metric.QUECTO)

        measure.optimal.representation shouldBe measure.representation
    }

    @Property
    fun `renders optimal correctly when magnitude is in range`(@ForAll("inRange") magnitude: BigDecimal) {
        val measure = TestUnit(magnitude, Metric.BASE)
        val optimalPrefix = magnitude.optimalPrefixFrom(Metric.BASE)
        val correctedMagnitude = magnitude * (Metric.BASE.scale(optimalPrefix))
        val compact = TestUnit(correctedMagnitude, optimalPrefix)

        measure.optimal.representation shouldBe compact.representation
    }

    @Property
    fun `representation is the optimal representation`(@ForAll("measures") measure: Measure<Metric>) {
        measure.optimal.representation shouldBe measure.toString()
    }

    @Property
    fun `equality is reflexive`(@ForAll("measures") x: Measure<Metric>) {
        x.equals(x).shouldBeTrue()
    }

    @Property
    fun `equality is symmetric`(@ForAll("measures") x: Measure<Metric>, @ForAll("prefixes") prefix: Metric) {
        val y = x.to(prefix)

        (x == y) shouldBe (y == x)
    }

    @Property
    fun `equality is transitive`(
        @ForAll("measures") x: Measure<Metric>,
        @ForAll("prefixes") first: Metric,
        @ForAll("prefixes") second: Metric
    ) {
        val y = x.to(first)
        val z = x.to(second)

        (x == y).shouldBeTrue()
        (y == z).shouldBeTrue()
        (x == z).shouldBeTrue()
    }

    @Suppress("EqualsNullCall")
    @Property
    fun `equality is non-null`(@ForAll("measures") x: Measure<Metric>) {
        (x.equals(null)).shouldBeFalse()
    }

    @Provide
    fun magnitudes() = Arbitraries.bigDecimals()

    @Provide
    fun nonZeroMagnitudes() = magnitudes().filter { magnitude -> !magnitude.zero }

    @Provide
    fun measures(): Arbitrary<Measure<Metric>> =
        Combinators.combine<BigDecimal, Metric>(magnitudes(), prefixes())
            .`as` { magnitude, prefix -> TestUnit(magnitude, prefix) }

    @Provide
    fun prefixes() = org.kisu.test.generators.Metric.system

    @Provide
    fun greaterThanZero() = Arbitraries.oneOf(
        Arbitraries.bigDecimals().greaterOrEqual(BigDecimal.ONE),
        Arbitraries.bigDecimals().lessOrEqual(BigDecimal.ONE.negate())
    )

    @Provide
    fun lesserThanOne() = Arbitraries.bigDecimals()
        .greaterThan(BigDecimal.ONE.negate())
        .lessThan(BigDecimal.ONE)
        .filter { it.compareTo(BigDecimal.ZERO) == 0 }

    @Provide
    fun inRange() = Arbitraries.integers()
        .between(-30, 30)
        .filter { it != 0 } // Optional: skip 0 if desired
        .map { power ->
            if (power > 0) {
                BigDecimal.TEN.pow(power)
            } else {
                BigDecimal.ONE.divide(BigDecimal.TEN.pow(-power), MathContext.DECIMAL128)
            }
        }
}
