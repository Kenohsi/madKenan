fun main() {
    val secretNumber = generateSecretNumber()
    var tries = 0

    println("Welcome to the Guessing Game!")
    println("I have generated a 4-digit number with no repeating digits. Can you guess it?")

    while (true) {
        tries++
        val guess = getValidGuess()
        val (n, m) = checkGuess(secretNumber, guess)
        println("Output: $n:$m")
        if (guess == secretNumber) {
            break
        }
    }

    println("Congratulations! You guessed the number in $tries tries.")
}

fun generateSecretNumber(): String {
    val digits = (0..9).shuffled().take(4)
    return digits.joinToString("")
}

fun getValidGuess(): String {
    while (true) {
        print("Enter your guess: ")
        val guess = readLine()?.trim()
        if (guess != null && guess.length == 4 && guess.all { it.isDigit() }) {
            return guess
        }
        println("Invalid guess. Please enter a 4-digit number.")
    }
}

fun checkGuess(secretNumber: String, guess: String): Pair<Int, Int> {
    val correctDigits = guess.filter { it in secretNumber }
    val numCorrect = correctDigits.length
    val numInPlace = guess.zip(secretNumber).count { (a, b) -> a == b }
    return Pair(numCorrect - numInPlace, numInPlace)
}
