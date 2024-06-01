package com.devchew.kartadrogowa.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.devchew.kartadrogowa.models.MainViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardsList(navController: NavHostController, viewModel: MainViewModel) {
    val list by viewModel.cardList.collectAsState()


    viewModel.updateCards()

    LazyColumn {
        items(list.size) { index ->
            ListItem(
                modifier = Modifier.clickable { navController.navigate(NavigationItem.Card.route + "/" + list[index].id) },
                text = {
                    Text(text = list[index].name + " [" + list[index].cardNumber.toString() + "]")
                },
                secondaryText = {
                    Text(text = list[index].date)
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
