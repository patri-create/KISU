package org.kisu.units

import org.kisu.prefixes.Metric
import org.kisu.prefixes.Prefix
import java.text.DecimalFormat

class Measure<T> private constructor(
    private val magnitude: Double,
    private val prefix: T,
    private val unit: String,
) where T : Enum<T>, T : Prefix {

    private val optimal by lazy {
        (Prefix.allEntries(prefix::class)
            .map { unit -> prefix.rescale(unit) * magnitude to unit }
            .lastOrNull { (magnitude, _) -> magnitude >= 1 } ?: (0.0 to prefix.base))
            .let { (magnitude, prefix) ->
                if (prefix.toString() != BASE) "$magnitude ${prefix.toString().lowercase()}$unit" else "$magnitude $unit"
            }
    }

    val literal by lazy {
        val magnitudeFormatted = DecimalFormat(DECIMAL_FORMAT).format(magnitude)
        if (prefix.toString() != BASE) "$magnitudeFormatted ${prefix.toString().lowercase()}$unit" else "$magnitudeFormatted $unit"
    }

    val canonical by lazy {
        val baseMagnitude = prefix.rescale(Metric.BASE) * magnitude
        "$baseMagnitude $unit"
    }

    val isZero: Boolean by lazy { magnitude == 0.0 }

    fun rescale(other: T): Measure<T> {
        val conversion = prefix.rescale(other)
        return Measure(magnitude * conversion, other, unit)
    }

    override fun toString(): String = optimal

    companion object {
        const val DECIMAL_FORMAT = "0.################"
        const val BASE = "BASE"

        fun <T> create(magnitude: Double, prefix: T, unit: String): Measure<T>
                where T : Enum<T>, T : Prefix {
            return Measure(magnitude, prefix, unit)
        }
    }
}