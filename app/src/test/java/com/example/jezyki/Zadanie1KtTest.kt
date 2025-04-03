package com.example.jezyki

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class Zadanie1KtTest {

    @Test
    fun expectHeronToThrowErrorWithNegativeAValue() {
        assertFails { heron(-1.0, 1.0, 1.0) }
    }

    @Test
    fun expectHeronToThrowErrorWithNegativeBValue() {
        assertFails { heron(1.0, -1.0, 1.0) }
    }

    @Test
    fun expectHeronToThrowErrorWithNegativeCValue() {
        assertFails { heron(1.0, 1.0, -1.0) }
    }

    @Test
    fun expectHeronToThrowWithTooLongAValue() {
        assertFails { heron(10.0, 1.0, 1.0) }
    }

    @Test
    fun expectHeronToThrowWithTooLongBValue() {
        assertFails { heron(1.0, 10.0, 1.0) }
    }

    @Test
    fun expectHeronToThrowWithTooLongCValue() {
        assertFails { heron(1.0, 1.0, 10.0) }
    }

    @Test
    fun expectHeronToReturnTriangleArea() {
        assertEquals(heron(3.0, 4.0, 5.0), 6.0)
    }
}