package art.com.cardgamedemo.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.subscribeBy
import art.com.cardgamedemo.R
import art.com.cardgamedemo.card_game.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber


class MainViewModel (
    app: Application,
    private val cardGame: CardGame,
    private val cardDeck: CardDecks,
    private val resources: ResourcesProvider
) : AndroidViewModel(app) {
    private var disposables = CompositeDisposable()
    private val _currentCard = MutableLiveData<Card>()
    val currentCard: LiveData<Card> by lazy { _currentCard }

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> by lazy { _message }

    private val _cardMessage = MutableLiveData<String>()
    val cardMessage: LiveData<String> by lazy { _cardMessage }

    fun layOffCard(){
        disposables += cardGame.layOffCard()
            .baseCall()
            .subscribeBy(
                onComplete = {
                    _message.postValue(resources.getString(R.string.card_lay_off))
                    refreshCurrentCard()
                },
                onError = { handleError(it as CardGameException, resources.getString(R.string.card_lay_off_error)) }
            )
    }

    fun moveCurrentCardLeft(){
        disposables += cardGame.moveCurrentCardLeft()
            .baseCall()
            .subscribeBy(
                onComplete = { refreshCurrentCard(true) },
                onError = { handleError(it as CardGameException, resources.getString(R.string.card_move_left_error)) }
            )
    }

    fun moveCurrentCardRight(){
        disposables += cardGame.moveCurrentCardRight()
            .baseCall()
            .subscribeBy(
                onComplete = { refreshCurrentCard(true) },
                onError = { handleError(it as CardGameException, resources.getString(R.string.card_move_right_error)) }
            )
    }

    fun returnCard(){
        disposables += cardGame.returnCard()
            .baseCall()
            .subscribeBy(
                onComplete = {
                    refreshCurrentCard()
                    _message.postValue(resources.getString(R.string.card_returned))
                },
                onError = { handleError(it as CardGameException, resources.getString(R.string.card_returned_error)) }
            )
    }

    fun getNewCard(){
        disposables += cardGame.getNewCard()
            .baseCall()
            .subscribeBy(
                onComplete = {
                    refreshCurrentCard()
                    _message.postValue(resources.getString(R.string.card_obtained))
                },
                onError = { handleError(it as CardGameException, resources.getString(R.string.card_obtained_error)) }
            )
    }

    fun start(){
        disposables += cardGame.start()
            .baseCall()
            .subscribeBy(
                onComplete = {
                    refreshCurrentCard()
                    _message.postValue(resources.getString(R.string.game_started))
                },
                onError = { handleError(it as CardGameException, resources.getString(R.string.start_game_error)) }
            )
    }

    fun restart(){
        disposables += cardGame.restart(cardDeck.getDeck())
            .baseCall()
            .subscribeBy(
                onComplete = {
                    refreshCurrentCard()
                    _message.postValue(resources.getString(R.string.game_restarted))
                },
                onError = { handleError(it as CardGameException, resources.getString(R.string.restart_game_error)) }
            )
    }

    private fun refreshCurrentCard(withMessage: Boolean = false){
        disposables += cardGame.getCurrentCard()
            .baseCall()
            .subscribeBy (
            onSuccess = {
                response ->
                _currentCard.postValue(response)
                if(withMessage){ _cardMessage.postValue(response.name)}
            },
            onError = { handleError(it as CardGameException, resources.getString(R.string.card_refresh_error)) }
        )
    }

    private fun handleError(throwable: CardGameException, log: String){
        Timber.e(log)
        when(throwable.message){
            CardGameExceptionType.EMPTY_DECK.name -> resources.getString(R.string.card_deck_empty)
            CardGameExceptionType.PLAYER_CARDS_EMPTY.name -> resources.getString(R.string.player_cards_end)
            CardGameExceptionType.OTHER.name -> {""}
            else -> resources.getString(R.string.unexpected_error)
        }.apply{
            _message.postValue(this)
            Timber.e(this)
        }
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}