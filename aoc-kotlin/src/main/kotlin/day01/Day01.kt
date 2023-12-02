package day01

import java.io.File

class Day01 {
    companion object {
        fun sumCalibrate(file: String): Int {
            return file.lines().sumOf { calibrateLine(it) }
        }

        fun sumCalibrateWithNumWords(file: String): Int {
            return file.lines()
                .map { replaceFirstAndLastWordNumWithInt(it) }
                .sumOf { calibrateLine(it) }
        }

        private fun calibrateLine(line: String): Int {
            val filteredLine = line.filter { it.isDigit() }
            val firstNumber = filteredLine.first()
            val lastNumber = filteredLine.last()
            return "$firstNumber$lastNumber".toInt()
        }

        private fun replaceFirstAndLastWordNumWithInt(line: String): String {
            val wordNumRegex = Regex("one|two|three|four|five|six|seven|eight|nine")
            val matches = wordNumRegex.findAll(line)
            val first = matches.firstOrNull()?.groupValues?.first()
            val firstRange = matches.firstOrNull()?.range
            if (firstRange != null && first != null) {
                return replaceFirstAndLastWordNumWithInt(line.replaceRange(firstRange, mapWordToInt(first)))
            }
            return line
        }

        private fun mapWordToInt(word: String): String {
            return when (word) {
                "one" -> "1"
                "two" -> "2"
                "three" -> "3"
                "four" -> "4"
                "five" -> "5"
                "six" -> "6"
                "seven" -> "7"
                "eight" -> "8"
                "nine" -> "9"
                else -> ""
            }
        }
    }
}

fun main() {
    val inputFile = File(Day01::class.java.getResource("input.txt")?.path.orEmpty())

    fun part1() {
        val result = Day01.sumCalibrate(inputFile.readText())
        println(
            """
            Result day 1 part1:
            $result
        """.trimIndent()
        )
    }

    fun part2() {
        val result = Day01.sumCalibrateWithNumWords(inputFile.readText())
        println(
            """
            Result day 1 part2:
            $result
        """.trimIndent()
        )
    }

    part1()
    part2()
}