package com.example.jezyki

import kotlin.math.sqrt
import kotlin.test.assertEquals
import kotlin.test.assertFails


/**
 * Funkcja oblicza pole trójkąta na podstawie zadanych długości boków. Używa do tego wzoru Herona. Sprawdza poprawność wprowadzanych paremetrów i nierówność trójkąta
 *
 * @param a pierwsza długość boku trójkąta
 * @param b druga długość boku trójkąta
 * @param c trzecia długość boku trójkąta
 * @return pole trójkąta o zadanych bokach, w formie liczby zmienno przecinkowej
 */
fun heron(a: Double, b: Double, c: Double): Double {
    require(a > 0 && b > 0 && c > 0) { "Boki muszą być dodatnie" }
    require(a + b > c && a + c > b && b + c > a) { "Podane długości nie spełniają nierówności trójkąta" }

    val p = (a + b + c) / 2
    var S = sqrt(p * (p - a) * (p - b) * (p - c))
    return S
}

// wykorzystane źródła: https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.math/
// https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/require.html
