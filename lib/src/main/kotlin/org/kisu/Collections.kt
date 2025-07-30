package org.kisu

import java.util.SortedSet
import java.util.TreeSet

typealias Matcher<T> = T.(T) -> Boolean
typealias Merger<T> = T.(T) -> T

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

fun<T> mergeLeft(left: T, right:T) = left

fun<T> mergeRight(left: T, right:T) = right

inline fun <T> buildSortedSet(builderAction: SortedSet<T>.() -> Unit): SortedSet<T> {
    val set = TreeSet<T>()
    set.builderAction()
    return set
}

private fun <T> id(left: T, right: T) = left == right
