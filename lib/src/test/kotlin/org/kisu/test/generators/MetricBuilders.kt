package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.element
import org.kisu.units.builders.MetricUnitBuilder
import org.kisu.units.builders.atto
import org.kisu.units.builders.centi
import org.kisu.units.builders.deca
import org.kisu.units.builders.deci
import org.kisu.units.builders.exa
import org.kisu.units.builders.femto
import org.kisu.units.builders.giga
import org.kisu.units.builders.hecto
import org.kisu.units.builders.kilo
import org.kisu.units.builders.mega
import org.kisu.units.builders.micro
import org.kisu.units.builders.milli
import org.kisu.units.builders.nano
import org.kisu.units.builders.peta
import org.kisu.units.builders.pico
import org.kisu.units.builders.quecto
import org.kisu.units.builders.quetta
import org.kisu.units.builders.ronna
import org.kisu.units.builders.ronto
import org.kisu.units.builders.tera
import org.kisu.units.builders.yocto
import org.kisu.units.builders.yotta
import org.kisu.units.builders.zepto
import org.kisu.units.builders.zetta

object MetricBuilders : Generator<Number.() -> MetricUnitBuilder> {
    override val generator: Arb<Number.() -> MetricUnitBuilder> = Arb.element(
        Number::quecto,
        Number::ronto,
        Number::yocto,
        Number::zepto,
        Number::atto,
        Number::femto,
        Number::pico,
        Number::nano,
        Number::micro,
        Number::milli,
        Number::centi,
        Number::deci,
        Number::deca,
        Number::hecto,
        Number::kilo,
        Number::mega,
        Number::giga,
        Number::tera,
        Number::peta,
        Number::exa,
        Number::zetta,
        Number::yotta,
        Number::ronna,
        Number::quetta,
    )
}
