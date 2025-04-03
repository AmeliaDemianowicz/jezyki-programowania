package com.example.jezyki

/**
 * Funkcja dla zadanego zbioru zwraca listę wszystkich jego podzbiorów wraz z pełnym zbiorem i zbiorem pustym
 * @param set zbiór z zadanymi elementami
 * @return zbiór podzbiorów wszystkich elementów zbioru set
 */
fun <T> permutations(set: Set<T>): Set<Set<T>> {
    if (set.isEmpty()) {
        return setOf(emptySet())
    }

    val head = set.first()
    val tail = set.drop(1).toSet()
    val tailPermutations = permutations(tail)

    val additionalPermutations = tailPermutations.map { it.plus(head) }.toSet()

    return tailPermutations.union(additionalPermutations)
}

fun main() {
    println(permutations(setOf(1, 2)))
}
//https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/empty-set.html
//https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/first.html
//https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.text/drop.html
//https://www.baeldung.com/kotlin/maps
//https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/union.html