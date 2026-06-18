package org.kisu.units

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll
import org.kisu.Magnitude
import org.kisu.test.generators.magnitude
import org.kisu.test.matchers.plusOrMinus
import org.kisu.units.builders.becquerels
import org.kisu.units.builders.cubicMetresPerKilogram
import org.kisu.units.builders.cubicMetresPerMole
import org.kisu.units.builders.farads
import org.kisu.units.builders.gramsPerCubicMetre
import org.kisu.units.builders.gramsPerMole
import org.kisu.units.builders.henries
import org.kisu.units.builders.henriesPerMetre
import org.kisu.units.builders.hertz
import org.kisu.units.builders.kelvins
import org.kisu.units.builders.metres
import org.kisu.units.builders.metresPerHenry
import org.kisu.units.builders.moles
import org.kisu.units.builders.molesPerCubicMetre
import org.kisu.units.builders.molesPerKilogram
import org.kisu.units.builders.ohms
import org.kisu.units.builders.ohmsMetre
import org.kisu.units.builders.pascals
import org.kisu.units.builders.reciprocalFarads
import org.kisu.units.builders.reciprocalHenries
import org.kisu.units.builders.reciprocalKelvins
import org.kisu.units.builders.reciprocalMetres
import org.kisu.units.builders.reciprocalMoles
import org.kisu.units.builders.reciprocalPascals
import org.kisu.units.builders.seconds
import org.kisu.units.builders.siemens
import org.kisu.units.builders.siemensPerMetre

private val ROUND_TRIP_TOLERANCE = Magnitude("1e-12")

class ReciprocalConversionTest : StringSpec({
    "converts amount and reciprocal amount as reciprocals" {
        checkAll(Arb.magnitude()) { magnitude ->
            val reciprocalAmount = magnitude.moles.reciprocalAmount
            assertReciprocal(magnitude, reciprocalAmount.component1(), reciprocalAmount.amount.component1())

            val amount = magnitude.reciprocalMoles.amount
            assertReciprocal(magnitude, amount.component1(), amount.reciprocalAmount.component1())
        }
    }

    "converts length and wave number as reciprocals" {
        checkAll(Arb.magnitude()) { magnitude ->
            val waveNumber = magnitude.metres.waveNumber
            assertReciprocal(magnitude, waveNumber.component1(), waveNumber.wavelength.component1())

            val wavelength = magnitude.reciprocalMetres.wavelength
            assertReciprocal(magnitude, wavelength.component1(), wavelength.waveNumber.component1())
        }
    }

    "converts time and frequency as reciprocals" {
        checkAll(Arb.magnitude()) { magnitude ->
            val frequency = magnitude.seconds.frequency
            assertReciprocal(magnitude, frequency.component1(), frequency.period.component1())

            val period = magnitude.hertz.period
            assertReciprocal(magnitude, period.component1(), period.frequency.component1())
        }
    }

    "converts time and activity as reciprocals" {
        checkAll(Arb.magnitude()) { magnitude ->
            val activity = magnitude.seconds.activity
            assertReciprocal(magnitude, activity.component1(), activity.meanInterval.component1())

            val meanInterval = magnitude.becquerels.meanInterval
            assertReciprocal(magnitude, meanInterval.component1(), meanInterval.activity.component1())
        }
    }

    "converts temperature and thermal expansion coefficient as reciprocals" {
        checkAll(Arb.magnitude()) { magnitude ->
            val coefficient = magnitude.kelvins.thermalExpansionCoefficient
            assertReciprocal(magnitude, coefficient.component1(), coefficient.temperature.component1())

            val temperature = magnitude.reciprocalKelvins.temperature
            assertReciprocal(magnitude, temperature.component1(), temperature.thermalExpansionCoefficient.component1())
        }
    }

    "converts pressure and compressibility as reciprocals" {
        checkAll(Arb.magnitude()) { magnitude ->
            val compressibility = magnitude.pascals.compressibility
            assertReciprocal(magnitude, compressibility.component1(), compressibility.pressure.component1())

            val pressure = magnitude.reciprocalPascals.pressure
            assertReciprocal(magnitude, pressure.component1(), pressure.compressibility.component1())
        }
    }

    "converts resistance and conductance as reciprocals" {
        checkAll(Arb.magnitude()) { magnitude ->
            val conductance = magnitude.ohms.conductance
            assertReciprocal(magnitude, conductance.component1(), conductance.resistance.component1())

            val resistance = magnitude.siemens.resistance
            assertReciprocal(magnitude, resistance.component1(), resistance.conductance.component1())
        }
    }

    "converts capacitance and elastance as reciprocals" {
        checkAll(Arb.magnitude()) { magnitude ->
            val elastance = magnitude.farads.elastance
            assertReciprocal(magnitude, elastance.component1(), elastance.capacitance.component1())

            val capacitance = magnitude.reciprocalFarads.capacitance
            assertReciprocal(magnitude, capacitance.component1(), capacitance.elastance.component1())
        }
    }

    "converts inductance and magnetic reluctance as reciprocals" {
        checkAll(Arb.magnitude()) { magnitude ->
            val reluctance = magnitude.henries.magneticReluctance
            assertReciprocal(magnitude, reluctance.component1(), reluctance.inductance.component1())

            val inductance = magnitude.reciprocalHenries.inductance
            assertReciprocal(magnitude, inductance.component1(), inductance.magneticReluctance.component1())
        }
    }

    "converts density and specific volume as reciprocals" {
        checkAll(Arb.magnitude()) { magnitude ->
            val specificVolume = magnitude.gramsPerCubicMetre.specificVolume
            assertReciprocal(magnitude, specificVolume.component1(), specificVolume.density.component1())

            val density = magnitude.cubicMetresPerKilogram.density
            assertReciprocal(magnitude, density.component1(), density.specificVolume.component1())
        }
    }

    "converts molarity and molar volume as reciprocals" {
        checkAll(Arb.magnitude()) { magnitude ->
            val molarVolume = magnitude.molesPerCubicMetre.molarVolume
            assertReciprocal(magnitude, molarVolume.component1(), molarVolume.molarity.component1())

            val molarity = magnitude.cubicMetresPerMole.molarity
            assertReciprocal(magnitude, molarity.component1(), molarity.molarVolume.component1())
        }
    }

    "converts molality and molar mass as reciprocals" {
        checkAll(Arb.magnitude()) { magnitude ->
            val molarMass = magnitude.molesPerKilogram.molarMass
            assertReciprocal(magnitude, molarMass.component1(), molarMass.molality.component1())

            val molality = magnitude.gramsPerMole.molality
            assertReciprocal(magnitude, molality.component1(), molality.molarMass.component1())
        }
    }

    "converts electric conductivity and resistivity as reciprocals" {
        checkAll(Arb.magnitude()) { magnitude ->
            val resistivity = magnitude.siemensPerMetre.resistivity
            assertReciprocal(magnitude, resistivity.component1(), resistivity.electricConductivity.component1())

            val electricConductivity = magnitude.ohmsMetre.electricConductivity
            assertReciprocal(
                magnitude,
                electricConductivity.component1(),
                electricConductivity.resistivity.component1(),
            )
        }
    }

    "converts magnetic permittivity and magnetic susceptibility as reciprocals" {
        checkAll(Arb.magnitude()) { magnitude ->
            val susceptibility = magnitude.henriesPerMetre.magneticSusceptibility
            assertReciprocal(magnitude, susceptibility.component1(), susceptibility.magneticPermittivity.component1())

            val permittivity = magnitude.metresPerHenry.magneticPermittivity
            assertReciprocal(magnitude, permittivity.component1(), permittivity.magneticSusceptibility.component1())
        }
    }
})

private fun assertReciprocal(
    source: Magnitude,
    reciprocal: Magnitude,
    roundTrip: Magnitude,
) {
    reciprocal shouldBe source.inverted
    roundTrip shouldBe (source plusOrMinus ROUND_TRIP_TOLERANCE)
}
