package day01

import java.io.File

class Day01 {
    companion object {
        fun sumCalibrate(file: String): Int {
            return file.lines().sumOf { calibrateLine(it) }
        }

        private fun calibrateLine(line: String): Int {
            val filteredLine = line.filter { it.isDigit() }
            val firstNumber = filteredLine.first()
            val lastNumber = filteredLine.last()
            return "$firstNumber$lastNumber".toInt()
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

    part1()
}