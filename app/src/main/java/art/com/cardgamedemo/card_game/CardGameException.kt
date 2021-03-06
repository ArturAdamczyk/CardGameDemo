package art.com.cardgamedemo.card_game

class CardGameException( message: String) : Throwable(message)

enum class CardGameExceptionType {
    EMPTY_DECK,
    PLAYER_CARDS_EMPTY,
    OTHER
}