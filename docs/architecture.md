# Architecture Overview

KISU models physical quantities as typed values. A value such as `3.kilo.metres` is not stored as a formatted string; it
is stored as a numeric magnitude and a unit expression, wrapped in a concrete measure type.

The core layers are:

- [prefixes](../lib/src/main/kotlin/org/kisu/prefixes): scale factors such as `kilo`, `milli`, and `kibi`
- [unit expressions](../lib/src/main/kotlin/org/kisu/units/representation): symbolic unit forms such as `m`, `m²`,
  `m/s`, and `J/(K·mol)`
- [measures](../lib/src/main/kotlin/org/kisu/units/Measure.kt): typed quantities such as `Length`, `Time`, `Speed`, and
  `Area`
- [builders](../lib/src/main/kotlin/org/kisu/units/builders): Kotlin DSL entry points such as `3.kilo.metres` and
  `64.kibi.bits`

For example, `2 km` decomposes into:

```text
Measure<Metre, Length>
├── magnitude: 2
└── expression: Metre(Metric.KILO)
    ├── prefix: Metric.KILO
    │   ├── symbol: "k"
    │   └── factor: 1000
    └── unit: Unit("m")
        └── symbol: "m"
```

That separation is the main architectural rule: quantities own values, expressions own units, and prefixes own scale.

## Prefix Systems

[Prefix](../lib/src/main/kotlin/org/kisu/prefixes/Prefix.kt) is the common contract for a scale coordinate with a symbol.
It supports comparison by factor:

```kotlin
import org.kisu.prefixes.Metric

Metric.MEGA > Metric.KILO   // true
```

Prefix-to-prefix conversion intentionally lives on unit expressions rather than raw prefixes. Expressions carry the
`Scale` context needed to resolve whether a prefix factor is an absolute multiplier or an exponent coordinate:

```kotlin
import org.kisu.prefixes.Metric
import org.kisu.units.base.Metre

Metre(Metric.KILO).to(Metre(Metric.MILLI)) // 1_000_000
```

The self type on `Prefix<Self>` is deliberate. It prevents mixing unrelated systems in prefix operations:

```kotlin
import org.kisu.prefixes.Binary
import org.kisu.prefixes.Metric

Metric.KILO * Metric.MEGA  // valid
Metric.KILO * Binary.KIBI  // does not compile
```

[System](../lib/src/main/kotlin/org/kisu/prefixes/primitives/System.kt) describes the complete ordered set around a
prefix. Every system exposes:

- `canonical`: the base scale, normally factor `1`
- `all`: every available prefix sorted from smallest to largest
- `smallest` and `largest`: the range boundaries
- `find(factor)`: the largest known prefix whose factor does not exceed the requested factor

KISU currently defines:

- [Metric](../lib/src/main/kotlin/org/kisu/prefixes/Metric.kt): SI-style powers of ten from `quecto` to `quetta`
- [Binary](../lib/src/main/kotlin/org/kisu/prefixes/Binary.kt): IEC powers of two from `kibi` to `quebi`
- [Decimal](../lib/src/main/kotlin/org/kisu/prefixes/Decimal.kt): powers of ten in 1000-step names for decimal storage
  notation

Metric and binary systems are exposed through the public builder DSL today. `Decimal` is a prefix system in the model,
but it is not the primary user-facing builder path.

Prefix multiplication and division can produce factors outside the named range. In that case, the closest available
prefix is kept and the remaining scale is carried separately as an overflow factor at the scalar-expression layer.

## Unit Expressions

The expression layer models unit symbols independently from measured values. It is built from four main types:

- [Unit](../lib/src/main/kotlin/org/kisu/units/representation/Unit.kt): a raw unit symbol plus an integer exponent
- [Scalar](../lib/src/main/kotlin/org/kisu/units/representation/Scalar.kt): one prefixed unit, such as `m`, `km`, `kg`,
  or `Kibit`
- [Product](../lib/src/main/kotlin/org/kisu/units/representation/Product.kt): multiplication of two expressions
- [Quotient](../lib/src/main/kotlin/org/kisu/units/representation/Quotient.kt): division of two expressions

[Expression](../lib/src/main/kotlin/org/kisu/units/representation/Expression.kt) is the common base. An expression is
also a prefix and a system, so it can be converted, normalized, enumerated, and compared. Its key properties are:

- `factor`: scale relative to the canonical expression
- `symbol`: printable unit symbol
- `canonical`, `all`, `smallest`, `largest`: the available expressions in the same system
- `factors`: normalized scalar factors that describe the expression dimensionally

Expression equality is based on concrete expression type and symbol. `toString()` returns `symbol`.

### Unit And Exponent

`Unit` is intentionally small. It stores a symbol and an [Exponent](../lib/src/main/kotlin/org/kisu/units/representation/Exponent.kt).
Multiplying or dividing units with the same symbol adds or subtracts exponents:

```kotlin
import org.kisu.units.representation.Unit

Unit("m") * Unit("m") // Unit("m", 2), rendered as m²
Unit("s", 2) / Unit("s") // Unit("s"), rendered as s
```

`Unit` does not know about prefixes. `km` is represented by a `Scalar` with prefix `Metric.KILO` and unit `Unit("m")`.

### Scalar

`Scalar<A, Self>` is an atomic expression made from a prefix and a unit. Examples in the catalog include:

- [Metre](../lib/src/main/kotlin/org/kisu/units/base/Length.kt): metric length unit
- [Second](../lib/src/main/kotlin/org/kisu/units/base/Time.kt): metric time unit
- [Kilogram](../lib/src/main/kotlin/org/kisu/units/base/Mass.kt): metric mass unit with special SI handling
- [Bit](../lib/src/main/kotlin/org/kisu/units/base/Information.kt): binary information unit

A scalar delegates system behavior to
[ScalarSystem](../lib/src/main/kotlin/org/kisu/prefixes/primitives/ScalarSystem.kt), which turns a prefix system into
the matching set of scalar expressions. For `Metre(Metric.KILO)`, that system can enumerate `qm`, `rm`, `ym`, ..., `m`,
..., `km`, ..., `Qm`.

Scalars expose:

- `factor`: prefix factor multiplied by any overflow factor
- `symbol`: prefix symbol plus unit symbol
- `inverted`: the same scalar with the unit exponent negated
- `positive` and `zero`: exponent-derived helpers used by quotient rendering
- `times` and `div`: expression algebra for building products and quotients

Repeated compatible scalars can merge their unit exponents:

```kotlin
import org.kisu.units.base.Metre

val areaExpression = Metre() * Metre()

println(areaExpression.symbol) // m²
```

### Product

`Product<A, B>` represents `A * B`. It multiplies expression factors, combines scalar factors, removes zero-exponent
factors, and renders a stable dot-separated symbol:

```kotlin
import org.kisu.units.base.Metre
import org.kisu.units.base.Second

val impulseLike = Metre() * Second()

println(impulseLike.symbol) // m·s
```

The nested generic type preserves how the expression was constructed, while `factors` and `symbol` expose the normalized
meaning.

### Quotient

`Quotient<A, B>` represents `A / B`. It divides expression factors, subtracts matching scalar exponents, inverts
denominator-only factors, removes zero-exponent factors, and parenthesizes multi-factor denominators:

```kotlin
import org.kisu.units.base.Metre
import org.kisu.units.base.Second

val speedExpression = Metre() / Second()
val dimensionless = Metre() / Metre()

println(speedExpression.symbol) // m/s
println(dimensionless.symbol)   // empty string
```

Many derived quantity expressions are typealiases over products and quotients. For example,
[Speed](../lib/src/main/kotlin/org/kisu/units/kinematics/linear/Speed.kt) uses
`Quotient<Metre, Second>` as its expression type.

## Measure

[Measure](../lib/src/main/kotlin/org/kisu/units/Measure.kt) is the base class for values. A concrete measure stores:

- a `BigDecimal` magnitude
- an expression `A`
- a factory function for creating the same concrete measure type after conversion or arithmetic

Its generic bounds are the key to the type model:

```kotlin
abstract class Measure<A, Self : Measure<A, Self>>
    where A : Expression<A>, A : System<A>
```

The expression type `A` must be both an expression and a system. The self type keeps fluent operations strongly typed:
adding `Length` to `Length` returns `Length`, while adding `Length` to `Time` does not compile.

`Measure` provides:

- `to(targetExpression)`: convert to a specific compatible expression
- `canonical`: convert to the expression's canonical form
- `optimal`: choose a readable expression whose magnitude is at least `1` when possible
- `representation`: render the stored magnitude and expression without rescaling non-zero values
- `+` and `-`: same-measure arithmetic
- scalar `*` and `/`: multiply or divide by numbers
- canonical equality, hashing, ordering, ranges, and sorting
- `decomposition`: split a canonical value into additive prefix components

Addition and subtraction convert both sides to the smaller expression, compute the result, then return it in the larger
of the two input expressions:

```kotlin
import org.kisu.units.builders.*

val total = 3.kilo.metres + 250.metres

println(total.representation) // 3.25 km
```

Equality and ordering use canonical values, so equivalent measures compare as equal even when stored with different
prefixes:

```kotlin
import org.kisu.units.builders.*

println(1.kilo.metres == 1000.metres) // true
```

Zero has one special rendering rule: `representation` uses the canonical expression for zero so equivalent zero values
produce the same display.

## Catalog And Builders

Concrete quantity files pair a measure type with its expression type. Simple quantities use one scalar expression:

```kotlin
class Length internal constructor(magnitude: BigDecimal, expression: Metre) :
    Measure<Metre, Length>(magnitude, expression, ::Length)

class Metre(...) : Scalar<Metric, Metre>(...)
```

Derived quantities use products, quotients, or catalog scalar units:

```kotlin
class Speed(magnitude: BigDecimal, expression: MetrePerSecond) :
    Measure<Speed.MetrePerSecond, Speed>(magnitude, expression, ::Speed) {

    typealias MetrePerSecond = Quotient<Metre, Second>
}
```

The builder package maps numbers and prefix builders to those concrete measure constructors:

- [NumberExtensions](../lib/src/main/kotlin/org/kisu/units/builders/NumberExtensions.kt): base forms like `3.metres`,
  `90.seconds`, and `10.metresPerSecond`
- [PrefixExtensions](../lib/src/main/kotlin/org/kisu/units/builders/PrefixExtensions.kt): prefix builder starters like
  `3.kilo` and `64.kibi`
- [MetricUnitBuilder](../lib/src/main/kotlin/org/kisu/units/builders/MetricUnitBuilder.kt): metric-prefixed unit
  completions like `3.kilo.metres`
- [BinaryUnitBuilder](../lib/src/main/kotlin/org/kisu/units/builders/BinaryUnitBuilder.kt): binary-prefixed information
  completions like `64.kibi.bits`

Mass is the main special case. The public SI base unit is kilogram, but prefix arithmetic is easier around grams. The
[Kilogram](../lib/src/main/kotlin/org/kisu/units/base/Mass.kt) scalar normalizes through `g` internally while exposing
`kg` as the public canonical mass expression.

Information is another special case. It uses the binary prefix system for bits, and construction validates that values
resolve to whole bits.

## Data Flow

A typical value moves through the system like this:

1. `3.kilo.metres` creates a metric prefix builder with magnitude `3`.
2. The `metres` builder creates `Length(3, Metre(Metric.KILO))`.
3. `representation` prints the stored value as `3 km`.
4. `canonical` converts through `Metre(Metric.KILO).to(Metre(Metric.BASE))` and returns `3000 m`.
5. `optimal` searches the expression system for a readable expression and returns the largest expression whose absolute
   converted magnitude is at least `1`.
6. Equality, ordering, and comparison use canonical magnitudes so storage prefix does not affect value semantics.

## Current Boundaries

The expression layer can model products and quotients today. Quantity-level dimensional arithmetic is not implemented
yet, so these operations are intentionally absent at the `Measure` level:

```kotlin
2.metres * 3.metres  // does not produce 6.squareMetres
10.metres / 2.seconds // does not produce 5.metresPerSecond
```

Use catalog builders for derived quantities until dimension-aware measure arithmetic is added:

```kotlin
import org.kisu.units.builders.*

val area = 6.squareMetres
val speed = 5.metresPerSecond
```

When adding a new quantity, follow the existing catalog pattern:

1. Choose or create the expression type.
2. Implement the concrete `Measure` subtype.
3. Add `Number` and, when applicable, metric or binary builder extensions.
4. Add tests for construction, conversion, canonical/optimal rendering, and arithmetic inherited from `Measure`.
5. Update `CANONICAL_ORDER` when introducing a new scalar unit that participates in compound symbol ordering.
