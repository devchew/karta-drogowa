package com.devchew.kartadrogowa.logic


data class CardData(
    val cardNumber: Number,
    val name: String,
    val date: String,
    val carNumber: Int,
    val panels: List<PanelLogic>
)

class CardLogic(
    private val cardNumber: Number,
    private val name: String,
    private val date: String,
    private val carNumber: Int
) {
    var panels: List<PanelLogic> = listOf()


    // Add panel to the list of panels and return panel class
    fun addPanel(
        type: OSPanelType,
        psName: String,
        duration: Float
    ) {
        panels = panels + PanelLogic(type, panels.size, psName, duration)

        // bind finish callback from previous panel
        if (panels.size > 1) {
            val prevPanel = panels[panels.size - 2]
            val currentPanel = panels[panels.size - 1]
            prevPanel.finishCallback = {
                currentPanel.provisionalStartTimeChange(
                    sumTime(listOf(prevPanel.pkcTime.value, TimeStruct(0,3,0,0)))
                )
            }
        }
    }

    fun getCardData(): CardData {
        return CardData(
            cardNumber = cardNumber,
            name = name,
            date = date,
            carNumber = carNumber,
            panels = panels
        )
    }


}