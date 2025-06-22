package org.kisu

/**
 * Returns the current value if it is not `null`, or the result of [block] otherwise.
 *
 * This is a convenient way to provide a fallback value lazily,
 * only invoking [block] if the receiver is `null`.
 *
 * @param block A lambda that produces a fallback value if the receiver is `null`.
 * @return The receiver if not `null`, otherwise the result of [block].
 */
fun <T> T?.orElse(block: () -> T): T = this ?: block()
