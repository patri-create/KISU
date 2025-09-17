package org.kisu.units.electromagnetic

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Ampere
import org.kisu.units.representation.Product
import org.kisu.units.special.Radian
import java.math.BigDecimal

/**
 * Represents **magnetomotive force** (MMF, 𝓕), which drives magnetic flux
 * through a magnetic circuit, analogous to electromotive force in electrical circuits.
 *
 * - **Dimension**: electric current × angle (A·rad)
 * - **SI Unit**: ampere-radian (A·rad)
 *
 * Magnetomotive force is defined as the product of current and the number of
 * turns in a coil:
 *
 *     𝓕 = N * I
 *
 * where:
 * - N is the number of turns,
 * - I is the electric current (A).
 *
 * Example usages include:
 * - Designing magnetic circuits, transformers, and electromagnets
 * - Calculating flux in solenoids and coils
 * - Analyzing inductive components in physics and engineering
 *
 * @param magnitude numerical value of the measure
 * @param expression unit expression in ampere-radian (A·rad)
 */
class MagnetomotiveForce(
    magnitude: BigDecimal,
    expression: AmpereRadian
) : Measure<MagnetomotiveForce.AmpereRadian, MagnetomotiveForce>(magnitude, expression, ::MagnetomotiveForce) {

    internal constructor(magnitude: BigDecimal, prefix: Metric = Metric.BASE) :
        this(magnitude, AmpereRadian(prefix))

    /**
     * Represents the SI unit **ampere-radian (A·rad)**.
     *
     * This unit measures **magnetomotive force**, i.e., the driving force that
     * produces magnetic flux in a magnetic circuit.
     * It is defined as the [Product] of [Ampere] (electric current) and [Radian] (angle).
     *
     * Example usages include:
     * - Calculating magnetomotive force in coils and solenoids
     * - Designing magnetic circuits and electromagnets
     *
     * @see MagnetomotiveForce for the physical quantity represented by this unit.
     */
    typealias AmpereRadian = Product<Ampere, Radian>

    companion object {
        /**
         * Creates a measure of **ampere-radians** (A·rad).
         *
         * This compound unit represents the product of:
         *  - an [Ampere] (electric current) with the specified [prefix]
         *  - multiplied by a [Radian] (angle)
         *
         * It can be used in contexts where current is associated with rotational motion,
         * such as in electromechanical systems.
         *
         * @param prefix Metric prefix to apply to the ampere unit.
         * Defaults to [Metric.BASE] (no prefix).
         *
         * @return An [AmpereRadian] representing A·rad.
         */
        @Suppress("FunctionNaming")
        internal fun AmpereRadian(prefix: Metric = Metric.BASE): AmpereRadian =
            Product(Ampere(prefix), Radian())
    }
}
