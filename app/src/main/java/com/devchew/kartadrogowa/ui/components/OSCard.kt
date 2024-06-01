package com.devchew.kartadrogowa.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devchew.kartadrogowa.logic.CardData
import com.devchew.kartadrogowa.logic.CardLogic
import com.devchew.kartadrogowa.logic.OSPanelType
import com.devchew.kartadrogowa.ui.theme.KartaDrogowaTheme


@Composable
fun OSCard(
    card: CardLogic
) {
    val data = card.getCardData()
    Column (
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .background(Color.White)
            .padding(5.dp)
    ) {
        CardHeader(
            data.carNumber,
            data.name,
            data.date,
            data.cardNumber
        )
        Column (
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(weight = 1f, fill = false)
        ) {
            data.panels.forEach {
                OSPanel(it)
            }
            PanelAddModal(
                starting = data.panels.isEmpty(),
                onConfirmation = { type, name, duration->
                    card.addPanel(
                        type,
                        name,
                        duration
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true, name = "OSCard", showSystemUi = true)
@Composable
fun OSCardPreview() {
    val tempCardData = CardLogic()

    tempCardData.create(
        carNumber = 68,
        name = "Rally Monte Calvaria",
        date = "2021-10-10",
        cardNumber = 4
    )

    tempCardData.addPanel(
        OSPanelType.Start,
        "Start",
        0f
    )

    tempCardData.addPanel(
        OSPanelType.Normal,
        "Radom",
        11.67f
    )

    KartaDrogowaTheme {
        OSCard(
            tempCardData
        )
    }
}