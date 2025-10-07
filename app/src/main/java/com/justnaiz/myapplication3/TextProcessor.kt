package com.justnaiz.myapplication3

fun generateInputText(): String {
    return "Kotlin является современным языком программирования, который компилируется в байткод Java..."
}

fun splitWordsByLength(text: String, lengthThreshold: Int): Pair<List<String>, List<String>> {
    val words = text.split(" ", ".", ",", "!", "?", ":", ";", "(", ")", "[", "]", "{", "}")
        .filter { it.isNotBlank() }
        .map { it.trim() }

    val longerWords = words.filter { it.length > lengthThreshold }.sortedBy { it.length }
    val shorterWords = words.filter { it.length <= lengthThreshold }.sortedBy { it.length }

    return Pair(longerWords, shorterWords)
}

fun formatResult(longerWords: List<String>, shorterWords: List<String>): String {
    val result = StringBuilder()

    result.append("==СЛОВА ДЛИННЕЕ ЗАДАННОГО ЧИСЛА==\n")
    if (longerWords.isEmpty()) {
        result.append("Нет слов\n")
    } else {
        longerWords.forEachIndexed { index, word ->
            result.append("${index + 1}. '$word' (длина: ${word.length})\n")
        }
    }

    result.append("\n==СЛОВА КОРОЧЕ ИЛИ РАВНЫ ЗАДАННОМУ ЧИСЛУ==\n")
    if (shorterWords.isEmpty()) {
        result.append("Нет слов\n")
    } else {
        shorterWords.forEachIndexed { index, word ->
            result.append("${index + 1}. '$word' (длина: ${word.length})\n")
        }
    }

    return result.toString()
}
