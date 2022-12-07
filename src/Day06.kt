fun main() {

    fun solve(input: String, distinct: Int): Int {
        var result = distinct
        input.windowed(distinct).forEach {
            if (it.toCharArray().distinct().size == distinct) {
                return result
            }
            result++
        }
        return result
    }

    fun part2(input: String): Int {
        return solve(input, 14)
    }

    fun part1(input: String): Int {
        return solve(input, 4)
    }

    // test if implementation meets criteria from the description, like:
    //val testInput = readInput("Day06_test")
    check(part1("mjqjpqmgbljsphdztnvjfqwrcgsmlb") == 7)
    check(part1("bvwbjplbgvbhsrlpgdmjqwftvncz") == 5)
    check(part1("nppdvjthqldpwncqszvftbrmjlhg") == 6)
    check(part1("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") == 10)
    check(part1("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") == 11)
    check(part2("mjqjpqmgbljsphdztnvjfqwrcgsmlb") == 19)
    check(part2("bvwbjplbgvbhsrlpgdmjqwftvncz") == 23)
    check(part2("nppdvjthqldpwncqszvftbrmjlhg") == 23)
    check(part2("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") == 29)
    check(part2("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") == 26)

    val input = readInput("Day06")
    println(part1(input.first()))
    println(part2(input.first()))
}
