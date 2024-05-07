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
import com.devchew.kartadrogowa.ui.components.CardHeader
import com.devchew.kartadrogowa.ui.components.OSPanel
import com.devchew.kartadrogowa.ui.theme.KartaDrogowaTheme

@Composable
fun OSCard() {
    Column (
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .background(Color.White)
            .padding(5.dp)
    ) {
        CardHeader(69, "Karta drogowa 1\nRally monte calvaria\n17.02.2024")
        Column (
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(weight = 1f, fill = false)
        ) {
            for (i in 1..3) {
                OSPanel()
            }
        }
    }
}

@Preview(showBackground = true, name = "OSCard", showSystemUi = true)
@Composable
fun OSCardPreview() {
    KartaDrogowaTheme {
        OSCard()
    }
}