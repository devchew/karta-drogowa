package com.devchew.kartadrogowa.ui.components

import android.text.format.DateFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import java.util.Date

@Composable
fun CardInitializationModal(
    onConfirmation: (name: String, date: String, carNumber: Int, cardNumber: Int) -> Unit
) {
    val openAlertDialog = remember { mutableStateOf(false) }

    val name = remember { mutableStateOf("") }
    val date = remember { mutableStateOf(DateFormat.format("yyyy-MM-dd", Date()) as String) }
    val cardNumber = remember { mutableStateOf(1) }
    val carNumber = remember { mutableStateOf(68) }

    when {
        !openAlertDialog.value -> Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = { openAlertDialog.value = true },
                modifier = Modifier.padding(8.dp),
            ) {
                Text("Stwórz kartę")
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
                        text = "Wypełnij dane karty",
                        modifier = Modifier.padding(16.dp),
                    )
                    TextField(
                        value = name.value,
                        onValueChange = { text -> name.value = text },
                        label = { Text("Nazwa imprezy") }
                    )
                    TextField(
                        value = date.value,
                        onValueChange = { text -> date.value = text },
                        label = { Text("Data") }
                    )
                    TextField(
                        value = carNumber.value.toString(),
                        onValueChange = { text -> carNumber.value = text.toInt() },
                        label = { Text("Numer samochodu") }
                    )
                    TextField(
                        value = cardNumber.value.toString(),
                        onValueChange = { text -> cardNumber.value = text.toInt() },
                        label = { Text("Numer karty") }
                    )

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
                                    name.value,
                                    date.value,
                                    carNumber.value,
                                    cardNumber.value
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