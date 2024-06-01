package com.devchew.kartadrogowa.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.devchew.kartadrogowa.models.MainViewModel
import com.devchew.kartadrogowa.ui.components.OSCard

@Composable
fun CardPage(cardId: Int, viewModel: MainViewModel) {
    viewModel.loadCard(cardId)

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column {
            OSCard(cardId, viewModel)
        }
    }
}