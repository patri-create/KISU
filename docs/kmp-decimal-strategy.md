# Decimal Magnitude Strategy

Issue #44 is the first step toward removing direct `BigDecimal` coupling before a Kotlin Multiplatform split. The chosen
approach is a staged magnitude abstraction instead of moving the project to KMP source sets immediately.

## Decision

KISU now introduces [Magnitude](../lib/src/main/kotlin/org/kisu/Magnitude.kt) as the decimal value wrapper. On the JVM
it delegates to `BigDecimal`, preserving the current precision, comparison, parsing, rendering, and arithmetic
expectations while giving the library a stable type to migrate internals toward.

[MagnitudeConfig](../lib/src/main/kotlin/org/kisu/MagnitudeConfig.kt) carries the arithmetic `MathContext`. The global
[KisuConfig](../lib/src/main/kotlin/org/kisu/KisuConfig.kt) object is the default config used by `Magnitude`, so
existing global precision behavior remains available through `KisuConfig.precision`. A caller that needs different
arithmetic for one value can pass a separate `MagnitudeConfig` to that `Magnitude` instance.

Measure constructors, destructuring, prefix algebra, expression factors, and builders now expose `Magnitude`. Direct
JVM decimal access is isolated behind `Magnitude.toBigDecimal()` for explicit interop and delegate tests.

## Why This First

Direct decimal usage appeared across public measure constructors, builders, prefix algebra, exceptions, tests,
documentation, and ABI declarations. This migration deliberately accepts the source and binary compatibility break so
KISU's numeric surface is `Magnitude` before the multiplatform backend work starts.

The wrapper-first path keeps behavior measurable:

- `Magnitude` provides KISU's current decimal construction paths: constants, strings, numbers, `valueOf`, and scaled
  integer values.
- `Magnitude` equality and comparison use numeric value, ignoring `BigDecimal` scale.
- Division uses the instance config, defaulting to `KisuConfig`, and also exposes `MathContext` and
  scale-plus-rounding overloads for current decomposition behavior.
- Power, absolute value, sign, scale, and trailing-zero normalization are represented on the wrapper.
- `Magnitude` extends `Number`, so the existing numeric builder DSL also accepts magnitude values.
- Arithmetic results keep the left operand config.
- `toBigDecimal()` keeps JVM interop explicit at the wrapper boundary.

## KMP Constraints

A later KMP split still has to choose the actual decimal backend for JS and Native. The wrapper gives that backend the
decimal surface KISU currently uses: construction, comparison, exact addition/subtraction/multiplication, rounded
division, powers, normalization, string conversion, scale, and sign/fraction checks.

The JVM behavior to preserve is:

- `MathContext.DECIMAL128` as the default precision.
- `KisuConfig.precision` as the global precision override.
- numeric equality independent of decimal scale.
- canonical measure equality based on numerically equal magnitudes.
- current rendering based on the delegated decimal string representation and explicit trailing-zero stripping where the
  measure layer already does that today.

## Migration Path

1. Introduce the `Magnitude` contract while preserving current behavior.
2. Replace public and internal `BigDecimal` usage with `Magnitude` across measures, builders, prefix algebra, expression
   factors, tests, docs, and ABI declarations.
3. Define the multiplatform decimal backend behind the same `Magnitude` behavior.
4. Split source sets once the backend choice is validated for JVM, JS, and Native.
