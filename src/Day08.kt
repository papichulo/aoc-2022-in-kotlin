fun main() {
    fun isVisible(x: Int, y: Int, matrix: List<String>): Boolean {
        if (x == 0 || x == matrix[0].lastIndex || y == 0 || y == matrix.lastIndex) return true
        val tree = matrix[x][y]
        val yArray = matrix.map { it[x] }
        return (0 until x).none { matrix[y][it] >= tree } ||
                (x + 1  until matrix[0].length).none { matrix[y][it] >= tree } ||
                (0 until y).none { yArray[it] >= tree } ||
                (y + 1 until matrix.size).none { yArray[it] >= tree }
    }

    fun part1(input: List<String>): Int {
        return input.mapIndexed { y, row ->
            row.filterIndexed { x, _ ->
                isVisible(x, y, input)
            }
        }.sumOf { it.length }
    }

    fun part2(input: List<String>): Int {
        return 2
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    //check(part2(testInput) == 45000)

    val input = readInput("Day08")
    println(part1(input))
    //println(part2(input))
}
