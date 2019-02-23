package art.com.cardgamedemo.card_game

import java.util.*

class CardDecks{

    companion object{
        val EMPTY: Int = -1
    }

    /**
        club = trefl/żołądź
        diamond - karo/dzwonek
        heart - kier/serce
        spade - pik/wino
        jack - walet
        queen - dama
        king - król
        ace - as
     **/
    enum class CardsType(val type: String) {
        ACE_CLUB("ACE_CLUB"),
        KING_CLUB( "KING_CLUB"),
        QUEEN_CLUB( "QUEEN_CLUB"),
        JACK_CLUB( "JACK_CLUB"),
        TEN_CLUB( "TEN_CLUB"),
        NINE_CLUB( "NINE_CLUB"),
        EIGHT_CLUB( "EIGHT_CLUB"),
        SEVEN_CLUB( "SEVEN_CLUB"),
        SIX_CLUB( "SIX_CLUB"),
        FIVE_CLUB( "FIVE_CLUB"),
        FOUR_CLUB( "FOUR_CLUB"),
        THREE_CLUB( "THREE_CLUB"),
        TWO_CLUB( "TWO_CLUB"),
        ACE_DIAMOND("ACE_DIAMOND"),
        KING_DIAMOND( "KING_DIAMOND"),
        QUEEN_DIAMOND( "QUEEN_DIAMOND"),
        JACK_DIAMOND( "JACK_DIAMOND"),
        TEN_DIAMOND( "TEN_DIAMOND"),
        NINE_DIAMOND( "NINE_DIAMOND"),
        EIGHT_DIAMOND( "EIGHT_DIAMOND"),
        SEVEN_DIAMOND( "SEVEN_DIAMOND"),
        SIX_DIAMOND( "SIX_DIAMOND"),
        FIVE_DIAMOND( "FIVE_DIAMOND"),
        FOUR_DIAMOND( "FOUR_DIAMOND"),
        THREE_DIAMOND( "THREE_DIAMOND"),
        TWO_DIAMOND( "TWO_DIAMOND"),
        ACE_HEART("ACE_HEART"),
        KING_HEART( "KING_HEART"),
        QUEEN_HEART( "QUEEN_HEART"),
        JACK_HEART( "JACK_HEART"),
        TEN_HEART( "TEN_HEART"),
        NINE_HEART( "NINE_HEART"),
        EIGHT_HEART( "EIGHT_HEART"),
        SEVEN_HEART( "SEVEN_HEART"),
        SIX_HEART( "SIX_HEART"),
        FIVE_HEART( "FIVE_HEART"),
        FOUR_HEART( "FOUR_HEART"),
        THREE_HEART( "THREE_HEART"),
        TWO_HEART( "TWO_HEART"),
        ACE_SPADE("ACE_SPADE"),
        KING_SPADE( "KING_SPADE"),
        QUEEN_SPADE( "QUEEN_SPADE"),
        JACK_SPADE( "JACK_SPADE"),
        TEN_SPADE( "TEN_SPADE"),
        NINE_SPADE( "NINE_SPADE"),
        EIGHT_SPADE( "EIGHT_SPADE"),
        SEVEN_SPADE( "SEVEN_SPADE"),
        SIX_SPADE( "SIX_SPADE"),
        FIVE_SPADE( "FIVE_SPADE"),
        FOUR_SPADE( "FOUR_SPADE"),
        THREE_SPADE( "THREE_SPADE"),
        TWO_SPADE( "TWO_SPADE"),
    }


    object StandardDeck {
        val cards: MutableList<Card> = mutableListOf(
            Card(CardsType.ACE_CLUB.type),
            Card(CardsType.KING_CLUB.type),
            Card(CardsType.QUEEN_CLUB.type),
            Card(CardsType.JACK_CLUB.type),
            Card(CardsType.TEN_CLUB.type),
            Card(CardsType.NINE_CLUB.type),
            Card(CardsType.EIGHT_CLUB.type),
            Card(CardsType.SEVEN_CLUB.type),
            Card(CardsType.SIX_CLUB.type),
            Card(CardsType.FIVE_CLUB.type),
            Card(CardsType.FOUR_CLUB.type),
            Card(CardsType.THREE_CLUB.type),
            Card(CardsType.TWO_CLUB.type),
            Card(CardsType.ACE_DIAMOND.type),
            Card(CardsType.KING_DIAMOND.type),
            Card(CardsType.QUEEN_DIAMOND.type),
            Card(CardsType.JACK_DIAMOND.type),
            Card(CardsType.TEN_DIAMOND.type),
            Card(CardsType.NINE_DIAMOND.type),
            Card(CardsType.EIGHT_DIAMOND.type),
            Card(CardsType.SEVEN_DIAMOND.type),
            Card(CardsType.SIX_DIAMOND.type),
            Card(CardsType.FIVE_DIAMOND.type),
            Card(CardsType.FOUR_DIAMOND.type),
            Card(CardsType.THREE_DIAMOND.type),
            Card(CardsType.TWO_DIAMOND.type),
            Card(CardsType.ACE_HEART.type),
            Card(CardsType.KING_HEART.type),
            Card(CardsType.QUEEN_HEART.type),
            Card(CardsType.JACK_HEART.type),
            Card(CardsType.TEN_HEART.type),
            Card(CardsType.NINE_HEART.type),
            Card(CardsType.EIGHT_HEART.type),
            Card(CardsType.SEVEN_HEART.type),
            Card(CardsType.SIX_HEART.type),
            Card(CardsType.FIVE_HEART.type),
            Card(CardsType.FOUR_HEART.type),
            Card(CardsType.THREE_HEART.type),
            Card(CardsType.TWO_HEART.type),
            Card(CardsType.ACE_SPADE.type),
            Card(CardsType.KING_SPADE.type),
            Card(CardsType.QUEEN_SPADE.type),
            Card(CardsType.JACK_SPADE.type),
            Card(CardsType.TEN_SPADE.type),
            Card(CardsType.NINE_SPADE.type),
            Card(CardsType.EIGHT_SPADE.type),
            Card(CardsType.SEVEN_SPADE.type),
            Card(CardsType.SIX_SPADE.type),
            Card(CardsType.FIVE_SPADE.type),
            Card(CardsType.FOUR_SPADE.type),
            Card(CardsType.THREE_SPADE.type),
            Card(CardsType.TWO_SPADE.type)
        )
    }

    fun getDeck(): LinkedList<Card> {
        val deck = LinkedList<Card>()
        deck.addAll(StandardDeck.cards.apply { shuffle() })
        return deck
    }

}