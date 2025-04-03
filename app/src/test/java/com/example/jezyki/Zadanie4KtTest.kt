package com.example.jezyki

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertFailsWith

class Zadanie4KtTest {

    @Test
    fun testFibonacciLoopWithValidValues() {
        assertEquals(fibbonaci(5), listOf(0, 1, 1, 2, 3))
    }

    @Test
    fun testFibonacciLoopWithNegativeN() {
        assertFails { fibbonaci(-1) }
    }

    @Test
    fun testFibonacciRecursiveWithValidValues() {
        assertEquals(fibbonaciRecursion(5), listOf(0, 1, 1, 2, 3))
    }

    @Test
    fun testFibonacciRecursiveWithNegativeN() {
        assertFails { fibbonaciRecursion(-1) }
    }
}