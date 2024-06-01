package com.devchew.kartadrogowa.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devchew.kartadrogowa.logic.MainViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.List.route,
    viewModel: MainViewModel,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(NavigationItem.Card.route + "/{cardId}") {backStackEntry ->
            val cardId = backStackEntry.arguments?.getString("cardId")?.toInt()
            if (cardId == null) {
                navController.navigate(NavigationItem.CreateCard.route)
                return@composable
            }
            CardPage(cardId, viewModel)
        }
        composable(NavigationItem.Timer.route + "/{until}") { backStackEntry ->
            val until = backStackEntry.arguments?.getInt("until")
            TimerPage(until)
        }
        composable(NavigationItem.List.route) {
            CardsList(navController, viewModel)
        }
        composable(NavigationItem.CreateCard.route) {
            CardCreation(navController, viewModel)
        }
    }
}