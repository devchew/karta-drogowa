package com.devchew.kartadrogowa.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.devchew.kartadrogowa.database.Panel
import com.devchew.kartadrogowa.models.MainViewModel


@Composable
fun OSCard(
    cardId: Int, viewModel: MainViewModel,
) {
    val card by viewModel.card.collectAsState()
    val panels by viewModel.panels.collectAsState()

    Column (
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .background(Color.White)
            .padding(5.dp)
    ) {
        CardHeader(
            card.carNumber,
            card.name,
            card.date,
            card.cardNumber
        )
        Column (
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(weight = 1f, fill = false)
        ) {
            panels.forEach {
                OSPanel(it, viewModel)
            }
            PanelAddModal(
                starting = panels.isEmpty(),
                onConfirmation = { type, name, duration ->
                    viewModel.onAddPanel(
                        panel = Panel(
                            pkcType = type,
                            name = name,
                            duration = duration,
                            cardId = cardId,
                            pkc = panels.size,
                        ),
                        callback = {
                            viewModel.loadCard(cardId)
                        }
                    )
                }
            )
        }
    }
}
