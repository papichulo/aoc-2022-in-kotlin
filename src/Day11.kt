fun main() {
    fun part1(input: List<String>): Int {
        // sum of directories lte 100000
        return 1
    }

    fun part2(input: List<String>): Int {
        return 2
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 45000)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
