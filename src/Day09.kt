import kotlin.math.absoluteValue
import kotlin.math.sign

data class Point(val x: Int = 0, val y: Int = 0) {
    fun move(direction: Char): Point =
        when (direction) {
            'U' -> copy(y = y - 1)
            'D' -> copy(y = y + 1)
            'L' -> copy(x = x - 1)
            'R' -> copy(x = x + 1)
            else -> throw IllegalArgumentException()
        }

    fun moveTowards(other: Point): Point =
        Point(
            (other.x - x).sign + x,
            (other.y - y).sign + y
        )

    fun touches(other: Point): Boolean =
        (x - other.x).absoluteValue <= 1 && (y - other.y).absoluteValue <= 1
}

fun main() {
    fun followPath(path: String, knots: Int): Int {
        val rope = Array(knots) { Point() }
        val tailVisits = mutableSetOf(Point())

        path.forEach { direction ->
            rope[0] = rope[0].move(direction)
            rope.indices.windowed(2, 1) { (head, tail) ->
                if (!rope[head].touches(rope[tail])) {
                    rope[tail] = rope[tail].moveTowards(rope[head])
                }
            }
            tailVisits += rope.last()
        }
        return tailVisits.size
    }

    fun getPathFromInput(input: List<String>): String {
        return input.joinToString("") { row ->
            val direction = row.substringBefore(" ")
            val numberOfMoves = row.substringAfter(" ").toInt()
            direction.repeat(numberOfMoves)
        }
    }

    fun part1(input: List<String>): Int {
        return followPath(getPathFromInput(input), 2)
    }

    fun part2(input: List<String>): Int {
        return followPath(getPathFromInput(input), 10)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 13)
    val testInput2 = readInput("Day09_test2")
    check(part2(testInput2) == 36)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
