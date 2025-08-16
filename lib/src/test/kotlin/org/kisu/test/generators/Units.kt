package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.next
import org.kisu.prefixes.Binary
import org.kisu.prefixes.Metric
import org.kisu.units.base.Ampere
import org.kisu.units.base.Bit
import org.kisu.units.base.Candela
import org.kisu.units.base.Kelvin
import org.kisu.units.base.Kilogram
import org.kisu.units.base.Metre
import org.kisu.units.base.Mol
import org.kisu.units.base.Second
import org.kisu.units.representation.Scalar
import org.kisu.units.special.Becquerel
import org.kisu.units.special.Coulomb
import org.kisu.units.special.Farad
import org.kisu.units.special.Gray
import org.kisu.units.special.Henry
import org.kisu.units.special.Joule
import org.kisu.units.special.Katal
import org.kisu.units.special.Lumen
import org.kisu.units.special.Lux
import org.kisu.units.special.Newton
import org.kisu.units.special.Ohm
import org.kisu.units.special.Pascal
import org.kisu.units.special.Radian
import org.kisu.units.special.Siemens
import org.kisu.units.special.Sievert
import org.kisu.units.special.Steradian
import org.kisu.units.special.Tesla
import org.kisu.units.special.Volt
import org.kisu.units.special.Watt
import org.kisu.units.special.Weber

object Units : Generator<Scalar<Metric, *>> {
    override val generator: Arb<Scalar<Metric, *>> = distinct(1).map { it.first() }

    val symbols = generator.map { unit -> unit.toString() }

    fun binaries(prefixMode: Mode = Mode.BASE): Arb<Scalar<Binary, *>> {
        return when(prefixMode) {
            Mode.BASE -> arbitrary { Bit(Binary.BASE) }
            Mode.RANDOM -> Binaries.generator.map { Bit(it) }
        }
    }

    fun distinct(number: Int, prefixMode: Mode = Mode.BASE): Arb<List<Scalar<Metric, *>>> {
        require(number <= METRIC_UNITS.size) {
            "Cannot ask for more than ${METRIC_UNITS.size} distinct units"
        }
        return arbitrary { rs ->
            METRIC_UNITS.shuffled(rs.random).take(number).map { constructor ->
                when (prefixMode) {
                    Mode.BASE -> constructor(Metric.BASE)
                    Mode.RANDOM -> constructor(Metrics.generator.next(rs))
                }
            }
        }
    }

    enum class Mode { BASE, RANDOM }
}

private val METRIC_UNITS: List<(Metric) -> Scalar<Metric, *>> = listOf(
    ::Newton,
    ::Pascal,
    ::Joule,
    ::Watt,
    ::Coulomb,
    ::Volt,
    ::Farad,
    ::Ohm,
    ::Siemens,
    ::Weber,
    ::Tesla,
    ::Henry,
    ::Lumen,
    ::Lux,
    ::Becquerel,
    ::Gray,
    ::Sievert,
    ::Katal,
    ::Metre,
    ::Kilogram,
    ::Second,
    ::Ampere,
    ::Kelvin,
    ::Mol,
    ::Candela,
    ::Radian,
    ::Steradian
)
