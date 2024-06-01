package com.devchew.kartadrogowa.navigation

enum class Screen {
    List,
    Card,
    Timer,
    CreateCard
}
sealed class NavigationItem(val route: String, val label: String) {
    object List : NavigationItem(Screen.List.name, "Lista kart")
    object Card : NavigationItem(Screen.Card.name, "Karta")
    object Timer : NavigationItem(Screen.Timer.name, "Stoper")
    object CreateCard : NavigationItem(Screen.CreateCard.name, "Nowa karta")
}