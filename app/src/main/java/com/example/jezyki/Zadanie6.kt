package com.example.jezyki

val COMPLEMENTS = mapOf('A' to 'T', 'T' to 'A', 'C' to 'G', 'G' to 'C')
val TRANSCRIPTIONS = mapOf('A' to 'U', 'T' to 'A', 'C' to 'G', 'G' to 'C')
val CODONS = mapOf(
    "UUU" to "Phe", "UUC" to "Phe", "UUA" to "Leu", "UUG" to "Leu",
    "CUU" to "Leu", "CUC" to "Leu", "CUA" to "Leu", "CUG" to "Leu",
    "AUU" to "Ile", "AUC" to "Ile", "AUA" to "Ile", "AUG" to "Met",
    "GUU" to "Val", "GUC" to "Val", "GUA" to "Val", "GUG" to "Val",
    "UCU" to "Ser", "UCC" to "Ser", "UCA" to "Ser", "UCG" to "Ser",
    "CCU" to "Pro", "CCC" to "Pro", "CCA" to "Pro", "CCG" to "Pro",
    "ACU" to "Thr", "ACC" to "Thr", "ACA" to "Thr", "ACG" to "Thr",
    "GCU" to "Ala", "GCC" to "Ala", "GCA" to "Ala", "GCG" to "Ala",
    "UAU" to "Tyr", "UAC" to "Tyr", "UAA" to "Stop", "UAG" to "Stop",
    "CAU" to "His", "CAC" to "His", "CAA" to "Gln", "CAG" to "Gln",
    "AAU" to "Asn", "AAC" to "Asn", "AAA" to "Lys", "AAG" to "Lys",
    "GAU" to "Asp", "GAC" to "Asp", "GAA" to "Glu", "GAG" to "Glu",
    "UGU" to "Cys", "UGC" to "Cys", "UGA" to "Stop", "UGG" to "Trp",
    "CGU" to "Arg", "CGC" to "Arg", "CGA" to "Arg", "CGG" to "Arg",
    "AGU" to "Ser", "AGC" to "Ser", "AGA" to "Arg", "AGG" to "Arg",
    "GGU" to "Gly", "GGC" to "Gly", "GGA" to "Gly", "GGG" to "Gly"
)
/**
 * Funkcja dla nici DNA o liczbie nukleotydów będącej wielokrotnościa trójki, dobiera nukleotydy komplementarne i łączy je w string, będący nową nicią
 * @param dna string zawierający  litery sumbolizujące nukleotydy, nasza początkowa nić DNA
 * @return string DNA komplementarnego do pierwotnej nici, zgodnie z mapa komplementarności
 */
fun complement(dna: String): String {
    require(dna.length % 3 == 0) { "..." }

    return dna
        .map { COMPLEMENTS[it] ?: throw IllegalArgumentException("Niepoprawny nukleotyd: $it") }
        .joinToString("")
}
/**
 * Funkcja dokonuje transkrybcji, czyli tworzy mRNA z matrycy DNA utworzonej wczesniej, komplementarnie dobudowuje nukleotydy z tym, że zamiast tyminy wystepuje uracyl
 * @param matrixDna string zawierający nukleotydy(litery A,T,C,G), jest to nasza nić matrycowa DNA
 * @return matrixDna w postaci stringa lecz ze zmienionymi komplementarnie zasadami azotowymi A->U,T->A,C->G,G->C
 */
fun transcribe(matrixDna: String): String {
    require(matrixDna.length % 3 == 0) { "..." }

    return matrixDna
        .map { TRANSCRIPTIONS[it] ?: throw IllegalArgumentException("Niepoprawny nukleotyd: $it") }
        .joinToString("")
}
/**
 * Funkcja dokonująca translacji, czyli przypisania konkretnych aminokwasów na podstawie kodonów (trzech kolejnych liter), na podstawie mapy CODONS
 * @param rna string zawierający komplementarną do DNA sekwencję, na którego podstawie dobierane sa aminokwasy w białku
 * @return string który zawiera odpowiadające kodonom skrótowe nazwy aminokwasów, wypisane w kolejności
 */
fun translate(rna: String): String {
    require(rna.length % 3 == 0) { "..." }
    return rna
        .chunked(3)
        .map { CODONS[it] ?: throw IllegalArgumentException("Nieznany kodon: $it") }
        .dropWhile { it != "Met" }
        .takeWhile { it != "Stop" }
        .joinToString("")
}

fun main() {
    val dna = "GCGGCG"
    val matrycowe = complement(dna)
    val rna = transcribe(matrycowe)
    val bialko = translate(rna)

    println("Nić kodująca DNA: $dna")
    println("Nić matrycowa DNA: $matrycowe")
    println("Sekwencja RNA: $rna")
    println("Sekwencja białka: $bialko")
}
//https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/chunked.html
//https://discuss.kotlinlang.org/t/python-like-dictionaries-in-kotlin/2008
//https://www.tutorialspoint.com/kotlin/kotlin_maps.htm
//https://www.tutorialspoint.com/kotlin/kotlin_array_dropwhile_function.htm
//https://www.tutorialspoint.com/kotlin/kotlin_array_takewhile_function.htm