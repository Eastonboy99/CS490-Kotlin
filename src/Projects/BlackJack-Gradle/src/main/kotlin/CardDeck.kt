// Class to represent a card
data class Card(val suit: String, val number: Int) {
    override fun toString(): String {
        val card: String = when (number) {
            13 -> "King"
            12 -> "Queen"
            11 -> "Jack"
            1 -> "Ace"
            else -> {
                number.toString()
            }
        }
        return "$card of $suit"
    }

    fun value() = when (number) {
        13, 12, 11 -> 10
        else -> {
            number
        }
    }
}

// A standard 52 card deck
object DefaultCardDeck {
    var deck = mutableListOf<Card>()

    init {
        // suit order - Spades, Clubs, Hearts, Diamonds
        for (i in 1..4) {
            for (j in 1..13) {
                when (i) {
                    1 -> deck.add(Card("Spades", j))
                    2 -> deck.add(Card("Clubs", j))
                    3 -> deck.add(Card("Hearts", j))
                    4 -> deck.add(Card("Diamonds", j))
                }

            }
        }
    }
}

// The deck in play
class PlayDeck(numOfDecks: Int) {
    private val deck: MutableList<Card> = mutableListOf()

    init {
        for (x in 0 until numOfDecks) {
            deck.apply { addAll(DefaultCardDeck.deck) }
        }
        deck.shuffle()
    }

    private val discardPile = mutableListOf<Card>()
    private var cardNum = 0

    // Deals card(s) to a person
    fun dealCard(numOfCards: Int, person: Person): MutableList<Card> {
        val cards = mutableListOf<Card>()
        if (deck.size < numOfCards)
            reShuffleDeck()
        for (x in 0 until numOfCards) {
            val card = deck.get(cardNum)
            deck.removeAt(cardNum)
            discardPile.add(card)
            person.hand.addCard(card)
            cards.add(card)
        }
        return cards
    }

    // Reshuffles the deck
    private fun reShuffleDeck() {
        deck.apply { addAll(discardPile) }
        discardPile.clear()
        deck.shuffle()
    }

}
