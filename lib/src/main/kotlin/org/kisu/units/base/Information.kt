package org.kisu.units.base

import org.kisu.Magnitude
import org.kisu.prefixes.Binary
import org.kisu.prefixes.InformationPrefix
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.prefixes.isCanonical
import org.kisu.units.Measure
import org.kisu.units.exceptions.SubBitInformation
import org.kisu.units.representation.Expression
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit

/**
 * Represents the quantity of **digital information**, measured in bits.
 *
 * This class models the fundamental unit of information in computing and digital communications: the **bit** (binary
 * digit).
 *
 * It supports binary prefixes such as kibibit (Ki), mebibit (Mi), gibibit (Gi), etc., allowing precise modeling of
 * quantities such as storage, memory, and bandwidth.
 *
 * - The **smallest representable unit** is a single bit.
 * - Negative values are not permitted.
 * - In canonical form, the quantity must also be whole: fractional raw bits are rejected.
 * - Fractional prefixed values are allowed only when they still resolve to a whole number of bits.
 *
 * Precision is maintained using [Magnitude].
 *
 * Instances are immutable and safely validated at construction.
 */
class Information private constructor(magnitude: Magnitude, expression: InformationUnit) :
    Measure<InformationUnit, Information>(magnitude, expression, Companion::invoke) {

    companion object {
        /**
         * Creates a new [Information] quantity with the given [magnitude] and [expression].
         *
         * If [expression] is canonical, [magnitude] must be a whole number of bits. Non-canonical values are validated
         * after conversion to canonical bits.
         *
         * @param magnitude The magnitude of the information quantity.
         * @param expression The information unit expression.
         * @return A new [Information] instance with the specified magnitude and expression.
         * @throws SubBitInformation if a non-integer bit value is used with the base unit.
         */
        operator fun invoke(
            magnitude: Magnitude,
            expression: InformationUnit,
        ): Information {
            if (expression.isCanonical && magnitude.hasFraction) {
                throw SubBitInformation(magnitude, Bit.UNIT.toString())
            }
            val information = Information(magnitude, expression)
            information.canonical // Forces validation in canonical bits so sub-bit values fail fast
            return information
        }

        /**
         * Creates a new [Information] quantity in canonical bits.
         */
        operator fun invoke(magnitude: Magnitude): Information =
            invoke(magnitude, Bit(Binary.BASE))

        /**
         * Creates a new [Information] quantity with the given [magnitude] and bit [expression].
         */
        operator fun <P> invoke(
            magnitude: Magnitude,
            expression: Bit<P>,
        ): Information where P : InformationPrefix<P> =
            invoke(magnitude, expression as InformationUnit)

        /**
         * Creates a new [Information] quantity with the given [magnitude] and byte [expression].
         */
        operator fun <P> invoke(
            magnitude: Magnitude,
            expression: Byte<P>,
        ): Information where P : InformationPrefix<P> =
            invoke(magnitude, expression as InformationUnit)

        /**
         * Creates a new [Information] quantity with the given [magnitude] and binary [prefix].
         *
         * @param magnitude The magnitude of the information quantity.
         * @param prefix The [Binary] prefix to apply to the bit unit.
         * @return A new [Information] instance with the specified magnitude and prefix.
         * @throws SubBitInformation if a non-integer bit value is used with the base unit.
         */
        operator fun invoke(
            magnitude: Magnitude,
            prefix: Binary = Binary.BASE,
        ): Information = invoke(magnitude, Bit(prefix))
    }
}

/**
 * Closed expression family for information units.
 */
sealed class InformationUnit : Expression<InformationUnit>() {
    protected abstract val scalar: Scalar<*, *>

    override val smallest: InformationUnit
        get() = all.first()

    override val largest: InformationUnit
        get() = all.last()

    override val factors: Set<Scalar<*, *>>
        get() = scalar.factors

    override val symbol: String
        get() = scalar.symbol

    override fun find(coordinate: Magnitude): InformationUnit =
        all.lastOrNull { unit -> unit.factor <= coordinate } ?: smallest

    override fun to(other: InformationUnit): Magnitude =
        factor / other.factor

    companion object {
        private const val BITS_PER_BYTE_COUNT = 8
        internal val BITS_PER_BYTE = Magnitude(BITS_PER_BYTE_COUNT)
    }
}

/**
 * Represents a bit-based information unit expression.
 *
 * One bit represents the canonical unit of digital information.
 * Use `Bit.UNIT` for the canonical unit.
 *
 * This type accepts information prefix families such as [org.kisu.prefixes.Decimal] (`k`, `M`, `G`) and
 * [Binary] (`Ki`, `Mi`, `Gi`).
 */
class Bit<P>(val prefix: P) : InformationUnit() where P : InformationPrefix<P> {
    override val scalar: org.kisu.units.representation.Scalar<*, *> = Scalar(prefix)

    override val factor: Magnitude
        get() = scalar.factor

    override val canonical: InformationUnit
        get() = Bit(prefix.canonical)

    override val all: List<InformationUnit>
        get() = prefix.all.map { prefix -> Bit(prefix) }

    override fun decompose(magnitude: Magnitude): List<Pair<Magnitude, InformationUnit>> =
        Scalar(prefix).decompose(magnitude)
            .map { (magnitude, unit) -> magnitude to Bit(unit.prefix) }

    companion object {
        /** The canonical symbol for digital information: "bit". */
        internal val UNIT = Unit("bit", 1)
    }

    private class Scalar<P> private constructor(
        algebra: Algebra<P>,
        val prefix: P,
        unit: Unit,
    ) : org.kisu.units.representation.Scalar<P, Scalar<P>>(
        algebra,
        prefix,
        unit,
        ::Scalar,
    ) where P : InformationPrefix<P> {
        constructor(prefix: P) : this(ExponentialAlgebra(prefix.radix), prefix, Bit.UNIT)
    }
}

/**
 * Represents a byte-based information unit expression.
 *
 * One byte represents eight bits of digital information. The byte-to-bit relationship is resolved when this expression
 * is canonicalized by [Information].
 *
 * This type accepts information prefix families such as [org.kisu.prefixes.Decimal] (`k`, `M`, `G`) and
 * [Binary] (`Ki`, `Mi`, `Gi`).
 */
class Byte<P>(val prefix: P) : InformationUnit() where P : InformationPrefix<P> {
    override val scalar: org.kisu.units.representation.Scalar<*, *> = Scalar(prefix)

    override val factor: Magnitude
        get() = BITS_PER_BYTE * scalar.factor

    override val canonical: InformationUnit
        get() = Bit(prefix.canonical)

    override val all: List<InformationUnit>
        get() = prefix.all.map { prefix -> Byte(prefix) }

    override fun decompose(magnitude: Magnitude): List<Pair<Magnitude, InformationUnit>> =
        Scalar(prefix).decompose(magnitude)
            .map { (magnitude, unit) -> magnitude to Byte(unit.prefix) }

    companion object {
        /** The canonical symbol for byte information: "B". */
        internal val UNIT = Unit("B", 1)
    }

    private class Scalar<P> private constructor(
        algebra: Algebra<P>,
        val prefix: P,
        unit: Unit,
    ) : org.kisu.units.representation.Scalar<P, Scalar<P>>(
        algebra,
        prefix,
        unit,
        ::Scalar,
    ) where P : InformationPrefix<P> {
        constructor(prefix: P) : this(ExponentialAlgebra(prefix.radix), prefix, Byte.UNIT)
    }
}
