package art.com.cardgamedemo.framework

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.speech.tts.TextToSpeech
import android.view.GestureDetector
import androidx.core.view.GestureDetectorCompat
import art.com.cardgamedemo.R
import art.com.cardgamedemo.card_game.CardDecks
import art.com.cardgamedemo.card_game.CardGame
import com.github.pwittchen.swipe.library.rx2.Swipe
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import timber.log.Timber
import java.util.*

val POLISH_LOCALE = "pl-PL"

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin(this, listOf(dependencies))
    }

    private val dependencies = module {
        viewModel { MainViewModel(this@App, get(), get(), get()) }
        single { Timber.DebugTree() }
        single { ResourcesProvider(this@App.resources) }
        factory { CardDecks()}
        factory { CardGame(deckCards = (get() as CardDecks).getDeck())}
        factory { (listener: TextToSpeech.OnInitListener) ->
            TextToSpeechFactory.getTextToSpeech(this@App, listener) }
        factory {(activity: Activity, listener: GestureDetector.OnGestureListener) ->
            GestureDetectorCompat(activity, listener) }
        factory{ Swipe() }
    }
}

object TextToSpeechFactory {
    fun getTextToSpeech(ctx: Context, listener: TextToSpeech.OnInitListener): TextToSpeech {
        val textToSpeech = TextToSpeech(ctx, listener)
        val languageSetResult = textToSpeech.setLanguage(Locale(POLISH_LOCALE))
        if (languageSetResult == TextToSpeech.LANG_MISSING_DATA || languageSetResult == TextToSpeech.LANG_NOT_SUPPORTED) {
            Timber.e(ctx.getString(R.string.polish_locale_unavailable))
        }
        return TextToSpeech(ctx, listener)
    }
}

class ResourcesProvider(private val resources: Resources) {
    fun getString(resId: Int): String = resources.getString(resId)
}
