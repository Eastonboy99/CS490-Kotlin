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

    val crimesPerDay =  crimes.size / crimes.groupBy { it.date }.size.toDouble()
    println("Crimes per day on average: ${crimesPerDay.roundToInt()}")

    val highestCrimeDate = crimes.groupBy { it.date }.maxBy { it.value.size }?.key ?: "Unknown"
    val maxCrimes = crimes.groupBy { it.date }.get(highestCrimeDate)?.size ?: "Unknown"
    println("$highestCrimeDate had the most reported crimes, with $maxCrimes reported.")

    val highestCrimeDistrict = crimes.groupBy { it.district }.maxBy { it.value.size }?.key?: "Unknown"
    println("District $highestCrimeDistrict had the most crime reported.")

    val lowestCrimeDistrict = crimes.groupBy { it.district }.minBy { it.value.size }?.key?: "Unknown"
    println("District $lowestCrimeDistrict had the least crime reported.")
    
    val distinctCrimes = crimes.groupBy { it.description }.size
    println("There were $distinctCrimes distinct types of crime reported.")

    val mostFrequentCrime = crimes.groupBy { it.description }.maxBy { it.value.size }?.key?: "Unknown"
    println("The most frequently reported crime was $mostFrequentCrime")
}