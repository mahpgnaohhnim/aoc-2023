package day02

import day02.dto.CubeAmount
import day02.dto.GameDto
import day02.enums.CubeColor
import java.io.File
import java.security.InvalidParameterException

class Day02 {

    companion object {

        fun sumGamePower(text: String): Int {
            val games = text.lines().map { convertLineToGame(it) }
            val powers = games.map { getGamePower(it) }
            return powers.sum()
        }

        fun sumValidGameIds(text: String, maxAmount: List<CubeAmount>): Int {
            val games = text.lines().map { convertLineToGame(it) }
            val validGames = games.filter {
                !it.cubeAmounts.any { cubeAmount ->
                    maxAmount.any { condition -> condition.cubeColor == cubeAmount.cubeColor && cubeAmount.amount > condition.amount }
                }
            }
            return validGames.sumOf { it.id }
        }

        private fun convertLineToGame(line: String): GameDto {
            val mainParts = line.split(':')
            if (mainParts.size != 2) {
                throw InvalidParameterException("invalid line $line")
            }
            val header = mainParts.first()
            val body = mainParts.last()

            val gameId = header.filter { it.isDigit() }.toInt()

            return GameDto(
                id = gameId,
                cubeAmounts = convertSets(body)
            )
        }

        private fun convertSets(gameBody: String): List<CubeAmount> {
            val sets = gameBody.replace(';', ',').split(",")
            return sets.map { convertCubeAmount(it) }
        }


        private fun convertCubeAmount(text: String): CubeAmount {
            val parts = text.trim().split(' ')
            if (parts.size != 2) {
                throw InvalidParameterException("invalid line $text")
            }
            val amount = parts.first().toInt()
            val cubeColor = when (parts.last()) {
                CubeColor.GREEN.text -> CubeColor.GREEN
                CubeColor.RED.text -> CubeColor.RED
                CubeColor.BLUE.text -> CubeColor.BLUE
                else -> throw InvalidParameterException("invalid cubeColor ${parts.last()}")
            }
            return CubeAmount(amount = amount, cubeColor = cubeColor)
        }

        private fun getGamePower(game: GameDto): Int {
            val map = game.cubeAmounts.groupBy { it.cubeColor }
            val maxValues = map.values.map { it.maxBy { cubeAmount -> cubeAmount.amount } }
            return maxValues.map { it.amount }.reduce { acc, next -> acc * next }
        }
    }
}

fun main() {
    val inputFile = File(Day02::class.java.getResource("input.txt")?.path.orEmpty())

    fun part1() {
        val maxCubeAmounts = listOf(
            CubeAmount(CubeColor.RED, 12),
            CubeAmount(CubeColor.GREEN, 13),
            CubeAmount(CubeColor.BLUE, 14)
        )

        val result = Day02.sumValidGameIds(inputFile.readText(), maxCubeAmounts)
        println(
            """
            Result day 2 part1:
            $result
        """.trimIndent()
        )
    }

    fun part2() {
        val result = Day02.sumGamePower(inputFile.readText())
        println(
            """
            Result day 2 part2:
            $result
        """.trimIndent()
        )
    }

    part1()
    part2()
}