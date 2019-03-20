// Class to represent the Game
class Game(private val players: MutableList<Player>, private val deck: PlayDeck) {

    private val zeroBalancePlayers = mutableListOf<Player>()
    // Start the game
    fun start() {
        var x = 1
        while (x <= 100 && players.size > 0) {
            println("------------------------ Begin Round $x ----------------------------------")
            //Take bets from players
            takeBets()

            // Deal cards to both Dealer and Players
            dealCards()

            // Dealer shows first card and keeps hole card hidden
            Dealer.showCard(0)

            // Players Decide
            playersDecide()

            // Dealer shows hole card
            Dealer.showCard(1)

            // Dealer decides
            Dealer.decide(deck)

            // Determine who won
            findRoundWinners()

            // See if any players have run out of money
            checkBalances()
            
            // Reset each players hand and the dealers hand
            roundReset()
            x++
        }
        println("-------------------------- Game End ------------------------------------")

        showPlayerBalances()
    }

    private fun takeBets() {
        var bet: Double
        players.forEach {
            bet = it.bet()
            println("Player ${it.id} bet $${bet.toMoney()}")
            println("Player ${it.id}'s balance is $${it.balance.toMoney()}")

        }
    }

    private fun dealCards() {
        var cards: MutableList<Card>?
        for (player in players) {
            cards = deck.dealCard(2, player)
            cards.forEach { println("Player ${player.id} received $it") }
        }
        cards = deck.dealCard(2, Dealer)
        cards.forEach { println("Dealer received $it") }

    }

    private fun playersDecide() {
        for (player in players) {
            do {
                player.decide()
                when(player.choice) {
                    "hit" -> {
                        val cards = deck.dealCard(1, player)
                        println("Player ${player.id} has chosen to Hit and has received ${cards[0]}")
                    }
                    "stand" -> println("Player ${player.id} has chosen to Stand")
                    "bust" -> println("Player ${player.id} has busted")
                }
            } while (player.choice == "hit")
        }

    }

    private fun findRoundWinners() {
        val winners = mutableListOf<Player>()
        print("Point Values: ")
        players.forEach { player -> print("Player ${player.id} - ${player.hand.pointValue}; ") }
        println("Dealer - ${Dealer.hand.pointValue}")
        when {
            Dealer.hand.pointValue <= 21 -> for (player in players) {
                if (player.hand.pointValue <= 21) {
                    if (Dealer.hand.pointValue < player.hand.pointValue) {
                        winners.add(player)
                        println("Player ${player.id} won")
                    }
                }
            }
            Dealer.hand.pointValue > 21 -> for (player in players) {
                if (player.hand.pointValue <= 21) {
                    winners.add(player)
                }
            }
        }
        if (winners.size == 0)
            println("Dealer Won")
        else
            winners.forEach { player -> player.balance += player.bet * 2; println("Player ${player.id} received $${(player.bet * 2).toMoney()}") }


    }

    private fun checkBalances() {
        for (i in players.indices) {
            if (players[i].balance < 5.0) {
                zeroBalancePlayers.add(players[i]); players.remove(players[i])
            }
        }
    }

    private fun roundReset() {
        Dealer.hand.clearHand()
        players.forEach {
            it.hand.clearHand()
            it.bet = 0.0
        }

    }

    private fun showPlayerBalances(){
        println("Balances at end of game: ")
        players.forEach { print("Player ${it.id} - $${it.balance.toMoney()} (${(it.balance - 1000.00).toMoney()}), ") }
        print("\n")
    }
}