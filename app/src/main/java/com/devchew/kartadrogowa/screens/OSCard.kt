package com.devchew.kartadrogowa.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devchew.kartadrogowa.database.OSPanelEntity
import com.devchew.kartadrogowa.ui.components.CardHeader
import com.devchew.kartadrogowa.ui.components.OSPanel
import com.devchew.kartadrogowa.ui.components.OSPanelData
import com.devchew.kartadrogowa.ui.components.OSPanelType
import com.devchew.kartadrogowa.ui.components.TimeStruct
import com.devchew.kartadrogowa.ui.theme.KartaDrogowaTheme

data class CardData(
    val cardNumber: Number,
    val name: String,
    val date: String,
    val carNumber: Int,
    val panels: List<OSPanelData>
)

@Composable
fun OSCard(data: CardData) {
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
                OSPanel(panelData = it)
            }
        }
    }
}

@Preview(showBackground = true, name = "OSCard", showSystemUi = true)
@Composable
fun OSCardPreview() {


    val list = listOf(
        OSPanelData(
            pkcType = OSPanelType.Start,
            pkc = 1,
            name = "Start",
            duration = 0f,
            finishTime = TimeStruct(0, 0, 0, 0),
            finishResult = TimeStruct(0, 0, 0, 0),
            provisionalStartTime = TimeStruct(0, 0, 0, 0),
            realStartTime = TimeStruct(0, 0, 0, 0),
            idealTime = TimeStruct(0, 0, 0, 0),
            pkcTime = TimeStruct(0, 0, 0, 0),
            estimatedTime = TimeStruct(0, 0, 0, 0),
        ),
        OSPanelData(
            pkcType = OSPanelType.Normal,
            pkc = 2,
            name = "PKC 1",
            duration = 0f,
            finishTime = TimeStruct(0, 0, 0, 0),
            finishResult = TimeStruct(0, 0, 0, 0),
            provisionalStartTime = TimeStruct(0, 0, 0, 0),
            realStartTime = TimeStruct(0, 0, 0, 0),
            idealTime = TimeStruct(0, 0, 0, 0),
            pkcTime = TimeStruct(0, 0, 0, 0),
            estimatedTime = TimeStruct(0, 0, 0, 0),
        ),
        OSPanelData(
            pkcType = OSPanelType.Normal,
            pkc = 3,
            name = "PKC 2",
            duration = 0f,
            finishTime = TimeStruct(0, 0, 0, 0),
            finishResult = TimeStruct(0, 0, 0, 0),
            provisionalStartTime = TimeStruct(0, 0, 0, 0),
            realStartTime = TimeStruct(0, 0, 0, 0),
            idealTime = TimeStruct(0, 0, 0, 0),
            pkcTime = TimeStruct(0, 0, 0, 0),
            estimatedTime = TimeStruct(0, 0, 0, 0),
        ),
    )


    KartaDrogowaTheme {
        OSCard(
            CardData(
                1,
                "Jan Kowalski",
                "2022-01-01",
                1,
                list
            )
        )
    }
}