package com.devchew.kartadrogowa.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devchew.kartadrogowa.database.OSPanelType
import com.devchew.kartadrogowa.database.Panel
import com.devchew.kartadrogowa.models.PanelModel

@Composable
fun NextOS(
    panel: PanelModel,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .background(color = Color.LightGray)
            .border(width = 1.dp, color = Color.Black)
            .padding(3.dp)
            .width(60.dp)
            .fillMaxHeight()
            .then(modifier)
    ) {
        InputGroup(
            name = "Przewidywany\nczas przejazdu",
            description = "PKC 1",
            h = panel.estimatedTime.value.h,
            m = panel.estimatedTime.value.m,
            onValueChange = { time -> panel.estimatedTimeChange(time)},
            beforeChane = { panel.estimatedTimeGetSuggested() },
            modifier = Modifier
                .padding(0.dp, 13.dp, 0.dp, 0.dp)
        )
    }
}

@Preview
@Composable
fun NextOSPreview() {
    NextOS(
        panel = PanelModel(
            Panel(
                pkcType = OSPanelType.Normal,
                name = "PKC 1",
                duration = 0f,
                cardId = 0,
                pkc = 0
            )
        )
    )
}