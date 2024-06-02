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
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.devchew.kartadrogowa.database.OSPanelType


@Composable
fun PanelAddModal(
    starting: Boolean = false,
    onConfirmation: (type: OSPanelType, name: String, duration: Float) -> Unit
) {
    val openAlertDialog = remember { mutableStateOf(false) }

    val name = remember { mutableStateOf("") }
    val type = mutableStateOf(OSPanelType.Normal)
    val duration = remember { mutableStateOf("") }

    if (starting) {
        type.value = OSPanelType.Start
    }

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
                        label = { Text("Nazwa PKC") },
                    )
                    TextField(
                        value = duration.value,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = { text -> duration.value = text },
                        label = { Text("Długość PKC") },
                        isError = duration.value.isNotEmpty() && duration.value.toFloatOrNull() == null
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            "Startowy PKC",
                            modifier = Modifier.padding(10.dp),
                        )
                        Switch(checked = type.value == OSPanelType.Start, onCheckedChange = {
                            type.value = if (it) OSPanelType.Start else OSPanelType.Normal
                        })
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
                                    duration.value.toFloatOrNull() ?: 0f
                                )
                                openAlertDialog.value = false
                            },
                            enabled = duration.value.isEmpty() || duration.value.toFloatOrNull() != null,
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

@Preview(showBackground = true, name = "PanelAddModal", showSystemUi = true)
@Composable
fun PreviewPanelAddModal() {
    PanelAddModal { _, _, _ -> }
}