package org.kisu.prefixes.primitives

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldBeSorted
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.ints.shouldBeZero
import io.kotest.matchers.shouldBe
import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.Provide
import org.kisu.test.fakes.InvalidSystem
import org.kisu.test.generators.System

@Suppress("UnusedParameter")
class SystemTest {

    @Property
    fun `retrieves base unit`(@ForAll("systems") system: StandardSystem<*>) {
        system.canonical.power.shouldBeZero()
    }

    @Property
    fun `crashes if there is an invalid system with no base`(@ForAll("systems") system: StandardSystem<*>) {
        shouldThrow<IllegalStateException> { StandardSystem(InvalidSystem::class).canonical }
    }

    @Property
    fun `retrieves all prefixes for a system`(@ForAll("systems") system: StandardSystem<*>) {
        system.all.shouldNotBeEmpty()
    }

    @Property
    fun `all prefixes from a system is sorted by power`(@ForAll("systems") system: StandardSystem<*>) {
        system.all.map { prefix -> prefix.power }.shouldBeSorted()
    }

    @Property
    fun `retrieves the smallest prefix`(@ForAll("systems") system: StandardSystem<*>) {
        system.smallest shouldBe system.all.first()
    }

    @Property
    fun `retrieves the largest prefix`(@ForAll("systems") system: StandardSystem<*>) {
        system.largest shouldBe system.all.last()
    }

    @Provide
    private fun systems() = System.systems
}
