package com.example.jezyki2

import kotlin.math.*
/**
 * Klasa wielomian przyjmująca liste współczynników wielomianu, sprawdzająca poprawnosc listy, nie może byc pusta i nie może zawierać zbędnych zer na początku
 * @param coefficients lista wartości double, którymi sa współczynniki wielomianu
 */
class Polynomial(private var coefficients: List<Double>) {
    init {
        require(coefficients.isNotEmpty()) { "Coefficients list can't be empty!" }
        require(coefficients.size == 1 || coefficients[0] != 0.0) { "Coefficients list can't begin with 0 value" }
    }

    /**
     * Funkcja degree zwracająca stopień wielomianu o zadanych współczynnikach
     * @return stopień wielomianu w postaci liczby całkowitej dodatniej
     */
    fun degree(): Int {
        return coefficients.size - 1
    }

    /**
     * Funkcja invoke, która dla każdego współczynnika wymnaża jego wartośc z zadanym parametrem x i sumuje je ze sobą
     * @param x liczba dla której chcemy obliczyc wartośc wielomianu
     * @return policzona wartośc po podstawieniu
     */
    operator fun invoke(x: Double): Double {
        var accumulator = 0.0

        for ((index, coefficient) in coefficients.withIndex()) {
            accumulator += coefficient * x.pow(degreeAtIndex(index))
        }

        return accumulator
    }

    /**
     * Funkcja operator plus, która pozwala zsumowac ze sobą dwa wielomiany i zapisac to jako p1+p2
     * @param other obiekt klasy wielomian o zadanych współczynnikach
     * @return zmieniony obiekt klasy wielomian
     */
    operator fun plus(other: Polynomial): Polynomial {
        var accumulator = mutableListOf<Double>()

        val thisCoefficients = coefficients.reversed()
        val otherCoefficients = other.coefficients.reversed()
        val maxSize = max(coefficients.size, other.coefficients.size)

        for (index in 0..<maxSize) {

            val thisCoefficient = thisCoefficients.getOrNull(index) ?: 0.0
            val otherCoefficient = otherCoefficients.getOrNull(index) ?: 0.0
            accumulator += thisCoefficient + otherCoefficient
        }

        return Polynomial(accumulator.reversed())
    }
    /**
     * Funkcja pozwalająca dodać do wielomianu 1 wielomian 2 z uzyciem operatora +=
     * @param other obiekt klasy wielomian o zadanych współczynnikach
     */
    operator fun plusAssign(other: Polynomial) {
        coefficients = plus(other).coefficients
    }
    /**
     * Funkcja operator minus, która pozwala odjąć od siebie dwa wielomiany i zapisac to jako p1-p2
     * @param other obiekt klasy wielomian
     * @return obiekt klasy wielomian po odejmowaniu
     */
    operator fun minus(other: Polynomial): Polynomial {
        var accumulator = mutableListOf<Double>()

        val thisCoefficients = coefficients.reversed()
        val otherCoefficients = other.coefficients.reversed()
        val maxSize = max(coefficients.size, other.coefficients.size)

        for (index in 0..<maxSize) {

            val thisCoefficient = thisCoefficients.getOrNull(index) ?: 0.0
            val otherCoefficient = otherCoefficients.getOrNull(index) ?: 0.0
            accumulator += thisCoefficient - otherCoefficient
        }

        return Polynomial(accumulator.reversed())
    }
    /**
     * Funkcja pozwalająca odjąć do wielomianu 1 wielomian 2 z uzyciem operatora -=
     * @param other obiekt klasy wielomian o zadanych współczynnikach
     */
    operator fun minusAssign(other: Polynomial) {
        coefficients = minus(other).coefficients
    }

    operator fun times(other: Polynomial): Polynomial {
        var accumulator = mutableListOf<Double>()

        val thisCoefficients = coefficients.reversed()
        val otherCoefficients = other.coefficients.reversed()
        val maxSize = max(coefficients.size, other.coefficients.size)

        for (index in 0..<maxSize) {

            val thisCoefficient = thisCoefficients.getOrNull(index) ?: 0.0
            val otherCoefficient = otherCoefficients.getOrNull(index) ?: 0.0
            accumulator += thisCoefficient * otherCoefficient
        }

        return Polynomial(accumulator.reversed())
    }


    /**
     * Funkcja pozwalająca pomnożyć dwa wielomiany,uzywająć operatora *=
     * @param other obiekt klasy wielomian o zadanych współczynnikach
     */
    operator fun timesAssign(other: Polynomial) {
        coefficients = times(other).coefficients
    }
    /**
     * Funkcja odpowiednio zapisująca wielomian, w zależności jaka cyfra jest pierwsza, ostatnia i tp.
     * @return string w postaci zapisanego odpowiednio wielomianu
     */
    override fun toString(): String {
        var accumulator = ""

        if (coefficients.size == 1) {
            return "W(x) = ${coefficients[0]}"
        }

        // coefficients.withIndex aby pozysać indeksy na których to jest
        for ((index, coefficient) in coefficients.withIndex()) {
            // nie robi tych if co pod nim i wchodzi odrazu do pętli kolejnej iteracji
            if (coefficient == 0.0) {
                continue
            }
            // dla najwyższej potęgi pierwszego co stoi ma sie pokazywać minus jeżeli jest ujemny
            if (index == 0 && coefficient < 0.0) {
                accumulator += "-"
            }
            //
            if (index > 0 && coefficient < 0.0) {
                accumulator += " - "
            }
            if (index > 0 && coefficient > 0.0) {
                accumulator += " + "
            }

            accumulator += "${abs(coefficient)}" // abs aby uzyskać wartośc bezwzględną

            // ostatni element x0 ma sie pokazywać bez x jako liczba sama
            if (degreeAtIndex(index) > 0) {
                accumulator += "x"
            }
            // a dla każdego wiekszego od 1 dodajemy zapis x^...
            if (degreeAtIndex(index) > 1) {
                accumulator += "^${degreeAtIndex(index)}"
            }
        }

        return "W(x) = ${accumulator}"
    }

    /**
     * Funkcja prywatna zwracająca stopień dla danego indeksu
     * @param index numer indeksu dla którego chcemu sprawdzic stopień
     * @return stopień wielomianu
     */
    private fun degreeAtIndex(index: Int): Int {
        return degree() - index
    }
}

fun main() {
    val polynomial =
        Polynomial(
            listOf(
                // xn (3)
                -1.0,
                // xn-1 (2)
                -2.0,
                // xn-2 (1)...
                -1.0,
                // xn-3 (0)
                3.0
            )
        )

    println(polynomial)
    println(polynomial(1.0))
    println(polynomial(-1.0))
    println(polynomial + Polynomial(listOf(1.0, 2.0)))

    println(Polynomial(listOf(0.0)))

    val x = Polynomial(listOf(2.0, 3.0))
    val y = Polynomial(listOf(4.0, 6.0))

    x *= y
    val a = Polynomial(listOf(2.0, 3.0))
    val b = Polynomial(listOf(4.0, 6.0))

    a -= b


    println(x)
    println(a)
}

// https://kotlinlang.org/docs/operator-overloading.html#arithmetic-operators
//https://kotlinlang.org/docs/operator-overloading.html#augmented-assignments
//https://www.codecademy.com/resources/docs/kotlin/math-methods/max
//https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.sequences/with-index.html
//https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/get-or-null.html
//Chat GPT
