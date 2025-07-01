package org.kisu.test.generators

import io.kotest.property.Arb
import io.kotest.property.arbitrary.element
import org.kisu.units.builders.BinaryUnitBuilder
import org.kisu.units.builders.exbi
import org.kisu.units.builders.gibi
import org.kisu.units.builders.kibi
import org.kisu.units.builders.mebi
import org.kisu.units.builders.pebi
import org.kisu.units.builders.quebi
import org.kisu.units.builders.robi
import org.kisu.units.builders.tebi
import org.kisu.units.builders.yobi
import org.kisu.units.builders.zebi

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
