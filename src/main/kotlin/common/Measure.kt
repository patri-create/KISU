package com.kisu.common

import java.text.DecimalFormat

class Measure<T> private constructor(
    private val magnitude: Double,
    private val prefix: T,
    private val unit: String,
    private val autoScale: Boolean = true,
    private val decimalFormat: Boolean = true
) where T : Enum<T>, T : Prefix {

    val readable by lazy {
        when {
            autoScale -> {
                (Prefix.allEntries(prefix::class)
                    .map { unit -> prefix.rescale(unit) * magnitude to unit }
                    .lastOrNull { (magnitude, _) -> magnitude >= 1 } ?: (0.0 to prefix.base))
                    .let { (magnitude, prefix) -> format(magnitude, prefix.toString(), unit) }
            }

            else -> {
                format(magnitude, prefix.toString(), unit)
            }
        }


    }

    val isZero: Boolean by lazy { magnitude == 0.0 }

    override fun toString(): String = readable

    fun rescale(other: T): Measure<T> {
        val conversion = prefix.rescale(other)
        return Measure(magnitude * conversion, other, unit, false)
    }

    private fun format(magnitude: Double, prefix: String, unit: String): String {
        val magnitude =
            if (decimalFormat) DecimalFormat("0.################").format(magnitude) else magnitude.toString()
        return if (prefix != "BASE") "$magnitude ${prefix.lowercase()}$unit" else "$magnitude $unit"
    }

    companion object {
        fun <T> create(magnitude: Double, prefix: T, unit: String): Measure<T>
                where T : Enum<T>, T : Prefix {
            return Measure(magnitude, prefix, unit)
        }
    }
}