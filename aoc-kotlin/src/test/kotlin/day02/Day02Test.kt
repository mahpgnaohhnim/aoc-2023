package day02

import day02.dto.CubeAmount
import day02.enums.CubeColor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day02Test {
    private val example = File(this.javaClass.getResource("example.txt")?.path.orEmpty())

    @Test
    fun challengePart1() {
        val maxCubeAmounts = listOf(
            CubeAmount(CubeColor.RED, 12),
            CubeAmount(CubeColor.GREEN, 13),
            CubeAmount(CubeColor.BLUE, 14)
        )
        val result = Day02.sumValidGameIds(example.readText(), maxCubeAmounts)
        assertEquals(8, result)
    }
}