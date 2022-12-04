fun main() {

    fun overlapsComplete(s1List: List<Int>, s2List: List<Int>): Int {
        return if ((s1List.first() <= s2List.first() && s1List.last() >= s2List.last()) ||
                (s2List.first() <= s1List.first() && s2List.last() >= s1List.last())) 1 else 0
    }

    fun overlapsPartially(s1List: List<Int>, s2List: List<Int>): Int {
        return if ((s1List.first() <= s2List.first() && s1List.last() >= s2List.first()) ||
                (s1List.first() <= s2List.last() && s1List.last() >= s2List.last()) ||
                (s2List.first() <= s1List.first() && s2List.last() >= s1List.first()) ||
                (s2List.first() <= s1List.last() && s2List.last() >= s1List.last())) 1 else 0
    }

    fun overlaps(s1: String, s2: String, complete: Boolean = true): Int {
        val s1List = s1.split('-').map { it.toInt() }
        val s2List = s2.split('-').map { it.toInt() }
        return if (complete) overlapsComplete(s1List, s2List) else overlapsPartially(s1List, s2List)
    }

    fun part2(input: List<String>): Int {
        return input.map { t -> t.split(',') }
            .sumOf { overlaps(it.first(), it.last(), false) }
    }

    fun part1(input: List<String>): Int {
        return input.map { t -> t.split(',') }
            .sumOf { overlaps(it.first(), it.last()) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
