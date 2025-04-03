package com.example.jezyki

import org.junit.Test
import kotlin.test.assertEquals

class Zadanie2KtTest {

    @Test
    fun testListIntersectionWithCommonParts() {
        assertEquals(intersect(listOf(1, 1, 2, 3, 8, 7), listOf(2, 1, 5, 5, 8, 9)), setOf(1, 2, 8))
    }

    @Test
    fun testListIntersectionWithEmptyList() {
        assertEquals(intersect(listOf(), listOf(2, 1, 5, 5, 8, 9)), setOf())
    }

    @Test
    fun testListIntersectionWithoutCommonParts() {
        assertEquals(intersect(listOf(3, 7, 4, 4, 3), listOf(2, 1, 5, 5, 8, 9)), setOf())
    }
}