package day03

import java.io.File

class Day03 {
    companion object {

        fun sumAdjacentNums(text: String): Int {
            val validNums = findValidNums(text)
            return validNums.sum()
        }

        fun findNumsFromLine(text: String): List<Int> {
            val nonNumericRegex = Regex("\\D+")
            val numbers = text.split(nonNumericRegex).filter { it.isNotEmpty() }
            return numbers.map { it.toInt() }
        }

        fun findValidNums(text: String): List<Int> {
            val validNumbers = mutableListOf<Int>()
            val invalidNumbers = mutableListOf<Int>()
            val lines = text.lines()
            for (i in 0..<lines.size) {
                val currentLine = lines[i]
                findNumsFromLine(currentLine).forEach {
                    val regex = Regex(it.toString())
                    val matcher = regex.find(currentLine)

                    matcher?.range?.let { range ->
                        val extendedRange = extendRange(range, currentLine.length)
                        if (checkAdjacent(lines, i, extendedRange)) {
                            validNumbers.add(it)
                        } else {
                            invalidNumbers.add(it)
                        }
                    }
                }
            }
            return validNumbers
        }

        private fun checkAdjacent(lines: List<String>, currentIndex: Int, range: IntRange): Boolean {
            val previousNeighbour = when (currentIndex) {
                0 -> ""
                else -> lines[currentIndex - 1].substring(range)
            }

            val nextNeighbour = when (currentIndex) {
                (lines.size - 1) -> ""
                else -> lines[currentIndex + 1].substring(range)
            }

            val currentTarget = lines[currentIndex].substring(range)

            val targetBlock = """
                $previousNeighbour
                $currentTarget
                $nextNeighbour
            """.trimIndent()
            val filteredBlock = filterAdjacent(targetBlock)
            return filteredBlock.isNotBlank()
        }

        private fun extendRange(matcherRange: IntRange, lineSize: Int): IntRange {
            var start = matcherRange.start
            if (start > 0) {
                start -= 1
            }

            var end = matcherRange.last
            if (end < lineSize - 1) {
                end += 1
            }
            return start..end
        }

        private fun filterAdjacent(targetText: String): String {
            val numericRegex = Regex("\\d+")
            return targetText.replace("\n", "")
                .replace(numericRegex, "")
                .filter { it != '.' }
        }
    }
}

fun main() {
    val inputFile = File(Day03::class.java.getResource("input.txt")?.path.orEmpty())

    fun part1() {
        val result = Day03.sumAdjacentNums(inputFile.readText())
        println(
            """
            Result day 3 part1:
            $result
        """.trimIndent()
        )
    }

    part1()
}