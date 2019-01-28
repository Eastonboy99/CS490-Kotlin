package Homworks.HW1

fun main() {
    for (i in 1..100) {
        print(i.toString() + " ")
        if (i % 2 == 0) {
            print("snap ")
        }
        if (i % 3 == 0) {
            print("crackle ")
        }
        if (i % 5 == 0) {
            print("pop ")
        }
        println("")
    }
}



//        when (i % 2) {
//            0 -> println("snap")
//            1 -> println("crackle")
//            3 -> println("pop")
//            0, 1 -> println("snap crackle")
//            1, 3 -> println("crackle pop")
//            0, 3 -> println("snap pop")
//        }
//    }
//}