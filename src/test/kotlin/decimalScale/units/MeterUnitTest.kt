package decimalScale.units

import com.kisu.common.Measure
import com.kisu.decimalScale.DecimalScale
import com.kisu.decimalScale.units.Meter
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MeterUnitTest {

    @Test
    fun `1 picometer is 1 picometer`() {
        val measure = Measure.create(1.0, DecimalScale.PICO, Meter)
        measure.toString() shouldBe "1 picometer"
    }

    @Test
    fun `1 nanometer is 1 nanometer`() {
        val measure = Measure.create(1.0, DecimalScale.NANO, Meter)
        measure.toString() shouldBe "1 nanometer"
    }

    @Test
    fun `1 micrometer is 1 micrometer`() {
        val measure = Measure.create(1.0, DecimalScale.MICRO, Meter)
        measure.toString() shouldBe "1 micrometer"
    }

    @Test
    fun `1 millimeter is 1 millimeter`() {
        val measure = Measure.create(1.0, DecimalScale.MILLI, Meter)
        measure.toString() shouldBe "1 millimeter"
    }

    @Test
    fun `1 centimeter is 1 centimeter`() {
        val measure = Measure.create(1.0, DecimalScale.CENTI, Meter)
        measure.toString() shouldBe "1 centimeter"
    }

    @Test
    fun `1 decimeter is 1 decimeter`() {
        val measure = Measure.create(1.0, DecimalScale.DECI, Meter)
        measure.toString() shouldBe "1 decimeter"
    }

    @Test
    fun `1 meter is 1 meter`() {
        val measure = Measure.create(1.0, DecimalScale.BASE, Meter)
        measure.toString() shouldBe "1 meter"
    }

    @Test
    fun `1 decameter is 1 decameter`() {
        val measure = Measure.create(1.0, DecimalScale.DECA, Meter)
        measure.toString() shouldBe "1 decameter"
    }

    @Test
    fun `1 hectometer is 1 hectometer`() {
        val measure = Measure.create(1.0, DecimalScale.HECTO, Meter)
        measure.toString() shouldBe "1 hectometer"
    }

    @Test
    fun `1 kilometer is 1 kilometer`() {
        val measure = Measure.create(1.0, DecimalScale.KILO, Meter)
        measure.toString() shouldBe "1 kilometer"
    }

    @Test
    fun `1 megameter is 1 megameter`() {
        val measure = Measure.create(1.0, DecimalScale.MEGA, Meter)
        measure.toString() shouldBe "1 megameter"
    }

    @Test
    fun `1 gigameter is 1 gigameter`() {
        val measure = Measure.create(1.0, DecimalScale.GIGA, Meter)
        measure.toString() shouldBe "1 gigameter"
    }

    @Test
    fun `1 petameter is 1 petameter`() {
        val measure = Measure.create(1.0, DecimalScale.PETA, Meter)
        measure.toString() shouldBe "1 petameter"
    }

    @Test
    fun `1 picometer is 0_000000000001 meter`() {
        val measure = Measure.create(1.0, DecimalScale.PICO,  Meter)
        val rescaled = measure.rescale(DecimalScale.BASE)
        rescaled.toString() shouldBe "0.000000000001 meter"
    }

    @Test
    fun `1 nanometer is 0_000000001 meter`() {
        val measure = Measure.create(1.0, DecimalScale.NANO,  Meter)
        val rescaled = measure.rescale(DecimalScale.BASE)
        rescaled.toString() shouldBe "0.000000001 meter"
    }

    @Test
    fun `1 micrometer is 0_000001 meter`() {
        val measure = Measure.create(1.0, DecimalScale.MICRO,  Meter)
        val rescaled = measure.rescale(DecimalScale.BASE)
        rescaled.toString() shouldBe "0.000001 meter"
    }

    @Test
    fun `1 millimeter is 0_001 meter`() {
        val measure = Measure.create(1.0, DecimalScale.MILLI,  Meter)
        val rescaled = measure.rescale(DecimalScale.BASE)
        rescaled.toString() shouldBe "0.001 meter"
    }

    @Test
    fun `1 centimeter is 0_01 meter`() {
        val measure = Measure.create(1.0, DecimalScale.CENTI,  Meter)
        val rescaled = measure.rescale(DecimalScale.BASE)
        rescaled.toString() shouldBe "0.01 meter"
    }

    @Test
    fun `1 decimeter is 0_1 meter`() {
        val measure = Measure.create(1.0, DecimalScale.DECI,  Meter)
        val rescaled = measure.rescale(DecimalScale.BASE)
        rescaled.toString() shouldBe "0.1 meter"
    }

    @Test
    fun `1 decameter is 10 meter`() {
        val measure = Measure.create(1.0, DecimalScale.DECA,  Meter)
        val rescaled = measure.rescale(DecimalScale.BASE)
        rescaled.toString() shouldBe "10 meter"
    }

    @Test
    fun `1 hectometer is 100 meter`() {
        val measure = Measure.create(1.0, DecimalScale.HECTO,  Meter)
        val rescaled = measure.rescale(DecimalScale.BASE)
        rescaled.toString() shouldBe "100 meter"
    }

    @Test
    fun `1 kilometer is 1000 meter`() {
        val measure = Measure.create(1.0, DecimalScale.KILO,  Meter)
        val rescaled = measure.rescale(DecimalScale.BASE)
        rescaled.toString() shouldBe "1000 meter"
    }

    @Test
    fun `1 megameter is 1000000 meter`() {
        val measure = Measure.create(1.0, DecimalScale.MEGA,  Meter)
        val rescaled = measure.rescale(DecimalScale.BASE)
        rescaled.toString() shouldBe "1000000 meter"
    }

    @Test
    fun `1 gigameter is 1000000000 meter`() {
        val measure = Measure.create(1.0, DecimalScale.GIGA,  Meter)
        val rescaled = measure.rescale(DecimalScale.BASE)
        rescaled.toString() shouldBe "1000000000 meter"
    }

    @Test
    fun `1 petameter is 1000000000000 meter`() {
        val measure = Measure.create(1.0, DecimalScale.PETA,  Meter)
        val rescaled = measure.rescale(DecimalScale.BASE)
        rescaled.toString() shouldBe "1000000000000 meter"
    }
}