package com.devchew.kartadrogowa.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.devchew.kartadrogowa.models.MainViewModel
import com.devchew.kartadrogowa.ui.components.OSCard

@Composable
fun CardPage(navController: NavHostController, cardId: Int, viewModel: MainViewModel) {
    viewModel.loadCard(cardId)
    val closestTimer by viewModel.closestTimer.collectAsState()

    Scaffold(

        floatingActionButton = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                FloatingActionButton(
                    onClick = { navController.navigate(NavigationItem.List.route) },
                ) {
                    Icon(Icons.Filled.Home, "Home")
                }
                FloatingActionButton(
                    onClick = { navController.navigate(NavigationItem.Timer.route + "/" + cardId + "/" + closestTimer) },
                ) {
                    Icon(Icons.Default.DateRange, "Timer")
                }
            }
        }

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column {
                OSCard(cardId, viewModel)
            }
        }
    }
}