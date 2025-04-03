package com.example.jezyki

import org.junit.Test
import kotlin.test.assertEquals

class Zadanie3KtTest {
    @Test
    fun shouldReturnOnePermutationForEmptySet() {
        assertEquals(permutations(setOf<Int>()), setOf(setOf()))
    }

    @Test
    fun shouldReturnTwoPermutationForSetWithOneElement() {
        assertEquals(
            permutations(setOf(1)),
            setOf(setOf(1), setOf())
        )
    }

    @Test
    fun shouldReturnFourPermutationForSetWithTwoElement() {
        assertEquals(
            permutations(setOf(1, 2)),
            setOf(setOf(1, 2), setOf(1), setOf(2), setOf())
        )
    }

    @Test
    fun shouldReturnEightPermutationForSetWithTwoElement() {
        assertEquals(
            permutations(setOf(1, 2, 3)),
            setOf(setOf(1, 2, 3), setOf(1, 2), setOf(1,3), setOf(2,3), setOf(1), setOf(2), setOf(3), setOf())
        )
    }
}