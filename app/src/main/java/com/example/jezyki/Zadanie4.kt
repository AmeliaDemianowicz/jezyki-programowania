package com.example.jezyki

/**
 * Funkcja oblicza n pierwszych wyrazów ciągu Fibbonaciego z użyciem pętli for i dodaje kolejne wyniki do listy
 *
 * @param n liczba pierwszych wyrazów ciągu fibbonaciego
 * @return lista n kolejnych wyrazów ciągu fibbonaciego
 */
fun fibbonaci(n: Int): List<Int> {
    require(n >= 0) { "Liczba n pierwszych elementów musi być liczba naturalną" }

    val fibbonacisequence = mutableListOf(0, 1)
    for (i in 2 until n) {
        fibbonacisequence.add(fibbonacisequence[i - 1] + fibbonacisequence[i - 2])
    }
    return fibbonacisequence.take(n)

}

/**
 * Funkcja sprawdzająca poprawność liczby pierwszych wyrazów
 * @param n liczba pierwszych wyrazów ciągu fibbonaciego
 * @return lista n kolejnych wyrazów ciągu fibbonaciego
 */
fun fibbonaciRecursion(n: Int): List<Int> {
    require(n >= 0) { "Liczba n pierwszych elementów musi być liczba naturalną" }

    /**
     * Funkcja oblicza n pierwszych wyrazów ciągu Fibbonaciego z użyciem rekurencji i dodaje kolejne wyniki do listy
     *
     * @param n liczba pierwszych wyrazów ciągu fibbonaciego
     * @param sequence lista przechowywująca dotychczasowo obliczone wartości ciągu, aby nie stracić wartości w kolejnych iteracjach
     * @return lista n kolejnych wyrazów ciągu fibbonaciego
     */
    fun fibonacciWithAccumulator(n: Int, sequence: List<Int>): List<Int> {
        if (sequence.size >= n) { // warunek zakończenia rekurencji
            return sequence.take(n)
        }

        val newSequence = sequence.plus(sequence[sequence.size - 1] + sequence[sequence.size - 2])
        return fibonacciWithAccumulator(n, newSequence)
    }

    return fibonacciWithAccumulator(n, mutableListOf(0, 1))
}

fun main() {
    println(fibbonaci(5))
    println(fibbonaciRecursion(5))
}
//źródła: https://www.baeldung.com/kotlin/fibonacci-series
//https://www.javaguides.net/2023/07/kotlin-program-to-calculate-fibonacci.html
//https://arrow-kt.io/learn/collections-functions/recursive/
// https://kt.academy/article/fk-cp-drop-take