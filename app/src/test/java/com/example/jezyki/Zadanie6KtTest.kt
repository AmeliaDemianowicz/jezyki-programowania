package com.example.jezyki

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails


class Zadanie6KtTest {
    @Test
    fun ValidComplement() {
        assertEquals(complement("ATGCGGAGT"), "TACGCCTCA")
    }

    @Test
    fun expectInvalidDnaValue() {
        assertFails { complement("AT") }
    }

    @Test
    fun expectInvalidNucleotydValue() {
        assertFails { complement("ATGCCATY") }
    }

    @Test
    fun expectInvalidMatrixValue() {
        assertFails { transcribe("ATGCC") }
    }

    @Test
    fun expectInvalidMatrixNucleotydValue() {
        assertFails { transcribe("ATGCCB") }
    }

    @Test
    fun ValidMatrixDna() {
        assertEquals(transcribe("ATGCGGAGT"), "UACGCCUCA")
    }

    @Test
    fun ValidTranslationFromRnaToProtein() {
        assertEquals(translate("AUGCGA"), "MetArg")
    }

    @Test
    fun ValidTranslationFromRnaToProteinStartCodonInside() {
        assertEquals(translate("CGAAUGCGA"), "MetArg")
    }

    @Test
    fun ValidTranslationFromRnaToProteinStartCodonAtTheEnd() {
        assertEquals(translate("CGACGAAUG"), "Met")
    }

    @Test
    fun ValidTranslationFromRnaToProteinStartAndStopCodon() {
        assertEquals(translate("ATGCGAGGCTAGACG"), "MetArgGly")
    }

    @Test
    fun ValidTranslationFromRnaToProteinNoStartCodon() {
        assertEquals(translate("GCGGCG"), "")
    }

    @Test
    fun expectInvalidRNAValue() {
        assertFails { transcribe("ATGCCB") }
    }

    @Test
    fun expectInvalidNucleotydNumbersValue() {
        assertFails { transcribe("ATGCC") }
    }


}