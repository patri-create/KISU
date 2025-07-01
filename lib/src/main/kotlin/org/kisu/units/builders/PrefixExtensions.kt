package org.kisu.units.builders

import org.kisu.bigDecimal

/** Creates a builder with the 'quecto' (10⁻³⁰) metric prefix. */
val Number.quecto get() = QuectoBuilder(bigDecimal)

/** Creates a builder with the 'ronto' (10⁻²⁷) metric prefix. */
val Number.ronto get() = RontoBuilder(bigDecimal)

/** Creates a builder with the 'yocto' (10⁻²⁴) metric prefix. */
val Number.yocto get() = YoctoBuilder(bigDecimal)

/** Creates a builder with the 'zepto' (10⁻²¹) metric prefix. */
val Number.zepto get() = ZeptoBuilder(bigDecimal)

/** Creates a builder with the 'atto' (10⁻¹⁸) metric prefix. */
val Number.atto get() = AttoBuilder(bigDecimal)

/** Creates a builder with the 'femto' (10⁻¹⁵) metric prefix. */
val Number.femto get() = FemtoBuilder(bigDecimal)

/** Creates a builder with the 'pico' (10⁻¹²) metric prefix. */
val Number.pico get() = PicoBuilder(bigDecimal)

/** Creates a builder with the 'nano' (10⁻⁹) metric prefix. */
val Number.nano get() = NanoBuilder(bigDecimal)

/** Creates a builder with the 'micro' (10⁻⁶) metric prefix. */
val Number.micro get() = MicroBuilder(bigDecimal)

/** Creates a builder with the 'milli' (10⁻³) metric prefix. */
val Number.milli get() = MilliBuilder(bigDecimal)

/** Creates a builder with the 'centi' (10⁻²) metric prefix. */
val Number.centi get() = CentiBuilder(bigDecimal)

/** Creates a builder with the 'deci' (10⁻¹) metric prefix. */
val Number.deci get() = DeciBuilder(bigDecimal)

/** Creates a builder with the 'deca' (10¹) metric prefix. */
val Number.deca get() = DecaBuilder(bigDecimal)

/** Creates a builder with the 'hecto' (10²) metric prefix. */
val Number.hecto get() = HectoBuilder(bigDecimal)

/** Creates a builder with the 'kilo' (10³) metric prefix. */
val Number.kilo get() = KiloBuilder(bigDecimal)

/** Creates a builder with the 'mega' (10⁶) metric prefix. */
val Number.mega get() = MegaBuilder(bigDecimal)

/** Creates a builder with the 'giga' (10⁹) metric prefix. */
val Number.giga get() = GigaBuilder(bigDecimal)

/** Creates a builder with the 'tera' (10¹²) metric prefix. */
val Number.tera get() = TeraBuilder(bigDecimal)

/** Creates a builder with the 'peta' (10¹⁵) metric prefix. */
val Number.peta get() = PetaBuilder(bigDecimal)

/** Creates a builder with the 'exa' (10¹⁸) metric prefix. */
val Number.exa get() = ExaBuilder(bigDecimal)

/** Creates a builder with the 'zetta' (10²¹) metric prefix. */
val Number.zetta get() = ZettaBuilder(bigDecimal)

/** Creates a builder with the 'yotta' (10²⁴) metric prefix. */
val Number.yotta get() = YottaBuilder(bigDecimal)

/** Creates a builder with the 'ronna' (10²⁷) metric prefix. */
val Number.ronna get() = RonnaBuilder(bigDecimal)

/** Creates a builder with the 'quetta' (10³⁰) metric prefix. */
val Number.quetta get() = QuettaBuilder(bigDecimal)

/** Creates a builder with the 'kibi' (2¹⁰) binary prefix. */
val Number.kibi get() = KibiBuilder(bigDecimal)

/** Creates a builder with the 'mebi' (2²⁰) binary prefix. */
val Number.mebi get() = MebiBuilder(bigDecimal)

/** Creates a builder with the 'gibi' (2³⁰) binary prefix. */
val Number.gibi get() = GibiBuilder(bigDecimal)

/** Creates a builder with the 'tebi' (2⁴⁰) binary prefix. */
val Number.tebi get() = TebiBuilder(bigDecimal)

/** Creates a builder with the 'pebi' (2⁵⁰) binary prefix. */
val Number.pebi get() = PebiBuilder(bigDecimal)

/** Creates a builder with the 'exbi' (2⁶⁰) binary prefix. */
val Number.exbi get() = ExbiBuilder(bigDecimal)

/** Creates a builder with the 'zebi' (2⁷⁰) binary prefix. */
val Number.zebi get() = ZebiBuilder(bigDecimal)

/** Creates a builder with the 'yobi' (2⁸⁰) binary prefix. */
val Number.yobi get() = YobiBuilder(bigDecimal)

/** Creates a builder with the 'robi' (2⁹⁰) binary prefix. */
val Number.robi get() = RobiBuilder(bigDecimal)

/** Creates a builder with the 'quebi' (2¹⁰⁰) binary prefix. */
val Number.quebi get() = QuebiBuilder(bigDecimal)
