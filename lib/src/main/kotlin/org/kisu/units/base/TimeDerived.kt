package org.kisu.units.base

import org.kisu.prefixes.Metric
import org.kisu.prefixes.algebra.Algebra
import org.kisu.prefixes.algebra.ExponentialAlgebra
import org.kisu.units.kinematics.linear.Acceleration
import org.kisu.units.kinematics.linear.Crackle
import org.kisu.units.kinematics.linear.Jerk
import org.kisu.units.kinematics.linear.Pop
import org.kisu.units.kinematics.linear.Snap
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit

/**
 * Represents squared seconds (s²).
 *
 * Commonly used in [Acceleration] (m/s²).
 */
class SecondSquared private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, SecondSquared>(algebra, prefix, unit, ::SecondSquared) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        internal val UNIT = Unit("s", 2)
    }
}

/**
 * Represents cubed seconds (s³).
 *
 * Used in [Jerk] (m/s³).
 */
class SecondCubed private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, SecondCubed>(algebra, prefix, unit, ::SecondCubed) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        internal val UNIT = Unit("s", 3)
    }
}

/**
 * Represents quartic seconds (s⁴).
 *
 * Used in [Snap] derivatives.
 */
class SecondFourth private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, SecondFourth>(algebra, prefix, unit, ::SecondFourth) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        internal val UNIT = Unit("s", 4)
    }
}

/**
 * Represents quintic seconds (s⁵).
 *
 * Used in [Crackle] (5th derivative of position).
 */
class SecondFifth private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, SecondFifth>(algebra, prefix, unit, ::SecondFifth) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        internal val UNIT = Unit("s", 5)
    }
}

/**
 * Represents sextic seconds (s⁶).
 *
 * Used in [Pop] (6th derivative of position).
 */
class SecondSixth private constructor(
    algebra: Algebra<Metric> = ExponentialAlgebra(),
    prefix: Metric,
    unit: Unit
) : Scalar<Metric, SecondSixth>(algebra, prefix, unit, ::SecondSixth) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix = prefix, unit = UNIT)

    companion object {
        internal val UNIT = Unit("s", 6)
    }
}
