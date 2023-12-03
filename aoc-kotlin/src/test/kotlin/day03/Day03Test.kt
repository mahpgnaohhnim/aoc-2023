package day03

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class Day03Test {
    private val example = File(this.javaClass.getResource("example.txt")?.path.orEmpty())

    @Test
    fun sumAdjacentNums() {
        val result = Day03.sumAdjacentNums(example.readText())
        Assertions.assertEquals(4361, result)
    }
}