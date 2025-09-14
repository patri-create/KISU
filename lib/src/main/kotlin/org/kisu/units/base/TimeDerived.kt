package org.kisu.units.base

import org.kisu.prefixes.Metric
import org.kisu.units.kinematics.linear.Acceleration
import org.kisu.units.kinematics.linear.Crackle
import org.kisu.units.kinematics.linear.Jerk
import org.kisu.units.kinematics.linear.Pop
import org.kisu.units.kinematics.linear.Snap
import org.kisu.units.representation.Scalar
import org.kisu.units.representation.Unit
import java.math.BigDecimal

/**
 * Represents squared seconds (s²).
 *
 * Commonly used in [Acceleration] (m/s²).
 */
class SecondSquared private constructor(
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, SecondSquared>(prefix, overflow, unit, ::SecondSquared) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

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
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, SecondCubed>(prefix, overflow, unit, ::SecondCubed) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

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
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, SecondFourth>(prefix, overflow, unit, ::SecondFourth) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

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
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, SecondFifth>(prefix, overflow, unit, ::SecondFifth) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

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
    prefix: Metric,
    overflow: BigDecimal = BigDecimal.ONE,
    unit: Unit
) : Scalar<Metric, SecondSixth>(prefix, overflow, unit, ::SecondSixth) {

    constructor(prefix: Metric = Metric.BASE) : this(prefix, BigDecimal.ONE, UNIT)

    companion object {
        internal val UNIT = Unit("s", 6)
    }
}
