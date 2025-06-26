package org.kisu.test.matchers

import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import java.math.BigDecimal

infix fun BigDecimal.plusOrMinus(tolerance: BigDecimal) =
    Matcher<BigDecimal> { actual ->
        val difference = actual.subtract(this).abs()
        MatcherResult(
            difference <= tolerance,
            { "Expected $actual to be within ±$tolerance of $this, but difference was $difference" },
            { "Expected $actual to not be within ±$tolerance of $this" },
        )
    }
