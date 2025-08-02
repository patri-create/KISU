package org.kisu.test.utils

val Int.superscript: String
    get() = toString().map {
        when (it) {
            '0' -> '⁰'
            '1' -> '¹'
            '2' -> '²'
            '3' -> '³'
            '4' -> '⁴'
            '5' -> '⁵'
            '6' -> '⁶'
            '7' -> '⁷'
            '8' -> '⁸'
            '9' -> '⁹'
            '-' -> '⁻'
            else -> error("Unexpected character: $it")
        }
    }.joinToString("")
