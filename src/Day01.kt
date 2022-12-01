fun main() {
    fun part1(input: List<String>): Int {
        var maxValue = 0
        var newMax = 0
        input.forEachIndexed{ index, element ->
            if (element.isEmpty()) {
                if (newMax > maxValue) {
                    maxValue = newMax
                }
                newMax = 0
            } else {
                newMax += Integer.parseInt(element)
                if (index == input.lastIndex && newMax > maxValue) {
                    maxValue = newMax
                }
            }
        }
        return maxValue
    }

    fun part2(input: List<String>): Int {
        val result = mutableListOf<Int>()
        var tempValue = 0
        input.forEachIndexed{ index, element ->
            if (element.isEmpty()) {
                result.add(tempValue)
                tempValue = 0
            } else {
                tempValue += Integer.parseInt(element)
                if (index == input.lastIndex) result.add(tempValue)
            }
        }
        return result.sorted().reversed().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
