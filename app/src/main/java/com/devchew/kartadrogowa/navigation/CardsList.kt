package com.devchew.kartadrogowa.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.devchew.kartadrogowa.models.MainViewModel
import com.devchew.kartadrogowa.ui.components.ConfirmationDialog


@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun CardsList(navController: NavHostController, viewModel: MainViewModel) {
    val list by viewModel.cardList.collectAsState()
    val dialogVisible = mutableStateOf(false)
    var dialogCallback: () -> Unit = {}

    viewModel.updateCards()
    Surface {
        when {
            dialogVisible.value -> {
                ConfirmationDialog(
                    onDismissRequest = { dialogVisible.value = false },
                    onConfirmation = {
                        dialogCallback()
                        dialogVisible.value = false
                    },
                    dialogTitle = "Usuwanie karty",
                    dialogText = "Czy na pewno chcesz usunąć tę kartę?",
                    icon = Icons.Default.Delete
                )
            }
        }
        LazyColumn {
            items(list.size) { index ->
                ListItem(
                    modifier = Modifier
                        .combinedClickable(
                            onClick = { navController.navigate(NavigationItem.Card.route + "/" + list[index].id) },
                            onLongClick = {
                                dialogCallback = {
                                    viewModel.removeCard(
                                        list[index].id,
                                        callback = {
                                            viewModel.updateCards()
                                        }
                                    )
                                }
                                dialogVisible.value = true
                            },
                            onLongClickLabel = "Long press to edit panel"
                        ),
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
}
