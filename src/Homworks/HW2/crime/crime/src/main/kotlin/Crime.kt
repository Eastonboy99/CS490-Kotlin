import java.io.File
import kotlin.math.roundToInt

data class Crime(val date: String, val district: Int, val description: String)

fun parse() = File(Crime::class.java.getResource("/crimes.csv").toURI())
                  .readLines()
                  .map {
                      val s = it.split(",")
                      Crime(s[0].substring(0 until s[0].indexOf(" ")), s[2].toInt(), s[5])
                  }

fun main() {
    val crimes = parse()

    // Use kotlin.collections and the extension functions it provides to answer the following questions
    // All of these answers can be calculated using a combination of methods provided by the kotlin std lib
    // map, maxBy, minBy, groupBy, sum, average, etc

    println("Sacramento Crime Report, January 2006")
    println("Total crimes reported: " + crimes.size)
    
    val crimesPerDay = 0.0 // Fixme
    println("Crimes per day on average: ${crimesPerDay.roundToInt()}")

    val highestCrimeDate = "1/1/06" // Fixme
    val maxCrimes = 0 // Fixme
    println("$highestCrimeDate had the most reported crimes, with $maxCrimes reported.")

    val highestCrimeDistrict = 0 // Fixme
    println("District $highestCrimeDistrict had the most crime reported.")

    val lowestCrimeDistrict = 0 // Fixme
    println("District $lowestCrimeDistrict had the least crime reported.")
    
    val distinctCrimes = 0 // Fixme
    println("There were $distinctCrimes distinct types of crime reported.")

    val mostFrequentCrime = "unknown" // Fixme
    println("The most frequently reported crime was $mostFrequentCrime")
}