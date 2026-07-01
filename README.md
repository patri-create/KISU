<p align="center">
  <img src="assets/logo-lettering.svg" alt="KISU" width="420">
</p>

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
println(distance.canonical.representation) // 3000 m
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
- precise numeric handling with `Magnitude`
- readable Kotlin DSL for prefixed quantities
- explicit quantity types instead of raw numbers and strings

KISU supports dimension-aware arithmetic between catalog quantities where a dedicated result type exists:

```kotlin
2.metres * 3.metres == 6.squareMetres
10.metres / 2.seconds == 5.metresPerSecond
5.metresPerSecond * 2.seconds == 10.metres
```

## Project Structure

- `docs/`: narrative getting-started and architecture documentation
- `lib/`: main library module
- `.github/workflows/`: CI, docs deployment, and dependency review
- GitHub milestones and issues: execution roadmap and backlog

## Toolchain

KISU currently builds and tests against Java 25 and Kotlin 2.4.x.

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

Generate the JaCoCo coverage report:

```bash
./gradlew :lib:jacocoTestReport
```

GitHub Actions uploads `lib/build/reports/jacoco/test/jacocoTestReport.xml` to Codecov from pull requests and `main` pushes. Configure a `CODECOV_TOKEN` repository secret for authenticated same-repository PR and `main` uploads; fork PRs use Codecov's tokenless public upload path.

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

## Versioning

KISU keeps the library version in the repository root `gradle.properties` as `VERSION_NAME`.

- Release versions use `MAJOR.MINOR.PATCH`, for example `0.1.0`
- Snapshot versions use `MAJOR.MINOR.PATCH-SNAPSHOT`, for example `0.1.1-SNAPSHOT`
- A release PR can set a stable version for publication, and the next manually triggered bump can move `main` forward to the next snapshot

A repository workflow called `Bump Library Version` is available from the GitHub Actions tab. It is manually triggered with `workflow_dispatch`, so only collaborators with permission to run Actions in the repository can execute it. The workflow computes the next version, updates `VERSION_NAME`, and opens a pull request instead of writing directly to `main`.

The workflow computes the next version automatically from the current `VERSION_NAME`:

- choose `patch`, `minor`, or `major`
- choose whether the result should be a `release` or `snapshot`
- the workflow increments the selected segment, updates `gradle.properties`, and opens a PR

Examples:
- `0.1.7-SNAPSHOT` + `patch` + `release` -> `0.1.8`
- `0.1.8` + `patch` + `snapshot` -> `0.1.9-SNAPSHOT`
- `0.1.8` + `minor` + `release` -> `0.2.0`

A second manual workflow, `Publish GitHub Release`, creates a GitHub Release for the current repository version:

- it reads `VERSION_NAME` from `gradle.properties`
- it fails if the version ends in `-SNAPSHOT`
- it creates tag `vMAJOR.MINOR.PATCH`
- it publishes a GitHub Release with generated release notes

The intended flow is:
- run `Bump Library Version` with `release` to open a PR for the next stable version
- merge that PR into `main`
- run `Publish GitHub Release` from `main`
- run `Bump Library Version` again with `snapshot` to move `main` to the next development version

## Documentation

Generated API docs are built with Dokka and published through the repository workflows.

The generated docs are useful once you already know the model. Narrative docs cover first-use onboarding and design
context:

- [Getting Started](docs/getting-started.md)
- [Architecture Overview](docs/architecture.md)

## Contributing

Contribution guidelines are planned as part of the `0.1.0` documentation baseline.

For now:
- open an issue for bugs or feature requests
- use the existing GitHub issue templates
- keep PRs scoped and include proof or tests for behavioral changes

## License

A repository license file is still pending and tracked in the roadmap. Until that is added, treat usage and redistribution as not yet formally licensed.
