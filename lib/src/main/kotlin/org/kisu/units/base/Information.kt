package org.kisu.units.base

import org.kisu.Magnitude
import org.kisu.prefixes.Binary
import org.kisu.prefixes.Decimal
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
 * Represents a quantity of **digital information**.
 *
 * [Information] values can be expressed either as [Bit] or [Byte] units. Both units accept information prefix scales:
 * [Decimal] for SI prefixes such as `kbit`, `Mbit`, `kB`, and `MB`, and [Binary] for IEC prefixes such as `Kibit`,
 * `Mibit`, `KiB`, and `MiB`.
 *
 * The canonical representation is an unprefixed IEC bit. For example, `2 kB` canonicalizes to `16000 bit`, while
 * `2 KiB` canonicalizes to `16384 bit`.
 *
 * - The **smallest representable unit** is a single bit.
 * - In canonical form, the quantity must also be whole: fractional raw bits are rejected.
 * - Fractional prefixed values are allowed only when they still resolve to a whole number of bits.
 *
 * Instances are immutable. Precision is maintained using [Magnitude].
 */
class Information private constructor(magnitude: Magnitude, private val unit: InformationUnit) :
    Measure<InformationUnit, Information>(magnitude, unit, Companion::invoke) {

    /**
     * Returns this quantity expressed in bit units, preserving the current SI or IEC scale.
     */
    val bits: Information
        get() = to(unit.bits).optimal

    /**
     * Returns this quantity expressed in byte units, preserving the current SI or IEC scale.
     */
    val bytes: Information
        get() = to(unit.bytes).optimal

    /**
     * Returns this quantity expressed with SI information prefixes, preserving whether it is a bit or byte quantity.
     */
    val decimal: Information
        get() = to(unit.decimal).optimal

    /**
     * Returns this quantity expressed with IEC information prefixes, preserving whether it is a bit or byte quantity.
     */
    val iec: Information
        get() = to(unit.iec).optimal

    /**
     * Alias for [iec].
     */
    val binary: Information
        get() = iec

    companion object {
        /**
         * Creates a new [Information] quantity with the given [magnitude] and [expression].
         *
         * The expression can be a [Bit] or [Byte] using either the SI [Decimal] scale or IEC [Binary] scale. The value
         * is validated after conversion to canonical bits so sub-bit values fail at construction time.
         *
         * @param magnitude The magnitude of the information quantity.
         * @param expression The information unit expression.
         * @return A new [Information] instance with the specified magnitude and expression.
         * @throws SubBitInformation if the value does not resolve to a whole number of canonical bits.
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
         * Creates a new [Information] quantity in unprefixed IEC bits.
         */
        operator fun invoke(magnitude: Magnitude): Information =
            invoke(magnitude, Bit(Binary.BASE))

        /**
         * Creates a new [Information] quantity with the given bit [expression].
         */
        operator fun <P> invoke(
            magnitude: Magnitude,
            expression: Bit<P>,
        ): Information where P : InformationPrefix<P> =
            invoke(magnitude, expression as InformationUnit)

        /**
         * Creates a new [Information] quantity with the given byte [expression].
         */
        operator fun <P> invoke(
            magnitude: Magnitude,
            expression: Byte<P>,
        ): Information where P : InformationPrefix<P> =
            invoke(magnitude, expression as InformationUnit)

        /**
         * Creates a new [Information] quantity in bit units with the given IEC [prefix].
         *
         * @param magnitude The magnitude of the information quantity.
         * @param prefix The IEC [Binary] prefix to apply to the bit unit.
         * @return A new [Information] instance with the specified magnitude and prefix.
         * @throws SubBitInformation if the value does not resolve to a whole number of canonical bits.
         */
        operator fun invoke(
            magnitude: Magnitude,
            prefix: Binary = Binary.BASE,
        ): Information = invoke(magnitude, Bit(prefix))
    }
}

/**
 * Closed expression family for information units.
 *
 * Each expression is either a [Bit] or a [Byte] with an SI or IEC prefix. All expressions canonicalize to unprefixed
 * IEC bits so equality and ordering compare physical information content rather than the original display unit.
 */
sealed class InformationUnit : Expression<InformationUnit>() {
    protected abstract val scalar: Scalar<*, *>

    /**
     * Equivalent bit expression on the same SI or IEC scale.
     */
    internal abstract val bits: InformationUnit

    /**
     * Equivalent byte expression on the same SI or IEC scale.
     */
    internal abstract val bytes: InformationUnit

    /**
     * Equivalent expression on the SI scale.
     */
    internal abstract val decimal: InformationUnit

    /**
     * Equivalent expression on the IEC scale.
     */
    internal abstract val iec: InformationUnit

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
        Magnitude(factor.toBigDecimal().divide(other.factor.toBigDecimal()), factor.config)

    companion object {
        private const val BITS_PER_BYTE_COUNT = 8
        internal val BITS_PER_BYTE = Magnitude(BITS_PER_BYTE_COUNT)
    }
}

/**
 * Represents a bit-based information unit expression.
 *
 * A bit is the smallest representable information unit. [Bit] accepts either an SI [Decimal] prefix or an IEC [Binary]
 * prefix and canonicalizes to an unprefixed IEC bit.
 *
 * Examples include `kbit` ([Decimal.KILO]) and `Kibit` ([Binary.KIBI]).
 */
class Bit<P>(val prefix: P) : InformationUnit() where P : InformationPrefix<P> {
    override val scalar: org.kisu.units.representation.Scalar<*, *> = Scalar(prefix)

    override val bits: InformationUnit
        get() = Bit(prefix.canonical)

    override val bytes: InformationUnit
        get() = Byte(prefix.canonical)

    override val decimal: InformationUnit
        get() = Bit(Decimal.BASE)

    override val iec: InformationUnit
        get() = Bit(Binary.BASE)

    override val factor: Magnitude
        get() = scalar.factor

    override val canonical: InformationUnit
        get() = Bit(Binary.BASE)

    override val all: List<InformationUnit>
        get() = prefix.all.map { prefix -> Bit(prefix) }

    override fun decompose(magnitude: Magnitude): List<Pair<Magnitude, InformationUnit>> =
        Scalar(prefix).decompose(magnitude)
            .map { (magnitude, unit) -> magnitude to Bit(unit.prefix) }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Bit<*>) return false

        return prefix == other.prefix
    }

    override fun hashCode(): Int = prefix.hashCode()

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
 * One byte represents eight bits of digital information. [Byte] accepts either an SI [Decimal] prefix or an IEC
 * [Binary] prefix and canonicalizes through the same unprefixed IEC bit expression as [Bit].
 *
 * Examples include `kB` ([Decimal.KILO]) and `KiB` ([Binary.KIBI]).
 */
class Byte<P>(val prefix: P) : InformationUnit() where P : InformationPrefix<P> {
    override val scalar: org.kisu.units.representation.Scalar<*, *> = Scalar(prefix)

    override val bits: InformationUnit
        get() = Bit(prefix.canonical)

    override val bytes: InformationUnit
        get() = Byte(prefix.canonical)

    override val decimal: InformationUnit
        get() = Byte(Decimal.BASE)

    override val iec: InformationUnit
        get() = Byte(Binary.BASE)

    override val factor: Magnitude
        get() = BITS_PER_BYTE * scalar.factor

    override val canonical: InformationUnit
        get() = Bit(Binary.BASE)

    override val all: List<InformationUnit>
        get() = prefix.all.map { prefix -> Byte(prefix) }

    override fun decompose(magnitude: Magnitude): List<Pair<Magnitude, InformationUnit>> =
        Scalar(prefix).decompose(magnitude)
            .map { (magnitude, unit) -> magnitude to Byte(unit.prefix) }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Byte<*>) return false

        return prefix == other.prefix
    }

    override fun hashCode(): Int = prefix.hashCode()

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
