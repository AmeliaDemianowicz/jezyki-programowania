package com.example.jezyki2
/**
 * Abstrakcyjna klasa reprezentująca sekwencję zasad tworzących ciąg, przechowująca informacje o niej np.
 *jej długość oraz identyfikator zbudowany z prefiksu i numeru. Sprawdza tez poprawnośc zasad
 *
 * @param identifierPrefix prefiks identyfikatora (np. "dna", "rna", "protein").
 * @param identifierNumber liczbowy składnik identyfikatora.
 * @param data zawartość sekwencji; nie może być pusta ani zawierać nieprawidłowych znaków.
 */
abstract class Sequence(
    identifierPrefix: String,
    protected val identifierNumber: Int,
    protected var data: String
) {
    val identifier = "$identifierPrefix$identifierNumber"
    val length = data.length

    init {
        require(data.isNotEmpty()) { "Cannot create empty sequence" }
        require(data.all { isValidSequenceCharacter(it) }) { "Cannot create sequence with invalid characters" }
    }

    /**
     * Funkcja mutate, mutuje sekwencję, podmieniając znak na podanej pozycji.
     * @param position pozycja znaku do zmiany, numer indeksu zaczynajac od 0
     * @param value nowy znak, który ma zostać wstawiony (musi być poprawny dla danej sekwencji)
     */
    fun mutate(position: Int, value: Char) {
        require(position in 0..<length) { "Cannot mutate at non-existent position" }
        require(isValidSequenceCharacter(value)) { "Cannot mutate with invalid character" }
        data = data.replaceRange(position, position + 1, "$value")
    }

    /**
     * Funkcja szukająca zadanego motywu, czyli ciągu zasad w sekwencji DNA
     * @param motif zadany motyw, string zawierający zasady np. "ATTGCCGCTA"
     */
    fun findMotif(motif: String): Int {
        require(motif.all { isValidSequenceCharacter(it) }) { "Cannot find motif with invalid characters" }
        require(data.contains(motif)) { "Cannot find motif" }
        return data.indexOf(motif)
    }

    /**
     * Funkcja sprawdzająca, czy dany znak jest poprawnym nukleotydem DNA
     * @param value znak do sprawdzenia
     * @return true, jeśli znak to A, C, G lub T
     */
    abstract fun isValidSequenceCharacter(value: Char): Boolean

    override fun toString(): String {
        return ">$identifier\n$data"
    }
}

/**
 * Klasa reprezentująca sekwencję DNA o prefiksie dna i numerze, dziedzicząca po klasie [Sequence], z dopuszczalnymi zasadami , zasadami komplementarnymi i zasadami transkrybcji
 * @param identifier liczbowy składnik identyfikatora sekwencji.
 * @param data zawartość sekwencji DNA, dozwolone znaki: A, C, G, T.
 */
class DNASequence(identifier: Int, data: String) : Sequence("dna", identifier, data) {
    companion object {
        private val VALID_CHARS = setOf('A', 'C', 'G', 'T')
        private val COMPLEMENTS = mapOf('A' to 'T', 'T' to 'A', 'C' to 'G', 'G' to 'C')
        private val TRANSCRIPTS = mapOf('A' to 'U', 'T' to 'A', 'C' to 'G', 'G' to 'C')
    }

    /**
     * Funkcja sprawdzająca, czy dany znak jest poprawnym nukleotydem DNA
     * @param value znak do sprawdzenia
     * @return true, jeśli znak to A, C, G lub T
     */
    override fun isValidSequenceCharacter(value: Char): Boolean {
        return VALID_CHARS.contains(value)
    }

    /**
     * Funkcja tworząca nową, komplementarną sekwencję DNA
     * @return DNASequence nowa sekwencja DNA z zasadami komplementarnymi
     */
    fun complement(): DNASequence {
        return DNASequence(identifierNumber, data.map { COMPLEMENTS.getValue(it) }.joinToString(""))
    }

    /**
     * Funkcja transkrybująca sekwencję DNA (A,T,G,C) do RNA(A,U,G,C)
     * @return RNASequence nowa sekwencja RNA na podstawie komplemantarnego DNA
     */
    fun transcribe(): RNASequence {
        return RNASequence(identifierNumber, data.map { TRANSCRIPTS.getValue(it) }.joinToString(""))
    }
}

/**
 * Klasa reprezentująca sekwencję RNA o prefiksie "rna" i numerze, dziedzicząca po klasie [Sequence], zawiera
 *zestaw dozwolonych nukleotydów RNA, sekwencje startowe i stopowe translacji. Posiada mapę kodonów i odpowiednich aminokwasów
 * @param identifier liczbowy składnik identyfikatora sekwencji
 * @param data zawartość sekwencji RNA, musi zawierać tylko znaki: A, C, G, U
 *
 */
class RNASequence(identifier: Int, data: String) : Sequence("rna", identifier, data) {
    companion object {
        private val VALID_CHARS = setOf('A', 'C', 'G', 'U')
        private val TRANSCRIPT_STARTERS = setOf("AUG")
        private val TRANSCRIPT_STOPPERS = setOf("UAA", "UAG", "UGA")
        private val TRANSCRIPTS = mapOf(
            "UUU" to 'F', "UUC" to 'F', "UUA" to 'L', "UUG" to 'L',
            "CUU" to 'L', "CUC" to 'L', "CUA" to 'L', "CUG" to 'L',
            "AUU" to 'I', "AUC" to 'I', "AUA" to 'I', "AUG" to 'M',
            "GUU" to 'V', "GUC" to 'V', "GUA" to 'V', "GUG" to 'V',
            "UCU" to 'S', "UCC" to 'S', "UCA" to 'S', "UCG" to 'S',
            "CCU" to 'P', "CCC" to 'P', "CCA" to 'P', "CCG" to 'P',
            "ACU" to 'T', "ACC" to 'T', "ACA" to 'T', "ACG" to 'T',
            "GCU" to 'A', "GCC" to 'A', "GCA" to 'A', "GCG" to 'A',
            "UAU" to 'Y', "UAC" to 'Y', "UAA" to "-", "UAG" to "-",
            "CAU" to 'H', "CAC" to 'H', "CAA" to 'Q', "CAG" to 'Q',
            "AAU" to 'N', "AAC" to 'N', "AAA" to 'K', "AAG" to 'K',
            "GAU" to 'D', "GAC" to 'D', "GAA" to 'E', "GAG" to 'E',
            "UGU" to 'C', "UGC" to 'C', "UGA" to "-", "UGG" to 'W',
            "CGU" to 'R', "CGC" to 'R', "CGA" to 'R', "CGG" to 'R',
            "AGU" to 'S', "AGC" to 'S', "AGA" to 'R', "AGG" to 'R',
            "GGU" to 'G', "GGC" to 'G', "GGA" to 'G', "GGG" to 'G'
        )
    }

    /**
     * Funkcja sprawdzająca, czy dany znak jest poprawnym nukleotydem RNA
     * @param value znak do sprawdzenia
     * @return true, jeśli znak to A, C, G lub U
     */
    override fun isValidSequenceCharacter(value: Char): Boolean {
        return VALID_CHARS.contains(value)
    }

    /**
     * Funkcja transkrybująca RNA na sekwencję białkową, znajdująca kodon start i stop, odczytując kodony między nimi i przypisujac im kody literowe aminokwasów
     * @return nowa sekwencja białkowa
     */
    fun transcribe(): ProteinSequence {
        val starter = TRANSCRIPT_STARTERS.find { data.contains(it) }
        val stopper = TRANSCRIPT_STOPPERS.find { data.substringAfter(starter ?: "").contains(it) }

        requireNotNull(starter) { "Cannot transcribe with non-existent starting codon" }
        requireNotNull(stopper) { "Cannot transcribe with non-existent stopping codon" }

        val proteinData = starter + data.substringAfter(starter).substringBefore(stopper)

        require(proteinData.length % 3 == 0) { "Cannot transcribe with invalid length" }

        return ProteinSequence(
            identifierNumber,
            proteinData.chunked(3).map { TRANSCRIPTS.getValue(it) }.joinToString("")
        )
    }
}

/**
 * Klasa reprezentująca sekwencję białkową prefiks "protein", dziedzicząca po klasie [Sequence]
 *Posiada litery będące symbolami danych aminokwasów (https://pl.wikipedia.org/wiki/Aminokwasy_bia%C5%82kowe)
 * @param identifier liczbowy składnik identyfikatora sekwencji
 * @param data zawartość sekwencji białkowej (np. "MKTLLIL") musi zawierać tylko znaki odpowiadające aminokwasom
 *
 */
class ProteinSequence(identifier: Int, data: String) : Sequence("protein", identifier, data) {
    companion object {
        private val VALID_CHARS =
            setOf(
                'A', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L',
                'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'Y',
                '-'
            )
    }

    /**
     * Funkcja sprawdzająca, czy dany znak jest poprawną litera okreslająca aminokwas
     * @param value znak do sprawdzenia
     * @return true, jeśli znak to A, C, G lub U
     */
    override fun isValidSequenceCharacter(value: Char): Boolean {
        return VALID_CHARS.contains(value)
    }
}

fun main() {
    val dna = DNASequence(1, "CGCGATGGATTCCTAG")
    val complement = dna.complement()
    val rna = complement.transcribe()
    val protein = rna.transcribe()

    println(dna)
    println(complement)
    println(rna)
    println(protein)
}
//https://pl.wikipedia.org/wiki/Aminokwasy_bia%C5%82kowe
//https://www.baeldung.com/kotlin/maps
//https://www.programiz.com/kotlin-programming/abstract-class
// Chat GPT
//https://kotlinlang.org/docs/functions.html