package com.devchew.kartadrogowa.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.devchew.kartadrogowa.database.Panel
import com.devchew.kartadrogowa.logic.MainViewModel
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