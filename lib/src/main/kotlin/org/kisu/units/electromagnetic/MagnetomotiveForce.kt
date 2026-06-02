package org.kisu.units.electromagnetic

import org.kisu.prefixes.Metric
import org.kisu.units.Measure
import org.kisu.units.base.Ampere
import org.kisu.units.representation.Product
import org.kisu.units.special.Radian
import java.math.BigDecimal

/**
 * Represents the physical quantity of **magnetomotive force**, measured in
 * [AmpereRadian] in this library.
 *
 * Magnetomotive force quantifies the driving influence that establishes magnetic flux in
 * a magnetic circuit. It plays a role analogous to voltage in electric circuits.
 *
 * Typical examples include coil excitation, transformer cores, electromagnets, and
 * magnetic circuit calculations.
 *
 * The associated unit representation is [AmpereRadian] (`A·rad`) as modeled by this
 * API.
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
         * Creates a [AmpereRadian] expression for **ampere radian** (`A·rad`).
         *
         * @param prefix Metric prefix applied to the ampere unit component.
         * Defaults to [Metric.BASE] (no prefix).
         * @return A [AmpereRadian] expression for `A·rad`.
         */
        @Suppress("FunctionNaming")
        internal fun AmpereRadian(prefix: Metric = Metric.BASE): AmpereRadian =
            Product(Ampere(prefix), Radian())
    }
}
