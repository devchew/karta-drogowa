package com.devchew.kartadrogowa.logic

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf


data class CardData(
    val cardNumber: Number,
    val name: String,
    val date: String,
    val carNumber: Int,
    val panels: List<PanelLogic>
)

data class CardDescription(
    val cardNumber: Number,
    val name: String,
    val date: String,
    val carNumber: Int,
    val id: String
)

class CardLogic() {
    val id: String = java.util.UUID.randomUUID().toString()
    private var cardNumber: Number = 0
    private var name: String = ""
    private var date: String = ""
    private var carNumber: Int = 0
    private var panels: MutableState<List<PanelLogic>> = mutableStateOf(listOf())
    var initialized = mutableStateOf(false)

    fun loadCardData(cardData: CardData) {
        this.cardNumber = cardData.cardNumber
        this.name = cardData.name
        this.date = cardData.date
        this.carNumber = cardData.carNumber
        this.panels.value = cardData.panels
        this.initialized.value = true
    }

    fun create(
        cardNumber: Number,
        name: String,
        date: String,
        carNumber: Int
    ) {
        this.cardNumber = cardNumber
        this.name = name
        this.date = date
        this.carNumber = carNumber
        this.initialized.value = true
    }

    // Add panel to the list of panels and return panel class
    fun addPanel(
        type: OSPanelType,
        psName: String,
        duration: Float
    ) {
        val newPanelList = panels.value.toMutableList()
        newPanelList.add(PanelLogic(type, newPanelList.size, psName, duration))

        // bind finish callback from previous panel
        if (newPanelList.size > 1) {
            val prevPanel = newPanelList[newPanelList.size - 2]
            val currentPanel = newPanelList[newPanelList.size - 1]
            prevPanel.finishCallback = {
                currentPanel.provisionalStartTimeChange(
                    sumTime(listOf(prevPanel.pkcTime.value, TimeStruct(0,3,0,0)))
                )
            }
        }

        panels.value = newPanelList
    }

    fun getCardData(): CardData {
        return CardData(
            cardNumber = cardNumber,
            name = name,
            date = date,
            carNumber = carNumber,
            panels = panels.value
        )
    }


}