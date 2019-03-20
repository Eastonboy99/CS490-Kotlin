fun main() {
    var id = 0
    val players = MutableList(5) { Player(++id) }
    val game = Game(players, PlayDeck(1))

    game.start()

}