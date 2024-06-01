package com.devchew.kartadrogowa.logic

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.util.UUID

class ListLogic() {
    var cards: MutableState<List<CardDescription>> = mutableStateOf(listOf())
    var initialized = mutableStateOf(false)

    init {
        // Load some initial data
        loadCards(
            listOf(
                CardDescription(1, "Karta 1", "01.01.2021", 1, UUID.randomUUID().toString()),
                CardDescription(2, "Karta 2", "02.02.2021", 2, UUID.randomUUID().toString()),
                CardDescription(3, "Karta 3", "03.03.2021", 3, UUID.randomUUID().toString())
            )
        )
    }

    fun loadCards(cards: List<CardDescription>) {
        this.cards.value = cards
        this.initialized.value = true
    }

    fun addCard(
        cardNumber: Number,
        name: String,
        date: String,
        carNumber: Int
    ) {
        val uid = UUID.randomUUID().toString()
        val newCardList = cards.value.toMutableList()
        newCardList.add(CardDescription(cardNumber, name, date, carNumber, uid))
        cards.value = newCardList
    }
}