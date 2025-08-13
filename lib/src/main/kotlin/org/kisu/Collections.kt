package org.kisu

/**
 * A function type that determines whether the receiver is considered equal to the [other] value.
 *
 * @param T the type of elements being compared
 * @receiver the current element to compare
 * @param other the element to compare against
 * @return `true` if the elements are considered equal, `false` otherwise
 */
typealias Matcher<T> = T.(T) -> Boolean

/**
 * A function type that merges the receiver with the [other] value into a single result.
 *
 * @param T the type of elements being merged
 * @receiver the current element to merge
 * @param other the element to merge with
 * @return the merged result
 */
typealias Merger<T> = T.(T) -> T

/**
 * Returns a [Set] containing elements present in both this collection and [other],
 * using the provided [equalTo] function to determine equality and [mergeWith] to
 * combine matching elements into a single value.
 *
 * If multiple matches are found, the merged value from [mergeWith] is added to the result set.
 *
 * @param T the element type
 * @param other the other collection to intersect with
 * @param equalTo the comparison function used to determine if two elements match.
 * Defaults to reference equality via [id].
 * @param mergeWith the merge function used to combine matching elements.
 * Defaults to [mergeRight], keeping the element from [other].
 * @return a [Set] of merged elements found in both collections
 */
fun <T> Iterable<T>.intersect(
    other: Iterable<T>,
    equalTo: Matcher<T> = ::id,
    mergeWith: Merger<T> = ::mergeRight
): Set<T> = buildSet {
    this@intersect.forEach { left ->
        other.forEach { right ->
            if (left.equalTo(right)) {
                add(left.mergeWith(right))
            }
        }
    }
}

/**
 * Merge function that always returns the [left] value, ignoring [right].
 *
 * @param left the left element
 * @param right the right element
 * @return [left]
 */
@Suppress("UnusedParameter")
fun <T> mergeLeft(left: T, right: T) = left

/**
 * Merge function that always returns the [right] value, ignoring [left].
 *
 * @param left the left element
 * @param right the right element
 * @return [right]
 */
@Suppress("UnusedParameter")
fun <T> mergeRight(left: T, right: T) = right

/**
 * Default equality matcher that returns `true` if [left] is equal to [right]
 * according to [Any.equals].
 *
 * @param left the left element
 * @param right the right element
 * @return `true` if [left] equals [right], `false` otherwise
 */
private fun <T> id(left: T, right: T) = left == right
