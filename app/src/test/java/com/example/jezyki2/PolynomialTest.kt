package com.example.jezyki2

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class PolynomialTest {

    @Test
    fun ValidConstructor() {
        val p = Polynomial(listOf(1.0, 0.0, -2.0))
        assertEquals(2, p.degree())
    }

    @Test
    fun ConstructorWithEmptyList() {
        assertFails { Polynomial(emptyList()) }
    }

    @Test
    fun ConstructorWithZero() {
        assertFails { Polynomial(listOf(0.0, 1.0)) }
    }

    @Test
    fun ValidDegree() {
        val p = Polynomial(listOf(3.0))
        assertEquals(0, p.degree())

        val q = Polynomial(listOf(2.0, 0.0, 0.0, 5.0))
        assertEquals(3, q.degree())

    }

    @Test
    fun ValidInvoke() {
        val p = Polynomial(listOf(2.0, 3.0, 1.0)) // 2x^2 + 3x + 1
        assertEquals(2 * 2.0 * 2.0 + 3 * 2.0 + 1.0, p(2.0), 1e-6)

    }

    @Test
    fun ValidPlus() {
        val p1 = Polynomial(listOf(1.0, 2.0)) // x + 2
        val p2 = Polynomial(listOf(3.0, 4.0, 5.0)) // 3x^2 + 4x + 5
        val result = p1 + p2 // 3x^2 + 5x + 7
        val expected = Polynomial(listOf(3.0, 5.0, 7.0))
        assertEquals(expected.toString(), result.toString())
    }

    @Test
    fun ValidPlusAssign() {
        val p1 = Polynomial(listOf(1.0, 2.0)) // x + 2
        val p2 = Polynomial(listOf(3.0, 4.0, 5.0)) // 3x^2 + 4x + 5
        p1 += p2
        val expected = Polynomial(listOf(3.0, 5.0, 7.0))
        assertEquals(expected.toString(), p1.toString())
    }

    @Test
    fun ValidMinus() {// tu cos nie działa
        val p1 = Polynomial(listOf(1.0, 2.0)) // x + 2
        val p2 = Polynomial(listOf(3.0, 4.0, 5.0)) // 3x^2 + 4x + 5
        val result = p1 - p2 //3x^2 - 3x - 3
        val expected = Polynomial(listOf(-3.0, -3.0, -3.0))
        assertEquals(expected.toString(), result.toString())
    }

    @Test
    fun ValidMinusAssign() {//tu cos nie działa
        val p1 = Polynomial(listOf(4.0, 5.0))
        val p2 = Polynomial(listOf(1.0, 2.0))
        p1 -= p2
        val expected = Polynomial(listOf(3.0, 3.0))
        assertEquals(expected.toString(), p1.toString())
    }

    @Test
    fun ValidTimes() {
        val p1 = Polynomial(listOf(2.0, 3.0)) // 2x + 3
        val p2 = Polynomial(listOf(4.0, 5.0)) // 4x + 5
        val result = p1 * p2
        val expected = Polynomial(listOf(8.0, 15.0))
        assertEquals(expected.toString(), result.toString())
    }

    @Test// tu coś nie działa
    fun ValidTimesAssign() {
        val p1 = Polynomial(listOf(1.0, 2.0)) // x + 2
        val p2 = Polynomial(listOf(2.0, 3.0)) // 2x + 3
        p1 *= p2
        val expected = Polynomial(listOf(2.0, 6.0))
        assertEquals(expected.toString(), p1.toString())
    }

    @Test
    fun ValidString() {
        val p1 = Polynomial(listOf(-3.0, 0.0, 2.0, -1.0)) // -3x^3 + 2x - 1
        val expected = "W(x) = -3.0x^3 + 2.0x - 1.0"
        assertEquals(expected, p1.toString())

        val constant = Polynomial(listOf(5.0))
        assertEquals("W(x) = 5.0", constant.toString())
    }

}