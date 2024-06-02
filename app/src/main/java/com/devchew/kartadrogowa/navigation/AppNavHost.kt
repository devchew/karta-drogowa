package com.devchew.kartadrogowa.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devchew.kartadrogowa.models.MainViewModel

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
        composable(
            NavigationItem.Card.route + "/{cardId}"
        ) { backStackEntry ->
            val cardId = backStackEntry.arguments?.getString("cardId")?.toInt()
            if (cardId == null) {
                navController.navigate(NavigationItem.CreateCard.route)
                return@composable
            }
            CardPage(navController, cardId, viewModel)
        }
        composable(
            NavigationItem.Timer.route + "/{cardId}/{until}",
            arguments = listOf(
                navArgument("cardId") { type = NavType.IntType },
                navArgument("until") { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val until = backStackEntry.arguments?.getLong("until") ?: 0L
            val cardId = backStackEntry.arguments?.getInt("cardId") ?: 0
            TimerPage(navController, until, cardId)
        }
        composable(NavigationItem.List.route) {

            CardsList(navController, viewModel)


        }
        composable(NavigationItem.CreateCard.route) {
            CardCreation(navController, viewModel)
        }
    }
}