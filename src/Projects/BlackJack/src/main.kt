fun main() {
    var id = 0
    val players = MutableList<Player>(5){Player(++id)}
    val game = Game(players,Dealer(), PlayDeck(1))

    game.start()

//    Need to make bets per person and if you win you get double your bet

}