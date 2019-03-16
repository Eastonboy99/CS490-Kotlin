fun main() {
    var id = 0
    val players = MutableList<Player>(5){Player(++id)}
    val game = Game(players,Dealer(), PlayDeck())

    game.start()
}