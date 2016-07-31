package org.t4atf.kotdroid.cards.loader

import org.t4atf.kotdroid.cards.Card
import org.t4atf.kotdroid.cards.Cards

interface DroidReader {

    fun <T : Card> readCards(): Cards<T>
}
