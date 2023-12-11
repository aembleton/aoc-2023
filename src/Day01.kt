fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            (line.firstDigit() * 10) + line.lastDigit()}
    }

    fun part2(input: List<String>): Int {
        input.forEachIndexed { i, line ->
            println("($i) $line -> ${strToValue(line)}")}

        return input.sumOf { line ->
            strToValue(line)
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part2(testInput) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

fun String.firstDigit() = this.toCharArray().firstOrNull { it.isDigit() }?.digitToInt() ?: 0
fun String.lastDigit() = this.toCharArray().lastOrNull { it.isDigit() }?.digitToInt() ?: 0

fun strToValue(str:String):Int {
    val first = firstNumber(str)
    val last = lastNumber(str)
    return (first * 10) + last
}

fun firstNumber(str:String):Int {
    val first = str.firstDigit()
    val indexOfFirst = str.toCharArray().indexOfFirst { it.isDigit() }
    val checkTo = if(indexOfFirst > -1) indexOfFirst else str.length
    val checkForWords = str.substring(0, checkTo)
    val firstWord = (3..5).flatMap { checkForWords.windowed(it) }
        .filter { it in wordsToNumbers.keys }
        .map { it to str.indexOf(it) }
        .minByOrNull { it.second }?.first
    return wordsToNumbers[firstWord] ?: first
}

fun lastNumber(str:String):Int {
    val last = str.lastDigit()
    val indexOfLast = str.toCharArray().indexOfLast { it.isDigit() }
    val checkForWords = str.substring(indexOfLast + 1)
    val lastWord = (3..5).flatMap { checkForWords.windowed(it) }
        .filter { it in wordsToNumbers.keys }
        .map { it to str.indexOf(it) }.maxByOrNull { it.second }?.first
    return wordsToNumbers[lastWord] ?: last
}

val wordsToNumbers = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9,
)