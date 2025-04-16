package com.kisu.common

import java.text.DecimalFormat

class Measure<T> private constructor(
    private val magnitude: Double,
    private val prefix: T,
    private val unit: String,
) where T : Enum<T>, T : Prefix {

    private val readableAutoScale by lazy {
        (Prefix.allEntries(prefix::class)
            .map { unit -> prefix.rescale(unit) * magnitude to unit }
            .lastOrNull { (magnitude, _) -> magnitude >= 1 } ?: (0.0 to prefix.base))
            .let { (magnitude, prefix) ->
                if (prefix.toString() != BASE) "$magnitude ${prefix.toString().lowercase()}$unit" else "$magnitude $unit"
            }
    }

    val readable by lazy {
        val magnitudeFormatted = DecimalFormat(DECIMAL_FORMAT).format(magnitude)
        if (prefix.toString() != BASE) "$magnitudeFormatted ${prefix.toString().lowercase()}$unit" else "$magnitudeFormatted $unit"
    }

    val isZero: Boolean by lazy { magnitude == 0.0 }

    override fun toString(): String = readableAutoScale

    fun rescale(other: T): Measure<T> {
        val conversion = prefix.rescale(other)
        return Measure(magnitude * conversion, other, unit)
    }


    companion object {
        const val DECIMAL_FORMAT = "0.################"
        const val BASE = "BASE"

        fun <T> create(magnitude: Double, prefix: T, unit: String): Measure<T>
                where T : Enum<T>, T : Prefix {
            return Measure(magnitude, prefix, unit)
        }
    }
}