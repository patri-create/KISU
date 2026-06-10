package org.kisu.prefixes

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeUnique

class TimeTest : StringSpec({
    "time symbols are unique" {
        Time.entries.map(Time::symbol).shouldBeUnique()
    }
})
