package art.com.cardgamedemo.card_game

import java.util.*

interface CardGameActions {
    fun layOffCard()
    fun moveCurrentCardLeft(): Card
    fun moveCurrentCardRight(): Card
    fun getCurrentCard(): Card
    fun returnCard()
    fun getNewCard()
    fun start()
    fun restart(deck: Queue<Card>)
}