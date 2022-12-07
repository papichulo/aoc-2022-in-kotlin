fun main() {

    fun solve(input: List<String>, batchMove: Boolean): String {
        val stackLines = input.filter { '[' in it }
        val moveLines = input.filter { it.startsWith("move") }

        val stacks = Array((stackLines.maxOf { it.length } + 1) / 4) {
            ArrayDeque<Char>()
        }

        stackLines.forEach { line ->
            val crates = "$line ".chunked(4).map { it.trim() }
            crates.forEachIndexed { stack, crate ->
                if (crate.isNotEmpty()) {
                    stacks[stack].addFirst(crate[1])
                }
            }
        }

        moveLines.forEach { line ->
            val parts = line.split(" ")
            val amount = parts[1].toInt()
            val from = parts[3].toInt() - 1
            val to = parts[5].toInt() - 1
            if (batchMove) {
                var order = ""
                repeat(amount) {
                    order += stacks[from].removeLast()
                }
                order.reversed().forEach { crate ->
                    stacks[to].addLast(crate)
                }
            } else {
                repeat(amount) {
                    val crate = stacks[from].removeLast()
                    stacks[to].addLast(crate)
                }
            }
        }

        return stacks.map { it.last() }.joinToString("")
    }

    fun part2(input: List<String>): String {
        return solve(input, true)
    }

    fun part1(input: List<String>): String {
        return solve(input, false)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
