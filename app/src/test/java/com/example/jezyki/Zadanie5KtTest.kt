package com.example.jezyki

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class Zadanie5KtTest {
    @Test
    fun CollatzInvalidC0 () {
        assertFails { collatz(-8) }
    }
    @Test
    fun CollatzInvalidC0 () {
        assertFails { collatz(0) }
    }

    @Test
    fun CollatzValid () {
        assertEquals(collatzTest(1..5), Triple(3,8,16))
    }
    @Test
    fun CollatzWrongRange (){
        assertFails { collatzTest(0..1) }
    }
    @Test
    fun CollatzValidSameNumberInRange () {
        assertEquals(collatzTest(1..1), Triple(1,1,1))
    }


}