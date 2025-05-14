package org.kisu.prefixes

import org.kisu.units.distance.Meter
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.parallel.ResourceAccessMode
import org.junit.jupiter.api.parallel.ResourceLock
import org.junit.jupiter.api.parallel.ResourceLocks
import org.junit.jupiter.api.parallel.Resources
import org.kisu.test.extensions.LocaleExtension
import org.kisu.units.Measure

@ExtendWith(LocaleExtension::class)
@ResourceLocks(
    ResourceLock(Resources.LOCALE, mode = ResourceAccessMode.READ_WRITE)
)
class MeterUnitTest {

    @Test
    fun `1 picometer is 1 picometer`() {
        val measure = Measure.create(1.0, Metric.PICO, Meter)
        measure.toString() shouldBe "1.0 picometer"
    }

    @Test
    fun `1 nanometer is 1 nanometer`() {
        val measure = Measure.create(1.0, Metric.NANO, Meter)
        measure.toString() shouldBe "1.0 nanometer"
    }

    @Test
    fun `1 micrometer is 1 micrometer`() {
        val measure = Measure.create(1.0, Metric.MICRO, Meter)
        measure.toString() shouldBe "1.0 micrometer"
    }

    @Test
    fun `1 millimeter is 1 millimeter`() {
        val measure = Measure.create(1.0, Metric.MILLI, Meter)
        measure.toString() shouldBe "1.0 millimeter"
    }

    @Test
    fun `1 centimeter is 1 centimeter`() {
        val measure = Measure.create(1.0, Metric.CENTI, Meter)
        measure.toString() shouldBe "1.0 centimeter"
    }

    @Test
    fun `1 decimeter is 1 decimeter`() {
        val measure = Measure.create(1.0, Metric.DECI, Meter)
        measure.toString() shouldBe "1.0 decimeter"
    }

    @Test
    fun `1 meter is 1 meter`() {
        val measure = Measure.create(1.0, Metric.BASE, Meter)
        measure.toString() shouldBe "1.0 meter"
    }

    @Test
    fun `1 decameter is 1 decameter`() {
        val measure = Measure.create(1.0, Metric.DECA, Meter)
        measure.toString() shouldBe "1.0 decameter"
    }

    @Test
    fun `1 hectometer is 1 hectometer`() {
        val measure = Measure.create(1.0, Metric.HECTO, Meter)
        measure.toString() shouldBe "1.0 hectometer"
    }

    @Test
    fun `1 kilometer is 1 kilometer`() {
        val measure = Measure.create(1.0, Metric.KILO, Meter)
        measure.toString() shouldBe "1.0 kilometer"
    }

    @Test
    fun `1 megameter is 1 megameter`() {
        val measure = Measure.create(1.0, Metric.MEGA, Meter)
        measure.toString() shouldBe "1.0 megameter"
    }

    @Test
    fun `1 gigameter is 1 gigameter`() {
        val measure = Measure.create(1.0, Metric.GIGA, Meter)
        measure.toString() shouldBe "1.0 gigameter"
    }

    @Test
    fun `1 petameter is 1 petameter`() {
        val measure = Measure.create(1.0, Metric.PETA, Meter)
        measure.toString() shouldBe "1.0 petameter"
    }

    @Test
    fun `1 picometer is 0_000000000001 meter`() {
        val measure = Measure.create(1.0, Metric.PICO, Meter)
        val rescaled = measure.rescale(Metric.BASE)
        rescaled.literal shouldBe "0.000000000001 meter"
    }

    @Test
    fun `1 nanometer is 0_000000001 meter`() {
        val measure = Measure.create(1.0, Metric.NANO, Meter)
        val rescaled = measure.rescale(Metric.BASE)
        rescaled.literal shouldBe "0.000000001 meter"
    }

    @Test
    fun `1 micrometer is 0_000001 meter`() {
        val measure = Measure.create(1.0, Metric.MICRO, Meter)
        val rescaled = measure.rescale(Metric.BASE)
        rescaled.literal shouldBe "0.000001 meter"
    }

    @Test
    fun `1 millimeter is 0_001 meter`() {
        val measure = Measure.create(1.0, Metric.MILLI, Meter)
        val rescaled = measure.rescale(Metric.BASE)
        rescaled.literal shouldBe "0.001 meter"
    }

    @Test
    fun `1 centimeter is 0_01 meter`() {
        val measure = Measure.create(1.0, Metric.CENTI, Meter)
        val rescaled = measure.rescale(Metric.BASE)
        rescaled.literal shouldBe "0.01 meter"
    }

    @Test
    fun `1 decimeter is 0_1 meter`() {
        val measure = Measure.create(1.0, Metric.DECI, Meter)
        val rescaled = measure.rescale(Metric.BASE)
        rescaled.literal shouldBe "0.1 meter"
    }

    @Test
    fun `1 decameter is 10 meter`() {
        val measure = Measure.create(1.0, Metric.DECA, Meter)
        val rescaled = measure.rescale(Metric.BASE)
        rescaled.literal shouldBe "10 meter"
    }

    @Test
    fun `1 hectometer is 100 meter`() {
        val measure = Measure.create(1.0, Metric.HECTO, Meter)
        val rescaled = measure.rescale(Metric.BASE)
        rescaled.literal shouldBe "100 meter"
    }

    @Test
    fun `1 kilometer is 1000 meter`() {
        val measure = Measure.create(1.0, Metric.KILO, Meter)
        val rescaled = measure.rescale(Metric.BASE)
        rescaled.literal shouldBe "1000 meter"
    }

    @Test
    fun `1 megameter is 1000000 meter`() {
        val measure = Measure.create(1.0, Metric.MEGA, Meter)
        val rescaled = measure.rescale(Metric.BASE)
        rescaled.literal shouldBe "1000000 meter"
    }

    @Test
    fun `1 gigameter is 1000000000 meter`() {
        val measure = Measure.create(1.0, Metric.GIGA, Meter)
        val rescaled = measure.rescale(Metric.BASE)
        rescaled.literal shouldBe "1000000000 meter"
    }

    @Test
    fun `1 petameter is 1000000000000 meter`() {
        val measure = Measure.create(1.0, Metric.PETA, Meter)
        val rescaled = measure.rescale(Metric.BASE)
        rescaled.literal shouldBe "1000000000000 meter"
    }

    @Test
    fun `1 gigameter is 1_0E9 meter canonical`() {
        val measure = Measure.create(1.0, Metric.GIGA, Meter)
        measure.canonical shouldBe "1.0E9 meter"
    }
}