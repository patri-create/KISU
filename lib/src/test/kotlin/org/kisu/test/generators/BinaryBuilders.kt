package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.element
import org.kisu.units.builders.BinaryUnitBuilder
import org.kisu.units.builders.MetricUnitBuilder
import org.kisu.units.builders.atto
import org.kisu.units.builders.centi
import org.kisu.units.builders.deca
import org.kisu.units.builders.deci
import org.kisu.units.builders.exa
import org.kisu.units.builders.exbi
import org.kisu.units.builders.femto
import org.kisu.units.builders.gibi
import org.kisu.units.builders.giga
import org.kisu.units.builders.hecto
import org.kisu.units.builders.kibi
import org.kisu.units.builders.kilo
import org.kisu.units.builders.mebi
import org.kisu.units.builders.mega
import org.kisu.units.builders.micro
import org.kisu.units.builders.milli
import org.kisu.units.builders.nano
import org.kisu.units.builders.pebi
import org.kisu.units.builders.peta
import org.kisu.units.builders.pico
import org.kisu.units.builders.quebi
import org.kisu.units.builders.quecto
import org.kisu.units.builders.quetta
import org.kisu.units.builders.robi
import org.kisu.units.builders.ronna
import org.kisu.units.builders.ronto
import org.kisu.units.builders.tebi
import org.kisu.units.builders.tera
import org.kisu.units.builders.yobi
import org.kisu.units.builders.yocto
import org.kisu.units.builders.yotta
import org.kisu.units.builders.zebi
import org.kisu.units.builders.zepto
import org.kisu.units.builders.zetta

object BinaryBuilders : Generator<Number.() -> BinaryUnitBuilder> {
    override val generator: Arb<Number.() -> BinaryUnitBuilder> = Arb.element(
        Number::kibi,
        Number::mebi,
        Number::gibi,
        Number::tebi,
        Number::pebi,
        Number::exbi,
        Number::zebi,
        Number::yobi,
        Number::robi,
        Number::quebi,
    )


}