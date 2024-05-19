package com.devchew.kartadrogowa.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.devchew.kartadrogowa.logic.OSPanelType

@Composable
fun PanelAddModal(
    onConfirmation: (type: OSPanelType, name: String, duration: Float) -> Unit
) {
    val openAlertDialog = remember { mutableStateOf(false) }

    val name = remember { mutableStateOf("") }
    val type = remember { mutableStateOf(OSPanelType.Normal) }
    val duration = remember { mutableFloatStateOf(0f) }
    val dropdownMenuState = remember { mutableStateOf(false) }

    when {
        !openAlertDialog.value -> Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                onClick = { openAlertDialog.value = true },
                modifier = Modifier.padding(8.dp),
            ) {
                Text("+")
            }
        }
        openAlertDialog.value -> Dialog(onDismissRequest = { openAlertDialog.value = false }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(375.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Dodaj PKC",
                        modifier = Modifier.padding(16.dp),
                    )
                    TextField(
                        value = name.value,
                        onValueChange = { text -> name.value = text },
                        label = { Text("Nazwa PKC") }
                    )
                    TextField(
                        value = duration.floatValue.toString(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        onValueChange = { text -> duration.floatValue = text.toFloat() },
                        label = { Text("Długość PKC") }
                    )
                    DropdownMenu(
                        expanded = dropdownMenuState.value,
                        onDismissRequest = { type.value = OSPanelType.Normal },
                    ) {
                        TextButton(onClick = { type.value = OSPanelType.Start }) {
                            Text("Start")
                        }
                        TextButton(onClick = { type.value = OSPanelType.Normal }) {
                            Text("Normalny")
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        TextButton(
                            onClick = { openAlertDialog.value = false },
                            modifier = Modifier.padding(8.dp),
                        ) {
                            Text("Anuluj")
                        }
                        TextButton(
                            onClick = {
                                onConfirmation(
                                    type.value,
                                    name.value,
                                    duration.floatValue
                                )
                                openAlertDialog.value = false
                            },
                            modifier = Modifier.padding(8.dp),
                        ) {
                            Text("Potwierdź")
                        }
                    }
                }
            }
        }

    }

}