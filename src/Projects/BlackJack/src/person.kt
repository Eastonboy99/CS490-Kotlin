open class Person {
    val hand = Hand();


}

class Player(id: Int) : Person() {
    var balance: Double = 1000.00
    var choice = ""
    val id = id


    fun bet(): Double {
        if (balance >= 20.0) {
            var num: Double = (5..20).shuffled().first().toDouble()
            balance -= num

            return num
        } else {
            balance = 0.0
            return balance
        }

    }

    fun decide() {
        choice = when {
            hand.pointValue < 15 -> "hit"
            hand.pointValue >= 15 && hand.pointValue <= 21 -> "stand"
            hand.pointValue > 21 -> "bust"
            else -> ""
        }
    }
}

class Dealer : Person() {
    var bets = 0.0

    fun showCard(i: Int) = println("Dealer has show ${hand.cards[i]}")
    fun decide(deck: PlayDeck) {
        when {
            hand.pointValue <= 16 -> deck.dealCard(1, this)
            hand.pointValue > 16 && hand.pointValue <= 21 -> "stand"
            hand.pointValue > 21 -> {
                println("Dealer Has Busted")
            }

        }
    }
}

class Hand {
    val cards = mutableListOf<Card>()
    var pointValue: Int = 0
        get() {
            var numOfAces = 0
            var value = 0
            cards.forEach { if (it.number == 1) numOfAces++ else value += it.value() }
            for (x in 0 until numOfAces)
                when (21 - value) {
                    in 11..21 -> value += 11
                    else -> value++
                }
            return value;
        }


    public fun addCard(card: Card) = cards.add(card)
//        .also{pointValue += card.value()}

    public fun clearHand() = cards.clear()
    override fun toString(): String {
        var printString = ""
        cards.forEach { printString += "$it, " }
        return printString
    }
}