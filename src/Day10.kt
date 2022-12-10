import kotlin.math.absoluteValue

fun main() {
    val taps = intArrayOf(20, 60, 100, 140, 180, 220)

    fun convertToIntList(input: List<String>): List<Int> {
        return buildList {
            add(1) // starts with 1
            input.forEach { line ->
                add(0)
                if (line.startsWith("addx")) {
                    add(line.substringAfter(" ").toInt())
                }
            }
        }
    }

    fun part1(input: List<String>): Int {
        val valueList = convertToIntList(input).runningReduce(Int::plus)
        return taps.sumOf { valueList[it - 1] * it }
    }

    fun part2(input: List<String>) {
        convertToIntList(input).runningReduce(Int::plus)
            .mapIndexed { index, i ->
            (i-(index%40)).absoluteValue <= 1
            }
            .windowed(40, 40, false)
            .forEach { row ->
                row.forEach { pixel ->
                    print(if(pixel) '#' else '.')
                }
            println()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 13140)
    part2(testInput)

    val input = readInput("Day10")
    println(part1(input))
    part2(input)
}
