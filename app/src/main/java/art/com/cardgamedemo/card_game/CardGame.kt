package art.com.cardgamedemo.card_game

import timber.log.Timber
import java.util.*

private val EMPTY: Int = -1

class CardGame(
    private var currentUserCard: Card = Card(""),
    private var cardIndex: Int = EMPTY,
    private val deckCards: Queue<Card> = LinkedList(),
    private val tableCards: Stack<Card> = Stack(),
    private val playerCards: LinkedList<Card> = LinkedList(),
    private val firstDealNumber: Int = GameConfig.firstLeadAmount
): CardGameActions {
    object GameConfig{
        const val firstLeadAmount: Int = 4
    }

    override fun start() {
        (1..firstDealNumber).forEach { playerCards.add(deckCards.poll()) }
        cardIndex = 0
        currentUserCard = playerCards[cardIndex]
    }

    override fun restart(deck: Queue<Card>){
        deckCards.clear()
        playerCards.clear()
        tableCards.clear()
        deckCards.addAll(deck)
        start()
    }

    private fun setCurrentIndex(){
        if(playerCards.isNotEmpty()){
            if(cardIndex + 1 == playerCards.size){
                cardIndex--
            }
        }
    }

    private fun setCurrentCard(){
        Timber.e("index nowego current card: " + cardIndex)
        Timber.e("wielkosc kart gracza: : " + playerCards.size)
        if(playerCards.isNotEmpty()){
            currentUserCard = playerCards[cardIndex]
        }else{
            currentUserCard = Card("")
        }
    }

    private fun logPlayerCards(){
        playerCards.forEach { Timber.e(it.name) }
        Timber.e(cardIndex.toString())
        Timber.e(currentUserCard.name)
        Timber.e(" ")
    }

    @Throws(CardGameException::class)
    override fun layOffCard() {
        Timber.e("lay off")
        if (playerCards.isNotEmpty()) {
            logPlayerCards()
            val card = currentUserCard
            tableCards.push(card)
            setCurrentIndex()
            playerCards.remove(card)
            setCurrentCard()
            logPlayerCards()
        }else{
            Timber.e("exception")
            throw CardGameException(CardGameExceptionType.PLAYER_CARDS_EMPTY.name)
        }
    }

    @Throws(CardGameException::class)
    override fun moveCurrentCardLeft(): Card {
        Timber.e("move left")
        logPlayerCards()
        Timber.e((cardIndex + 1).toString()+" vs "+(playerCards.size - 1).toString())
        if(cardIndex + 1 <= playerCards.size - 1){
            cardIndex++
            currentUserCard = playerCards[cardIndex]
        }else{
            Timber.e("exception")
            throw CardGameException(CardGameExceptionType.PLAYER_CARDS_EMPTY.name)
        }
        logPlayerCards()
        return currentUserCard
    }

    @Throws(CardGameException::class)
    override fun moveCurrentCardRight(): Card {
        Timber.e("move_right")
        logPlayerCards()
        Timber.e((cardIndex - 1).toString()+" vs "+(playerCards.size - 1).toString())
        if(cardIndex - 1 <= playerCards.size - 1 && cardIndex -1 >= 0){
            cardIndex--
            currentUserCard = playerCards[cardIndex]
        }else{
            Timber.e("exception")
            throw CardGameException(CardGameExceptionType.PLAYER_CARDS_EMPTY.name)
        }
        logPlayerCards()
        return currentUserCard
    }

    @Throws(CardGameException::class)
    override fun getCurrentCard(): Card {
        return currentUserCard
    }

    @Throws(CardGameException::class)
    override fun returnCard(){
        Timber.e("return card")
        if (playerCards.isNotEmpty()) {
            logPlayerCards()
            val card = playerCards[cardIndex]
            deckCards.offer(card)
            setCurrentIndex()
            playerCards.remove(card)
            setCurrentCard()
            logPlayerCards()
        }else{
            Timber.e("exception")
            throw CardGameException(CardGameExceptionType.PLAYER_CARDS_EMPTY.name)
        }
    }

    @Throws(CardGameException::class)
    override fun getNewCard(){
        Timber.e("get new card")
        logPlayerCards()
        if(deckCards.isEmpty()){
            Timber.e("exception")
            throw CardGameException(CardGameExceptionType.EMPTY_DECK.name)
        }
        cardIndex++
        playerCards.add(cardIndex, deckCards.poll())
        setCurrentCard()
        logPlayerCards()
    }
}