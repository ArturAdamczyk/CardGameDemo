package art.com.cardgamedemo.framework

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat
import art.com.cardgamedemo.R
import art.com.cardgamedemo.card_game.*
import com.bumptech.glide.Glide
import com.github.pwittchen.swipe.library.rx2.Swipe
import com.github.pwittchen.swipe.library.rx2.SwipeEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import androidx.lifecycle.Observer
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class MainActivity:
    AppCompatActivity(),
    GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener,
    TextToSpeech.OnInitListener {

    private val gestureDetector: GestureDetectorCompat by inject { parametersOf(this@MainActivity, this@MainActivity) }
    private val textToSpeech: TextToSpeech by inject { parametersOf( this@MainActivity) }
    private val swipe: Swipe by inject()
    private val viewModel: MainViewModel by viewModel()
    private val context: Context = this
    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupGestureDetector()
        setupGame()
        viewModel.currentCard.observe(this, Observer { loadCardImage(it.name) })
        viewModel.message.observe(this, Observer { readTextAloud(it) })
        viewModel.cardMessage.observe(this, Observer { readTextAloud(getCardName(it)) })

        disposable = swipe.observe()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { swipeEvent ->
                when(swipeEvent) {
                    SwipeEvent.SWIPED_LEFT -> run{ viewModel.moveCurrentCardLeft() }
                    SwipeEvent.SWIPED_RIGHT -> run{ viewModel.moveCurrentCardRight() }
                    SwipeEvent.SWIPED_UP -> run{ viewModel.layOffCard() }
                    //SwipeEvent.SWIPED_DOWN -> run{ viewModel.restart() }
                    else -> {}
                }
            }
    }

    override fun onDestroy(){
        super.onDestroy()
        with(textToSpeech) {
            stop()
            shutdown()

        }
        disposable.dispose()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // tts is lazy injected by koin so onInit is not invoked until after first usage of textToSpeech object what results in the necessity to repeat it
            readTextAloud(viewModel.message.value!!)
        }else{
            Timber.e(getString(R.string.tts_unavailable))
        }
    }

    override fun onDoubleTap(event: MotionEvent): Boolean {
        viewModel.getNewCard()
        return true
    }

    override fun onLongPress(event: MotionEvent) {
        viewModel.returnCard()
    }

    override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
        readTextAloud(getCardName(viewModel.currentCard.value!!.name))
        return true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (gestureDetector.onTouchEvent(event)) true else super.onTouchEvent(event)
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        return swipe.dispatchTouchEvent(event) || super.dispatchTouchEvent(event)
    }

    private fun setupGestureDetector(){
        gestureDetector.setOnDoubleTapListener(this)
    }

    private fun readTextAloud(msg: String) {
        textToSpeech.speak(msg, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    private fun loadCardImage(cardType: String){
        CardHelper.resolveDrawable(cardType).apply {
            if (this != CardDecks.EMPTY)  Glide.with(context).load(this).into(imageView)
            else imageView.setImageDrawable(null)
        }
    }

    private fun getCardName(cardType: String): String{
        CardHelper.resolveName(cardType).apply{
            return if(this != CardHelper.EMPTY) getString(this) else ""
        }
    }

    private fun setupGame(){
        viewModel.start()
    }






    override fun onDown(event: MotionEvent): Boolean = true

    override fun onFling(event1: MotionEvent, event2: MotionEvent, velocityX: Float, velocityY: Float): Boolean = true

    override fun onScroll(event1: MotionEvent, event2: MotionEvent, distanceX: Float, distanceY: Float): Boolean = true

    override fun onDoubleTapEvent(event: MotionEvent): Boolean = true

    override fun onShowPress(event: MotionEvent) {}

    override fun onSingleTapUp(event: MotionEvent): Boolean = true
}








