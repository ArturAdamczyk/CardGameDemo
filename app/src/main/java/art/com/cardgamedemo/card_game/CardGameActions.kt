package art.com.cardgamedemo.card_game

import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

interface CardGameActions {
    fun layOffCard(): Completable
    fun moveCurrentCardLeft(): Completable
    fun moveCurrentCardRight(): Completable
    fun getCurrentCard(): Single<Card>
    fun returnCard(): Completable
    fun getNewCard(): Completable
    fun start(): Completable
    fun restart(deck: Queue<Card>): Completable
}