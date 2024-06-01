package com.devchew.kartadrogowa.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.devchew.kartadrogowa.logic.CardLogic
import com.devchew.kartadrogowa.logic.OSPanelType

@Composable
fun CardCreation(navController: NavHostController) {
    val name = remember { mutableStateOf("") }
    val type = remember { mutableStateOf(OSPanelType.Normal) }
    val duration = remember { mutableFloatStateOf(0f) }
    val cardNumber = remember { mutableIntStateOf(0) }
    val carNumber = remember { mutableIntStateOf(0) }
    val date = remember { mutableStateOf("") }

    val cardLogic = remember { CardLogic() }

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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { text -> duration.floatValue = text.toFloat() },
            label = { Text("Długość PKC") }
        )
        TextField(
            value = cardNumber.intValue.toString(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { text -> cardNumber.intValue = text.toInt() },
            label = { Text("Numer karty") }
        )
        TextField(
            value = carNumber.intValue.toString(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { text -> carNumber.intValue = text.toInt() },
            label = { Text("Numer startowy") }
        )
        TextField(
            value = date.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { text -> date.value = text },
            label = { Text("Numer karty") }
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
                onClick = { navController.navigate(NavigationItem.List.route) },
                modifier = Modifier.padding(8.dp),
            ) {
                Text("Anuluj")
            }
            TextButton(
                onClick = {
                    cardLogic.addPanel(
                        type = type.value,
                        psName = name.value,
                        duration = duration.value
                    )
                    navController.navigate(NavigationItem.Card.route + "/${cardLogic.id}")
                },
                modifier = Modifier.padding(8.dp),
            ) {
                Text("Potwierdź")
            }
        }
    }

}