package art.com.cardgamedemo.framework

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat
import art.com.cardgamedemo.R
import art.com.cardgamedemo.card_game.*
import art.com.cardgamedemo.framework.Configs.POLISH_LOCALE
import com.bumptech.glide.Glide
import java.util.*
import com.github.pwittchen.swipe.library.rx2.Swipe
import com.github.pwittchen.swipe.library.rx2.SwipeEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber


class MainActivity:
    AppCompatActivity(),
    GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener,
    TextToSpeech.OnInitListener {

    private lateinit var gestureDetector: GestureDetectorCompat
    private lateinit var textToSpeech: TextToSpeech
    private val speakingQueue = LinkedList<String>()
    private var swipe: Swipe = Swipe()
    private lateinit var disposable: Disposable
    private lateinit var cardGame: CardGame
    private val context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupTextToSpeech()
        setupGestureDetector()
        setupGame()

            disposable = swipe.observe()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { swipeEvent ->
                    try{
                        when(swipeEvent) {
                            SwipeEvent.SWIPED_LEFT -> run{
                                readTextAloud(getCardName(cardGame.moveCurrentCardLeft()))
                                loadCardImage(cardGame.getCurrentCard())
                            }
                            SwipeEvent.SWIPED_RIGHT -> run{
                                readTextAloud(getCardName(cardGame.moveCurrentCardRight()))
                                loadCardImage(cardGame.getCurrentCard())
                            }
                            SwipeEvent.SWIPED_UP -> run{
                                cardGame.layOffCard()
                                loadCardImage(cardGame.getCurrentCard())
                                readTextAloud(getString(R.string.card_lay_off))
                            }
                            SwipeEvent.SWIPED_DOWN -> run{
                                cardGame.restart(CardDecks().getDeck())
                                loadCardImage(cardGame.getCurrentCard())
                                readTextAloud(getString(R.string.game_restarted))
                            }
                        }
                    }catch(e: CardGameException){
                        when(e.message){
                            CardGameExceptionType.EMPTY_DECK.name -> getString(R.string.card_deck_empty)
                            CardGameExceptionType.EMPTY_DECK.name -> getString(R.string.player_cards_end)
                            else -> getString(R.string.unexpected_error)
                        }.apply{ Timber.e(this) }
                    }
                }

    }

    override fun onDestroy(){
        with(textToSpeech) {
            stop()
            shutdown()
        }
        super.onDestroy()
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        return swipe.dispatchTouchEvent(event) || super.dispatchTouchEvent(event)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onDone(utteranceId: String) {
                    if (!speakingQueue.isEmpty()) {
                        textToSpeech.speak(speakingQueue.pop(), TextToSpeech.QUEUE_FLUSH, null, "")
                    }
                }
                override fun onError(utteranceId: String) {}
                override fun onStart(utteranceId: String) {}
            })
            readTextAloud(getString(R.string.game_started))
        } else {
            //logMessage(resources.getString(R.string.documentation_tts_not_available))
        }
    }

    override fun onDoubleTap(event: MotionEvent): Boolean {
        cardGame.getNewCard()
        readTextAloud(getString(R.string.card_obtained))
        loadCardImage(cardGame.getCurrentCard())
        return true
    }

    override fun onLongPress(event: MotionEvent) {
        cardGame.returnCard()
        readTextAloud(getString(R.string.card_returned))
        loadCardImage(cardGame.getCurrentCard())
    }

    override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
        readTextAloud(getCardName(cardGame.getCurrentCard()))
        return true
    }

    private fun setupTextToSpeech(){
        textToSpeech = TextToSpeech(this, this)
        textToSpeech.setLanguage(Locale(POLISH_LOCALE))
    }

    private fun setupGestureDetector(){
        gestureDetector = GestureDetectorCompat(this, this)
        gestureDetector.setOnDoubleTapListener(this)
    }

    private fun readTextAloud(msg: String) {
        speakingQueue.add(msg)
        textToSpeech.speak(speakingQueue.pop(), TextToSpeech.QUEUE_FLUSH, null, "")
    }

    private fun loadCardImage(card: Card){
        CardHelper.resolveDrawable(card).apply {
            if (this != CardDecks.EMPTY){
                Glide.with(context).load(this).into(imageView)
                //imageView.setImageDrawable(ContextCompat.getDrawable(context, this))
            }else{
                imageView.setImageDrawable(null)
            }
        }
    }

    private fun getCardName(card: Card): String{
        CardHelper.resolveName(card).apply{
            return if(this != CardHelper.EMPTY) getString(this) else ""
        }
    }

    private fun setupGame(){
        cardGame = CardGame(deckCards = CardDecks().getDeck())
        cardGame.start()
        loadCardImage(cardGame.getCurrentCard())
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (gestureDetector.onTouchEvent(event)) true else super.onTouchEvent(event)
    }




    override fun onDown(event: MotionEvent): Boolean {
        return true
    }

    override fun onFling(
        event1: MotionEvent,
        event2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return true
    }

    override fun onScroll(
        event1: MotionEvent,
        event2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        return true
    }

    override fun onDoubleTapEvent(event: MotionEvent): Boolean {
        return true
    }

    override fun onShowPress(event: MotionEvent) {}

    override fun onSingleTapUp(event: MotionEvent): Boolean {
        return true
    }
}








