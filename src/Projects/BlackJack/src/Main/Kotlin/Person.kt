// Class to represent a person with a hand of cards
abstract class Person {
    val hand = Hand()
}

// Class to represent the player(s) in the game
class Player(val id: Int) : Person() {
    var balance: Double = 1000.00
    var choice = ""
    var bet = 0.0


    // The Player randomly selects a bet
    fun bet(): Double {
        if (balance >= 20.0) {
            var num: Double = (5..20).shuffled().first().toDouble()
            balance -= num

            bet = num

        } else {
            bet = balance
            balance = 0.0
        }
        return bet

    }

    // The Player decides whether or not to hit or stand
    fun decide() {
        val dealersPoints = Dealer.hand.cards[0].value() + 10
        choice = when {
            hand.pointValue < dealersPoints -> "hit"
            hand.pointValue in (dealersPoints..21) -> "stand"
            hand.pointValue > 21 -> "bust"
            else -> ""
        }
    }
}
// Class to represent the Dealer
object Dealer : Person() {

    // Shows Players a card
    fun showCard(i: Int) = println("Dealer has shown ${hand.cards[i]}")

    // Dealer decides whether to hit or stand
    fun decide(deck: PlayDeck) {
        when {
            hand.pointValue <= 16 -> deck.dealCard(1, this)
            hand.pointValue in (17..21) -> "stand"
            hand.pointValue > 21 -> {
                println("Dealer Has Busted")
            }

        }
    }
}

// Class to represent a person's hand of cards
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
            return value
        }

    // Add card to hand
    fun addCard(card: Card) = cards.add(card)

    // Clear hand
    fun clearHand() = cards.clear()


    override fun toString(): String {
        var printString = ""
        cards.forEach { printString += "$it, " }
        return printString
    }
}