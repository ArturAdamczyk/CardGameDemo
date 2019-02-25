package art.com.cardgamedemo.framework

import art.com.cardgamedemo.R
import art.com.cardgamedemo.card_game.CardDecks

class CardHelper{
    companion object {
        const val EMPTY: Int = -1

        fun resolveName(cardType: String): Int {
            return when (cardType) {
                CardDecks.CardsType.ACE_CLUB.type -> R.string.ace_club
                CardDecks.CardsType.KING_CLUB.type -> R.string.king_club
                CardDecks.CardsType.QUEEN_CLUB.type -> R.string.queen_club
                CardDecks.CardsType.JACK_CLUB.type -> R.string.jack_club
                CardDecks.CardsType.TEN_CLUB.type -> R.string.ten_club
                CardDecks.CardsType.NINE_CLUB.type -> R.string.nine_club
                CardDecks.CardsType.EIGHT_CLUB.type -> R.string.eight_club
                CardDecks.CardsType.SEVEN_CLUB.type -> R.string.seven_club
                CardDecks.CardsType.SIX_CLUB.type -> R.string.six_club
                CardDecks.CardsType.FIVE_CLUB.type -> R.string.five_club
                CardDecks.CardsType.FOUR_CLUB.type -> R.string.four_club
                CardDecks.CardsType.THREE_CLUB.type -> R.string.three_club
                CardDecks.CardsType.TWO_CLUB.type -> R.string.two_club
                CardDecks.CardsType.ACE_DIAMOND.type -> R.string.ace_diamond
                CardDecks.CardsType.KING_DIAMOND.type -> R.string.king_diamond
                CardDecks.CardsType.QUEEN_DIAMOND.type -> R.string.queen_diamond
                CardDecks.CardsType.JACK_DIAMOND.type -> R.string.jack_diamond
                CardDecks.CardsType.TEN_DIAMOND.type -> R.string.ten_diamond
                CardDecks.CardsType.NINE_DIAMOND.type -> R.string.nine_diamond
                CardDecks.CardsType.EIGHT_DIAMOND.type -> R.string.eight_diamond
                CardDecks.CardsType.SEVEN_DIAMOND.type -> R.string.seven_diamond
                CardDecks.CardsType.SIX_DIAMOND.type -> R.string.six_diamond
                CardDecks.CardsType.FIVE_DIAMOND.type -> R.string.five_diamond
                CardDecks.CardsType.FOUR_DIAMOND.type -> R.string.four_diamond
                CardDecks.CardsType.THREE_DIAMOND.type -> R.string.three_diamond
                CardDecks.CardsType.TWO_DIAMOND.type -> R.string.two_diamond
                CardDecks.CardsType.ACE_HEART.type -> R.string.ace_heart
                CardDecks.CardsType.KING_HEART.type -> R.string.king_heart
                CardDecks.CardsType.QUEEN_HEART.type -> R.string.queen_heart
                CardDecks.CardsType.JACK_HEART.type -> R.string.jack_heart
                CardDecks.CardsType.TEN_HEART.type -> R.string.ten_heart
                CardDecks.CardsType.NINE_HEART.type -> R.string.nine_heart
                CardDecks.CardsType.EIGHT_HEART.type -> R.string.eight_heart
                CardDecks.CardsType.SEVEN_HEART.type -> R.string.seven_heart
                CardDecks.CardsType.SIX_HEART.type -> R.string.six_heart
                CardDecks.CardsType.FIVE_HEART.type -> R.string.five_heart
                CardDecks.CardsType.FOUR_HEART.type -> R.string.four_heart
                CardDecks.CardsType.THREE_HEART.type -> R.string.three_heart
                CardDecks.CardsType.TWO_HEART.type -> R.string.two_heart
                CardDecks.CardsType.ACE_SPADE.type -> R.string.ace_spade
                CardDecks.CardsType.KING_SPADE.type -> R.string.king_spade
                CardDecks.CardsType.QUEEN_SPADE.type -> R.string.queen_spade
                CardDecks.CardsType.JACK_SPADE.type -> R.string.jack_spade
                CardDecks.CardsType.TEN_SPADE.type -> R.string.ten_spade
                CardDecks.CardsType.NINE_SPADE.type -> R.string.nine_spade
                CardDecks.CardsType.EIGHT_SPADE.type -> R.string.eight_spade
                CardDecks.CardsType.SEVEN_SPADE.type -> R.string.seven_spade
                CardDecks.CardsType.SIX_SPADE.type -> R.string.six_spade
                CardDecks.CardsType.FIVE_SPADE.type -> R.string.five_spade
                CardDecks.CardsType.FOUR_SPADE.type -> R.string.four_spade
                CardDecks.CardsType.THREE_SPADE.type -> R.string.three_spade
                CardDecks.CardsType.TWO_SPADE.type -> R.string.two_spade
                else -> EMPTY
            }
        }

        fun resolveDrawable(cardType: String): Int {
            return when (cardType) {
                CardDecks.CardsType.ACE_CLUB.type -> R.drawable.club_ace
                CardDecks.CardsType.KING_CLUB.type -> R.drawable.club_king
                CardDecks.CardsType.QUEEN_CLUB.type -> R.drawable.club_queen
                CardDecks.CardsType.JACK_CLUB.type -> R.drawable.club_jack
                CardDecks.CardsType.TEN_CLUB.type -> R.drawable.club_ten
                CardDecks.CardsType.NINE_CLUB.type -> R.drawable.club_nine
                CardDecks.CardsType.EIGHT_CLUB.type -> R.drawable.club_eight
                CardDecks.CardsType.SEVEN_CLUB.type -> R.drawable.club_seven
                CardDecks.CardsType.SIX_CLUB.type -> R.drawable.club_six
                CardDecks.CardsType.FIVE_CLUB.type -> R.drawable.club_five
                CardDecks.CardsType.FOUR_CLUB.type -> R.drawable.club_four
                CardDecks.CardsType.THREE_CLUB.type -> R.drawable.club_three
                CardDecks.CardsType.TWO_CLUB.type -> R.drawable.club_two
                CardDecks.CardsType.ACE_DIAMOND.type -> R.drawable.diamond_ace
                CardDecks.CardsType.KING_DIAMOND.type -> R.drawable.diamond_king
                CardDecks.CardsType.QUEEN_DIAMOND.type -> R.drawable.diamond_queen
                CardDecks.CardsType.JACK_DIAMOND.type -> R.drawable.diamond_jack
                CardDecks.CardsType.TEN_DIAMOND.type -> R.drawable.diamond_ten
                CardDecks.CardsType.NINE_DIAMOND.type -> R.drawable.diamond_nine
                CardDecks.CardsType.EIGHT_DIAMOND.type -> R.drawable.diamond_eight
                CardDecks.CardsType.SEVEN_DIAMOND.type -> R.drawable.diamond_seven
                CardDecks.CardsType.SIX_DIAMOND.type -> R.drawable.diamond_six
                CardDecks.CardsType.FIVE_DIAMOND.type -> R.drawable.diamond_five
                CardDecks.CardsType.FOUR_DIAMOND.type -> R.drawable.diamond_four
                CardDecks.CardsType.THREE_DIAMOND.type -> R.drawable.diamond_three
                CardDecks.CardsType.TWO_DIAMOND.type -> R.drawable.diamond_two
                CardDecks.CardsType.ACE_HEART.type -> R.drawable.heart_ace
                CardDecks.CardsType.KING_HEART.type -> R.drawable.heart_king
                CardDecks.CardsType.QUEEN_HEART.type -> R.drawable.heart_queen
                CardDecks.CardsType.JACK_HEART.type -> R.drawable.heart_jack
                CardDecks.CardsType.TEN_HEART.type -> R.drawable.heart_ten
                CardDecks.CardsType.NINE_HEART.type -> R.drawable.heart_nine
                CardDecks.CardsType.EIGHT_HEART.type -> R.drawable.heart_eight
                CardDecks.CardsType.SEVEN_HEART.type -> R.drawable.heart_seven
                CardDecks.CardsType.SIX_HEART.type -> R.drawable.heart_six
                CardDecks.CardsType.FIVE_HEART.type -> R.drawable.heart_five
                CardDecks.CardsType.FOUR_HEART.type -> R.drawable.heart_four
                CardDecks.CardsType.THREE_HEART.type -> R.drawable.heart_three
                CardDecks.CardsType.TWO_HEART.type -> R.drawable.heart_two
                CardDecks.CardsType.ACE_SPADE.type -> R.drawable.spade_ace
                CardDecks.CardsType.KING_SPADE.type -> R.drawable.spade_king
                CardDecks.CardsType.QUEEN_SPADE.type -> R.drawable.spade_queen
                CardDecks.CardsType.JACK_SPADE.type -> R.drawable.spade_jack
                CardDecks.CardsType.TEN_SPADE.type -> R.drawable.spade_ten
                CardDecks.CardsType.NINE_SPADE.type -> R.drawable.spade_nine
                CardDecks.CardsType.EIGHT_SPADE.type -> R.drawable.spade_eight
                CardDecks.CardsType.SEVEN_SPADE.type -> R.drawable.spade_seven
                CardDecks.CardsType.SIX_SPADE.type -> R.drawable.spade_six
                CardDecks.CardsType.FIVE_SPADE.type -> R.drawable.spade_five
                CardDecks.CardsType.FOUR_SPADE.type -> R.drawable.spade_four
                CardDecks.CardsType.THREE_SPADE.type -> R.drawable.spade_three
                CardDecks.CardsType.TWO_SPADE.type -> R.drawable.spade_two
                else -> EMPTY
            }
        }
    }
}
