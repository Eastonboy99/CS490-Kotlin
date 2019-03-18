class Game(val players: MutableList<Player>, val dealer: Dealer, val deck: PlayDeck) {

    val zeroBalancePlayers = mutableListOf<Player>()
    fun start() {
        var x = 1
        while (x <= 100 && players.size > 0) {
            println("------------------------ Begin Round $x ----------------------------------")
            //Take bets from players
            takeBets()
            // Deal cards to both Dealer and Players
            dealCards()
            //Dealer shows first card and keeps hole card hidden
            dealer.showCard(0)

            //Players Decide
            playersDecide()

            //Dealer shows hole card
            dealer.showCard(1)

            //dealer decides
            dealer.decide(deck)
            //find out who won
            findRoundWinners()

            checkBalances()

            roundReset()
            x++
        }
        println("-------------------------- Game End ------------------------------------")
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
        cards = deck.dealCard(2, dealer)
        cards.forEach { println("Dealer received $it") }

    }

    private fun playersDecide() {
        for (player in players) {
            do {
                player.decide()
                if (player.choice == "hit") {
                    var cards = deck.dealCard(1, player)
                    println("Player ${player.id} has chosen to Hit and has received ${cards[0]}")
                } else {
                    println("Player ${player.id} has chosen to Stand")
                }
            } while (player.choice == "hit")
        }

    }

    private fun findRoundWinners() {
        val winners = mutableListOf<Player>()
        print("Point Values: ")
        players.forEach { player -> print("Player ${player.id} - ${player.hand.pointValue}; ") }
        println("Dealer - ${dealer.hand.pointValue}")
        when {
            dealer.hand.pointValue <= 21 -> for (player in players) {
                if (player.hand.pointValue <= 21) {
                    if (dealer.hand.pointValue < player.hand.pointValue) {
                        winners.add(player)
                        println("Player ${player.id} won")
                    }
                    1
                }
            }
            dealer.hand.pointValue > 21 -> for (player in players) {
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
        dealer.hand.clearHand()
        players.forEach {
            it.hand.clearHand()
            it.bet = 0.0
        }

    }
}