package day01

import java.io.File

class Day01 {
    companion object {
        fun sumCalibrate(file: String): Int {
            return file.lines().sumOf { calibrateLine(it) }
        }

        fun sumCalibrateWithNumWords(file: String): Int {
            return file.lines()
                .map { replaceWordNumWithInt(it) }
                .sumOf { calibrateLine(it) }
        }

        private fun calibrateLine(line: String): Int {
            val filteredLine = line.filter { it.isDigit() }
            val firstNumber = filteredLine.first()
            val lastNumber = filteredLine.last()
            return "$firstNumber$lastNumber".toInt()
        }

        private fun replaceWordNumWithInt(line: String): String {
            return line.replace("one", "one1one")
                .replace("two", "two2two")
                .replace("three", "three3three")
                .replace("four", "four4four")
                .replace("five", "five5five")
                .replace("six", "six6six")
                .replace("seven", "seven7seven")
                .replace("eight", "eight8eight")
                .replace("nine", "nine9nine")
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