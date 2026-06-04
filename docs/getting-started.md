# Getting Started

KISU models physical quantities as typed Kotlin values. A length is a `Length`, a duration is a `Time`, a speed is a
`Speed`, and so on. The value carries both a numeric magnitude and the unit expression that magnitude is stored in.

The main entry point is the builder DSL in `org.kisu.units.builders`.

```kotlin
import org.kisu.units.builders.*

val distance = 3.kilo.metres
val shorter = 250.metres
val duration = 90.seconds
val payload = 64.kibi.bits
```

The same pattern works for base SI quantities, many derived SI quantities, metric-prefixed units, and binary-prefixed
information values.

## Reading A Measure

Every measure has three useful display forms:

```kotlin
import org.kisu.units.builders.*

val distance = 3.kilo.metres

println(distance.representation) // 3 km
println(distance.canonical.representation) // 3000 m
println(distance.optimal)        // 3 km
println(distance.toString())     // 3 km
```

`representation` is the stored magnitude and stored expression. It does not rescale a non-zero value.

`canonical` converts the same quantity to the quantity's canonical expression. For length, that is metres; for time,
seconds; for information, bits.

`optimal` chooses a readable expression from the unit system. `toString()` delegates to `optimal.representation`, so
printing a measure gives the readable form.

Zero is special: `representation` normalizes zero to the canonical expression so equivalent zero values render the same
way.

## Converting Units

Use `to(...)` when you need a specific target expression. The target expression type must match the measure type.

```kotlin
import org.kisu.prefixes.Metric
import org.kisu.units.base.Metre
import org.kisu.units.base.Second
import org.kisu.units.builders.*

val distance = 3.kilo.metres
val duration = 2.seconds

val metres = distance.to(Metre(Metric.BASE))
val millisecondDuration = duration.to(Second(Metric.MILLI))

println(metres.representation)              // 3000 m
println(millisecondDuration.representation) // 2000 ms
```

For compound expressions, build the target expression with the same expression algebra used by the unit catalog:

```kotlin
import org.kisu.prefixes.Metric
import org.kisu.units.base.Metre
import org.kisu.units.base.Second
import org.kisu.units.builders.*

val speed = 10.metresPerSecond
val kilometresPerSecond = speed.to(Metre(Metric.KILO) / Second())

println(kilometresPerSecond.representation) // 0.01 km/s
```

## Arithmetic

Measures support arithmetic within the same concrete quantity type.

```kotlin
import org.kisu.units.builders.*

val total = 3.kilo.metres + 250.metres
val remaining = total - 125.metres
val doubled = total * 2
val average = total / 2

println(total)     // 3.25 km
println(remaining) // 3.125 km
println(doubled)   // 6.5 km
println(average)   // 1.625 km
```

Addition and subtraction convert both sides to a compatible smaller expression, calculate the result, and return it in
the larger of the two input expressions. Equality and ordering compare canonical values, so equivalent values compare as
equal even when they are stored with different prefixes.

```kotlin
import org.kisu.units.builders.*

val same = 1.kilo.metres == 1000.metres
val ordered = listOf(500.metres, 1.kilo.metres).sorted()
val inRange = 750.metres in 500.metres..1.kilo.metres
```

Quantity-level dimensional arithmetic is not implemented yet. For now, create derived quantities from the catalog:

```kotlin
import org.kisu.units.builders.*

val speed = 10.metresPerSecond
val area = 12.squareMetres
val pressure = 101.3.kilo.pascals
```

## Recipes

### Keep Stored Units But Display Readably

Use `representation` when stored units matter, and use `toString()` or `optimal` when a compact display matters.

```kotlin
import org.kisu.units.builders.*

val sample = 1500.metres

println(sample.representation) // 1500 m
println(sample.optimal)        // 1.5 km
```

### Normalize Before Persisting Or Comparing

Persisting `canonical.representation` avoids storing the same quantity in multiple equivalent forms.

```kotlin
import org.kisu.units.builders.*

val value = 1.5.kilo.metres
val normalized = value.canonical.representation // 1500 m
```

### Decompose A Metric Value Into Prefix Parts

`decomposition` breaks a canonical value into additive prefix components from largest expression to smallest expression.

```kotlin
import org.kisu.units.builders.*

val parts = 1234.metres.decomposition

println(parts.joinToString()) // 1 km, 2 hm, 3 dam, 4 m
```

Decomposition uses the absolute canonical magnitude. It is most useful for display and breakdowns, not for preserving a
negative sign.

### Choose Bits Or Bytes Deliberately

`bits` use binary prefixes such as `kibi`, `mebi`, and `gibi`. `bytes` use metric prefixes such as `kilo`, `mega`, and
`giga`.

```kotlin
import org.kisu.units.builders.*

val memory = 64.kibi.bits
val fileSize = 4.mega.bytes

println(memory.canonical.representation)   // 65536 bit
println(fileSize.canonical.representation) // 4000000 B
```

Information is canonical in whole bits. Fractional prefixed values are allowed only when they convert to a whole number
of bits.

```kotlin
import org.kisu.units.builders.*

val halfKibibit = 0.5.kibi.bits

println(halfKibibit.canonical.representation) // 512 bit
```

### Use The Catalog For Derived Quantities

Use the existing typed builders for derived quantities instead of combining raw numbers and strings.

```kotlin
import org.kisu.units.builders.*

val acceleration = 9.81.metresPerSecondSquared
val energy = 2.5.kilo.joules
val thermalResistance = 4.kelvinsPerWatt
```

This keeps the value typed throughout the call chain and gives you conversion, comparison, display, and scalar
arithmetic for that quantity.
