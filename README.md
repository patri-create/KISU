# KISU

KISU is a Kotlin library for working with SI quantities, prefixes, and derived units with typed value objects.

It gives you:
- strongly typed measures such as `Length`, `Mass`, `Speed`, and `Area`
- metric and binary prefix builders like `3.kilo.metres` or `4.kibi.bits`
- conversion, normalization, comparison, decomposition, and arithmetic within the same quantity type
- a growing catalog of SI base, derived, photometric, chemistry, thermodynamics, mechanics, and electromagnetic units

## Status

KISU is under active development.

Current state:
- the library surface is already broad
- tests and Dokka are in place
- publishing and release automation are planned, but not finished yet

Roadmap and backlog are tracked in GitHub milestones and issues.

## Example

```kotlin
import org.kisu.units.builders.kilo
import org.kisu.units.builders.metres
import org.kisu.units.builders.seconds

val distance = 3.kilo.metres
val shorter = 250.metres
val time = 90.seconds

val total = distance + shorter

println(distance.representation) // 3 km
println(distance.canonical)      // 3000 m
println(total)                   // 3.25 km
println(time.representation)     // 90 s
```

Metric and binary prefixes are supported through the builder DSL:

```kotlin
import org.kisu.units.builders.giga
import org.kisu.units.builders.hertz
import org.kisu.units.builders.kibi
import org.kisu.units.builders.bits

val cpuClock = 3.2.giga.hertz
val payload = 64.kibi.bits
```

You can also create base-unit values directly:

```kotlin
import org.kisu.units.builders.metres
import org.kisu.units.builders.squareMetres

val width = 12.metres
val area = 24.squareMetres
```

## Measure Semantics

- `representation` is the stored value and stored unit, without rescaling
- `canonical` is the same quantity normalized to the type's canonical unit
- `optimal` is the human-friendly measure form used by `toString()`

Two non-obvious cases are worth calling out:
- `Kilogram` normalizes through `g` internally so metric prefix arithmetic stays coherent, but its public canonical form is still `kg`
- `Information` is canonical in `bit`, so fractional prefixed values are only valid when they still resolve to a whole number of bits

## What KISU Focuses On

- SI and IEC-style unit modeling
- precise numeric handling with `BigDecimal`
- readable Kotlin DSL for prefixed quantities
- explicit quantity types instead of raw numbers and strings

KISU is moving toward dimension-aware arithmetic between quantities. For example, a future milestone targets operations like:

```kotlin
2.metres * 3.metres == 6.squareMetres
10.metres / 2.seconds == 5.metresPerSecond
```

That work is tracked in the roadmap for milestone `0.3.0`.

## Project Structure

- `lib/`: main library module
- `.github/workflows/`: CI, docs deployment, and dependency review
- GitHub milestones and issues: execution roadmap and backlog

## Toolchain

KISU currently builds and tests against Java 25 and Kotlin 2.3.x.

- CI uses Temurin JDK 25 in every workflow
- the Gradle wrapper is the supported entrypoint for local builds
- the Gradle toolchain configuration can provision the required JDK automatically when it is not installed locally

If you do have a local JDK installed, prefer JDK 25 so local execution matches CI directly.

## Build

From the repository root:

```bash
./gradlew :lib:build
```

Run tests:

```bash
./gradlew :lib:test
```

Generate API docs with Dokka:

```bash
./gradlew :lib:dokkaGenerate
```

Static analysis:

```bash
./gradlew :lib:detekt
```

## Installation

There is no public package distribution configured yet.

Until publishing is set up, KISU should be consumed from source or through local builds. Publishing support is planned as part of milestone `0.1.0`.

## Documentation

Generated API docs are built with Dokka and published through the repository workflows.

The generated docs are useful once you already know the model. The root README and the planned getting-started guide are intended to cover first-use onboarding.

## Contributing

Contribution guidelines are planned as part of the `0.1.0` documentation baseline.

For now:
- open an issue for bugs or feature requests
- use the existing GitHub issue templates
- keep PRs scoped and include proof or tests for behavioral changes

## License

A repository license file is still pending and tracked in the roadmap. Until that is added, treat usage and redistribution as not yet formally licensed.
