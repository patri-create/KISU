package org.kisu.units

import org.kisu.prefixes.Metric
import org.kisu.prefixes.Prefix
import org.kisu.prefixes.primitives.System
import java.math.BigDecimal
import java.text.DecimalFormat

class Measure<T>(
    private val magnitude: BigDecimal,
    private val prefix: T,
    private val unit: String,
) where T : System<T>, T : Prefix {

    constructor(magnitude: Double, prefix: T, unit: String) : this(BigDecimal.valueOf(magnitude), prefix, unit)

    private val optimal by lazy {
        (prefix.all
            .map { unit -> prefix.scale(unit) * magnitude to unit }
            .lastOrNull { (magnitude, _) -> magnitude >= BigDecimal.ONE } ?: (0.0 to prefix.base))
            .let { (magnitude, prefix) ->
                if (prefix.toString() != BASE) "$magnitude ${prefix.toString().lowercase()}$unit" else "$magnitude $unit"
            }
    }

    val literal by lazy {
        val magnitudeFormatted = DecimalFormat(DECIMAL_FORMAT).format(magnitude)
        if (prefix.toString() != BASE) "$magnitudeFormatted ${prefix.toString().lowercase()}$unit" else "$magnitudeFormatted $unit"
    }

    val canonical by lazy {
        val baseMagnitude = prefix.scale(Metric.BASE) * magnitude
        "$baseMagnitude $unit"
    }

    val isZero: Boolean by lazy { magnitude.compareTo(BigDecimal.ZERO) == 0}

    fun rescale(other: T): Measure<T> {
        val conversion = prefix.scale(other)
        return Measure(magnitude * conversion, other, unit)
    }

    override fun toString(): String = optimal

    companion object {
        const val DECIMAL_FORMAT = "0.################"
        const val BASE = "BASE"

        fun <T> create(magnitude: Double, prefix: T, unit: String): Measure<T>
                where T : System<T>, T : Prefix {
            return Measure(magnitude, prefix, unit)
        }
    }
}