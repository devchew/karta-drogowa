package com.devchew.kartadrogowa.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.devchew.kartadrogowa.logic.ListLogic
import com.devchew.kartadrogowa.ui.components.PanelAddModal


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardsList(navController: NavHostController) {
    val list = ListLogic();


    LazyColumn {
        items(list.cards.value.size) { index ->
            ListItem(
                modifier = Modifier.clickable { navController.navigate(NavigationItem.Card.route + "/" + list.cards.value[index].id) },
                text = {
                    Text(text = list.cards.value[index].name + " [" + list.cards.value[index].cardNumber.toString() + "]")
                },
                secondaryText = {
                    Text(text = list.cards.value[index].date)
                }
            )
        }
        item {
            ListItem(
                modifier = Modifier.clickable { navController.navigate(NavigationItem.CreateCard.route) },
                text = {
                    Text(text = "Dodaj nową kartę")
                },
            )
        }
    }

}
