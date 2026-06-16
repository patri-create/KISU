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

Current measure constructors and destructuring still expose `BigDecimal`. That keeps the JVM source surface stable while
the decimal abstraction is introduced and tested.

## Why This First

Direct `BigDecimal` usage appears across public measure constructors, builders, prefix algebra, exceptions, tests,
documentation, and ABI declarations. Replacing every public signature in one change would combine an architecture
migration with a large source and binary compatibility break.

The wrapper-first path keeps behavior measurable:

- `Magnitude` provides KISU's current decimal construction paths: constants, strings, numbers, `valueOf`, and scaled
  integer values.
- `Magnitude` equality and comparison use numeric value, ignoring `BigDecimal` scale.
- Division uses the instance config, defaulting to `KisuConfig`, and also exposes a scale-plus-rounding overload for
  current decomposition behavior.
- Power, absolute value, sign, scale, and trailing-zero normalization are represented on the wrapper.
- Arithmetic results keep the left operand config.
- `toBigDecimal()` keeps JVM interop explicit during the transition.

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

1. Keep this PR focused on the `Magnitude` contract while preserving current `BigDecimal` call sites.
2. Replace internal `BigDecimal` usage with `Magnitude` in a follow-up PR, starting with prefix algebra and expression
   factors.
3. Define the multiplatform decimal backend behind the same `Magnitude` behavior.
4. Split source sets once the backend choice is validated for JVM, JS, and Native.
5. Plan a separate public API migration if measure constructors or destructuring should expose `Magnitude` directly.
