package day01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day01Test {
    private val file1 = File(this.javaClass.getResource("example1.txt")?.path.orEmpty())
    private val file2 = File(this.javaClass.getResource("example2.txt")?.path.orEmpty())

    @Test
    fun challengePart1() {
        val result = Day01.sumCalibrate(file1.readText())
        assertEquals(142, result)
    }

    @Test
    fun challengePart2() {
        val result = Day01.sumCalibrateWithNumWords(file2.readText())
        assertEquals(281, result)
    }

}