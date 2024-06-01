package com.devchew.kartadrogowa.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.devchew.kartadrogowa.logic.CardData
import com.devchew.kartadrogowa.logic.CardLogic
import com.devchew.kartadrogowa.ui.components.CardInitializationModal
import com.devchew.kartadrogowa.ui.components.OSCard

@Composable
fun CardPage(cardId: String) {

    val cardData = remember { CardLogic() }

    cardData.loadCardData(CardData(
        carNumber = 68,
        name = "Rally Monte Calvaria",
        date = "2021-10-10",
        cardNumber = 4,
        panels = listOf()
    ))

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        when {
            cardData.initialized.value ->
                Column {
                    OSCard(cardData)
                }
            !cardData.initialized.value ->
                CardInitializationModal(
                    onConfirmation = { name, date, carNumber, cardNumber ->
                        cardData.create(
                            carNumber = carNumber,
                            name = name,
                            date = date,
                            cardNumber = cardNumber
                        )
                    }
                )
        }
    }
}