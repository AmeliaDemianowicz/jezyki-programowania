package com.example.jezyki

/**
 * Funkcja oblicza wszystkie kolejne elementy ciągu collaza zaczynając od zadanaego c0 az do momentu wpadnięcia w pętlę 4,2,1
 *
 * @param c0 wartośc elementu c0
 * @return lista kolejnych elementów ciągu przed wpadnięciem w pętlę 4,2,1
 */

fun collatz(c0: Int): List<Int> {
    if (c0 <= 0) throw IllegalArgumentException("c0 musi być liczbą naturalną większą od zera")
    val sequence = mutableListOf(c0)
    var current = c0

    for (i in 1..Int.MAX_VALUE) {
        if (current == 1) break

        current = if (current % 2 == 0) {
            current / 2 // Jeśli liczba parzysta, dzielimy przez 2
        } else {
            3 * current + 1 // Jeśli nieparzysta, stosujemy wzór 3n + 1
        }
        if (current <= 0) throw IllegalStateException("Wartość ciągu przekroczyła zakres liczb całkowitych")
        sequence.add(current)
    }
    return sequence
}

/**
 * Funkcja oblicza najdłuższą wartość ciągu przed wpadnięciem w cykl, jego długosc i wartośc c0 dla której tak sie dzieję
 * Oblicza tez wartość tego elementu, dla którego tak sie dzieję
 * @param range zakres od liczby do liczby, dla której sprawdzane sa opisane zalezności
 */
fun collatzTest(range: IntRange): Triple<Int, Int, Int> {
    var maxLength = 0
    var maxValue = 0
    var maxC0 = 0

    for (c0 in range) {
        val sequence = collatz(c0)
        val maxInSequence = sequence.max()

        if (sequence.size > maxLength) {
            maxLength = sequence.size
            maxC0 = c0
        }

        if (maxInSequence > maxValue) {
            maxValue = maxInSequence
        }
    }

    return Triple(maxC0, maxLength, maxValue)

}

fun main() {
    println(collatz(10))
    val (maxC0, maxLength, maxValue) = collatzTest(1..1)

    println("Najdłuższy ciąg, przed wpadnięciem ciągu w cykl, dla c0 = $maxC0 ma długość $maxLength")
    println("Największa wartość w tym ciągu przed wpadnięciem w cykl to $maxValue")

}
// źródła: https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-int/-companion/-m-a-x_-v-a-l-u-e.html
//https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-illegal-state-exception/
//https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-illegal-argument-exception/
//https://www.baeldung.com/kotlin/returning-multiple-values
//czat GPT

