import java.io.BufferedReader
import java.io.InputStreamReader
import java.time.Duration
import java.time.Instant
import kotlin.random.Random

private val secondsPerQuestion = 5

val errorMessages = arrayOf(
    "fel, försök igen",
    "inte riktigt rätt",
)

fun main() {
    val readFrom = BufferedReader(InputStreamReader(System.`in`))

    var totalTime = Duration.ZERO
    val numberOfQuestions = 20

    // Stora minus10
    for (i in 1..numberOfQuestions) {
        val left = Random.nextInt(10, 20)
        val right = Random.nextInt(left)

        val timeBefore = Instant.now()

        var correct = false

        while (!correct) {
            print("Fråga: $i - vad är $left - $right? ")
            try {
                val svar = readFrom.readLine().toInt()

                if (svar == left - right) {
                    correct = true
                } else {
                    print("${randomMessage(errorMessages)}\n")
                }
            } catch (e: NumberFormatException) {
                print("Inte ett tal, försök igen\n")
            }
        }
        val timeTaken = Duration.between(timeBefore, Instant.now())

        totalTime = totalTime.plus(timeTaken)

        print("Det tog ${timeTaken.seconds} sekunder att svara rätt\n")
    }

    val timePerQuestion = totalTime.seconds / numberOfQuestions

    print("Bra jobbat, du svarade rätt på $numberOfQuestions frågor på ${totalTime.seconds} sekunder!\n")
    if (timePerQuestion < secondsPerQuestion) {
        print("Du är snabb nog att klara stora minus!")
    } else {
        print("Du behöver öva mera! Det tog $timePerQuestion sekunder per fråga, det får ta $secondsPerQuestion sekunder")
    }
}

fun randomMessage(strings: Array<String>): String =
    strings[Random.nextInt(strings.size)]
