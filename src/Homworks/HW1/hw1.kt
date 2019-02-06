package Homworks.HW1

fun main() {
    for (i in 1..100) {
        when {
            i % 2 == 0 && i % 3 == 0 && i % 5 == 0 -> println("snap crackle pop")
            i % 2 == 0 && i % 3 == 0 -> println("snap crackle")
            i % 3 == 0 && i % 5 == 0 -> println("crackle pop")
            i % 2 == 0 && i % 5 == 0 -> println("snap pop")
            i % 2 == 0 -> println("snap")
            i % 3 == 0 -> println("crackle")
            i % 5 == 0 -> println("pop")
            else -> {
                println(i)
            }
        }
    }
}
