package com.devchew.kartadrogowa.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devchew.kartadrogowa.models.MainViewModel
import com.devchew.kartadrogowa.models.PanelModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OSPanel(
    panel: PanelModel,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier,
) {

    val dialogVisible = remember {
        mutableStateOf(false)
    }
    var dialogCallback: () -> Unit = {}
    Surface {
        when {
            dialogVisible.value -> {
                ConfirmationDialog(
                    onDismissRequest = { dialogVisible.value = false },
                    onConfirmation = {
                        dialogCallback()
                        dialogVisible.value = false
                    },
                    dialogTitle = "Usuwanie Panelu",
                    dialogText = "Czy na pewno chcesz usunąć ten panel?",
                    icon = Icons.Default.Delete
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .then(modifier)
                .combinedClickable(
                    onClick = { },
                    onLongClick = {
                        dialogCallback = {
                            viewModel.removePanel(
                                panel.id,
                                callback = {
                                    viewModel.updateCards()
                                }
                            )
                        }
                        dialogVisible.value = true
                    },
                    onLongClickLabel = "Long press to edit panel"
                ),

            ) {
            Strip(pkc = panel.pkc.value)
            Details(
                panel = panel,
                modifier = Modifier.weight(1f)
            )
            NextOS(
                panel = panel,
            )
        }
    }
}