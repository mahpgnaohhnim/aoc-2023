package day01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day01Test {
    private val file1 = File(this.javaClass.getResource("example1.txt")?.path.orEmpty())

    @Test
    fun challengePart1() {
        val result = Day01.sumCalibrate(file1.readText())
        assertEquals(142, result)
    }

}