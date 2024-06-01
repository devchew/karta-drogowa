package com.devchew.kartadrogowa.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.List.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Card.route + "/{cardId}") {backStackEntry ->
            val cardId = backStackEntry.arguments?.getString("cardId")
            if (cardId == null) {
                navController.navigate(NavigationItem.CreateCard.route)
                return@composable
            }
            CardPage(cardId)
        }
        composable(NavigationItem.Timer.route + "/{until}") { backStackEntry ->
            val until = backStackEntry.arguments?.getInt("until")
            TimerPage(until)
        }
        composable(NavigationItem.List.route) {
            CardsList(navController)
        }
        composable(NavigationItem.CreateCard.route) {
            CardCreation(navController)
        }
    }
}