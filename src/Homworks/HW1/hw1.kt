package Homworks.HW1

fun main() {
    for (i in 1..100) {
        var printed = false
        if (i % 2 == 0) {
            print("snap ")
            printed = true
        }
        if (i % 3 == 0) {
            print("crackle ")
            printed = true

        }
        if (i % 5 == 0) {
            print("pop ")
            printed = true

        }
        if (!printed) {
            print(i)
        }
        println("")
    }
}
