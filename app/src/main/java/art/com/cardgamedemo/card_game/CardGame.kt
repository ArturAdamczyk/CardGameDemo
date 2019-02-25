package art.com.cardgamedemo.card_game

import io.reactivex.Completable
import io.reactivex.Single
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

    override fun start(): Completable {
        (1..firstDealNumber).forEach { playerCards.add(deckCards.poll()) }
        cardIndex = 0
        currentUserCard = playerCards[cardIndex]
        return Completable.create{it.onComplete()}
    }

    override fun restart(deck: Queue<Card>): Completable{
        deckCards.clear()
        playerCards.clear()
        tableCards.clear()
        deckCards.addAll(deck)
        start()
        return Completable.create{it.onComplete()}
    }

    @Throws(CardGameException::class)
    override fun layOffCard(): Completable {
        if (playerCards.isNotEmpty()) {
            val card = currentUserCard
            tableCards.push(card)
            setCurrentIndex()
            playerCards.remove(card)
            setCurrentCard()
        }else{
            Timber.e(CardGameExceptionType.PLAYER_CARDS_EMPTY.name)
            return Completable.create{it.onError(
                CardGameException(CardGameExceptionType.PLAYER_CARDS_EMPTY.name))}
        }
        return Completable.create{it.onComplete()}
    }

    @Throws(CardGameException::class)
    override fun moveCurrentCardLeft(): Completable {
        if(playerCards.isEmpty()){
            return Completable.create{it.onError(
                (CardGameException(CardGameExceptionType.PLAYER_CARDS_EMPTY.name)))}
        }
        if(cardIndex + 1 <= playerCards.size - 1){
            cardIndex++
            currentUserCard = playerCards[cardIndex]
        }else{
            return Completable.create{it.onError(
                (CardGameException(CardGameExceptionType.OTHER.name)))}
        }
        return Completable.create{it.onComplete()}
    }

    @Throws(CardGameException::class)
    override fun moveCurrentCardRight(): Completable {
        if(playerCards.isEmpty()){
            return Completable.create{it.onError(
                (CardGameException(CardGameExceptionType.PLAYER_CARDS_EMPTY.name)))}
        }
        if(cardIndex - 1 <= playerCards.size - 1 && cardIndex -1 >= 0){
            cardIndex--
            currentUserCard = playerCards[cardIndex]
        }else{
            return Completable.create{it.onError(
                CardGameException(CardGameExceptionType.OTHER.name))}
        }
        return Completable.create{it.onComplete()}
    }

    @Throws(CardGameException::class)
    override fun getCurrentCard(): Single<Card> {
        return Single.create{it.onSuccess(currentUserCard)}
    }

    @Throws(CardGameException::class)
    override fun returnCard(): Completable{
        if (playerCards.isNotEmpty()) {
            val card = playerCards[cardIndex]
            deckCards.offer(card)
            setCurrentIndex()
            playerCards.remove(card)
            setCurrentCard()
        }else{
            Timber.e(CardGameExceptionType.PLAYER_CARDS_EMPTY.name)
            return Completable.create{it.onError(
                (CardGameException(CardGameExceptionType.PLAYER_CARDS_EMPTY.name)))}
        }
        return Completable.create{it.onComplete()}
    }

    @Throws(CardGameException::class)
    override fun getNewCard(): Completable{
        if(deckCards.isEmpty()){
            Timber.e(CardGameExceptionType.EMPTY_DECK.name)
            return Completable.create{it.onError(
                (CardGameException(CardGameExceptionType.EMPTY_DECK.name)))}
        }
        cardIndex++
        playerCards.add(cardIndex, deckCards.poll())
        setCurrentCard()
        return Completable.create{it.onComplete()}
    }

    private fun setCurrentIndex(){
        if(playerCards.isNotEmpty()){
            if(cardIndex + 1 == playerCards.size){ cardIndex-- }
        }
    }

    private fun setCurrentCard(){
        currentUserCard = if (playerCards.isNotEmpty())  playerCards[cardIndex] else Card("")
    }

    // for debugging purpose
    private fun logPlayerCards(){
        playerCards.forEach { Timber.e(it.name) }
        Timber.e(cardIndex.toString())
        Timber.e(currentUserCard.name)
        Timber.e(" ")
    }
}