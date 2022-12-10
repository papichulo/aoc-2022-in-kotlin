data class Dir(
    var name: String,
    var size: Long = 0L,
    var parent: Dir?
)

fun main() {
    fun calculateSize(dir: Dir, addSize: Long) {
        dir.size += addSize
        if (dir.parent != null) calculateSize(dir.parent!!, addSize)
    }
    fun parseLine(line: List<String>, current: Dir, dirs: MutableMap<String, Dir>): Dir {
        println(line)
        when (line[0]) {
            "$" -> {
                return if (line[1] == "cd") {
                    if (line[2] == "..") current.parent!! else if  (line[2].startsWith("/")) dirs[line[2]]!! else dirs["${current.name}/${line[2]}"]!!
                } else {
                    current
                }
            }
            "dir" -> {
                val newDir = Dir("${current.name}/${line[1]}", parent = current)
                dirs[newDir.name] = newDir
            }
            else -> calculateSize(current, line[0].toLong())
        }
        return current
    }

    fun parseDirs(input: List<String>): Map<String, Dir> {
        val dirs = mutableMapOf<String, Dir>()
        var current = Dir("/", parent = null)
        dirs[current.name] = current
        input.forEach { line ->
            current = parseLine(line.split(" "), current, dirs)
        }
        return dirs
    }

    fun part1(input: List<String>): Long {
        return parseDirs(input).values.filter { it.size < 100_000L }.sumOf { it.size }
    }

    fun part2(input: List<String>): Long {
        val dirs = parseDirs(input)
        return dirs.values.filter { 70_000_000 - dirs["/"]!!.size + it.size >= 30_000_000 }.minBy { it.size }.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437L)
    check(part2(testInput) == 24933642L)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
