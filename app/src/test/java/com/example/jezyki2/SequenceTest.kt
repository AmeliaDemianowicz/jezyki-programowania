package com.example.jezyki2

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails


class SequenceTest {
    @Test
    fun EmptySequenceDNA() {
        assertFails { DNASequence(9, "") }
    }

    @Test
    fun InvalidSequenceCharDNA() {
        assertFails { DNASequence(9, "DFG") }
    }

    @Test
    fun ValidDNA() {
        val dna = DNASequence(1, "ATCG")
        assertEquals(dna.identifier, "dna1")
        assertEquals(dna.length, 4)
    }

    @Test
    fun ValidComplementDNA() {
        val dna = DNASequence(1, "ATCG")
        val complement = dna.complement()
        assertEquals(">dna1\nTAGC", complement.toString())
    }

    @Test
    fun ValidTranscriptionDNA() {
        val dna = DNASequence(2, "ATCG")
        val rna = dna.transcribe()
        assertEquals(">rna2\nUAGC", rna.toString())
    }

    @Test
    fun ValidMutationDNA() {
        val dna = DNASequence(4, "ACGT")
        dna.mutate(1, 'G')
        assertEquals(">dna4\nAGGT", dna.toString())
    }

    @Test
    fun InvalidMutationDNANegativePosition() {
        val dna = DNASequence(6, "ACGT")
        assertFails { dna.mutate(-1, 'A') }
    }

    @Test
    fun InvalidMutationDNAPosition() {
        val dna = DNASequence(6, "ACGT")
        assertFails { dna.mutate(10, 'A') }
    }

    @Test
    fun InvalidMutationDNAChar() {
        val dna = DNASequence(7, "ACGT")
        assertFails { dna.mutate(2, 'X') }
    }

    @Test
    fun ValidFindMotif() {
        val dna = DNASequence(5, "ACGTACGT")
        val index = dna.findMotif("CGT")
        assertEquals(1, index)
    }

    @Test
    fun InvalidMotifCharacters() {
        val dna = DNASequence(8, "ACGT")
        assertFails { dna.findMotif("XYZ") }
    }

    @Test
    fun InvalidMotif() {
        val dna = DNASequence(8, "ACGT")
        assertFails { dna.findMotif("AA") }
    }

    @Test
    fun EmptySequenceRNA() {
        assertFails { RNASequence(10, "") }
    }

    @Test
    fun InvalidSequenceCharRNA() {
        assertFails { RNASequence(10, "ABC") }
    }

    @Test
    fun ValidTranscriptionRNAToProtein() {
        val rna = RNASequence(3, "GGGAUGGCUUAA") // kodon start: AUG, stop: UAA
        val protein = rna.transcribe()
        assertEquals(">protein3\nMA", protein.toString()) // AUG->M, GCU->A
    }

    @Test
    fun InvalidTranscriptionRNAToProtein() {
        val rna = RNASequence(3, "GGGGGGGGGG")
        assertFails { rna.transcribe() }
    }

    @Test
    fun InvalidTranscriptionRNAToProtein2() {
        val rna = RNASequence(3, "GCCAGGUAG")
        assertFails { rna.transcribe() }//brak kodonu startowego tylko stop
    }

    @Test
    fun InvalidNumbersOfChars() {
        val rna = RNASequence(3, "AUGCGUAG")
        assertFails { rna.transcribe() }//pomiędzy startem i stopem ilośc zasad nie będąca wielokrotnościa 3
    }

    @Test
    fun EmptySequenceProtein() {
        assertFails { ProteinSequence(10, "") }
    }

    @Test
    fun InvalidSequenceCharProtein() {
        assertFails { ProteinSequence(10, "XQZ") }
    }

}