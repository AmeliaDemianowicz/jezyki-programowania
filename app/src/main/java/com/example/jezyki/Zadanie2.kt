package com.example.jezyki


/**
 * Funkcja oblicza część wspólną dwóch list z dowolnym typem elementów
 *
 * @param x pierwszy multizbiór
 * @param y drugi multizbiór
 * @return zbiór elementów, które są wspólne dla oby zadanych list
 */
fun <T> intersect(x: List<T>, y: List<T>): Set<T> {
    return x.intersect(y)
}

fun main() {
    println(intersect(listOf(1, 1, 2, 3), listOf(2, 1, 1, 2)))
}
//https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/intersect.html
//https://stackoverflow.com/questions/53687530/intersection-of-two-lists-maintaining-duplicate-values-in-kotlin
//https://kotlinlang.org/docs/set-operations.html