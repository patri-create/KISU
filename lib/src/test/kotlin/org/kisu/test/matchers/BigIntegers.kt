package org.kisu.test.matchers

import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import org.kisu.Magnitude

infix fun Magnitude.plusOrMinus(tolerance: Magnitude) =
    Matcher<Magnitude> { actual ->
        val difference = (actual - this).abs
        MatcherResult(
            difference <= tolerance,
            { "Expected $actual to be within ±$tolerance of $this, but difference was $difference" },
            { "Expected $actual to not be within ±$tolerance of $this" },
        )
    }
