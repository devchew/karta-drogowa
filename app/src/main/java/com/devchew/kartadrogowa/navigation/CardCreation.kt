package com.devchew.kartadrogowa.navigation

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.devchew.kartadrogowa.database.Card
import com.devchew.kartadrogowa.models.MainViewModel

@Composable
fun CardCreation(navController: NavHostController, viewModel: MainViewModel) {
    val name = remember { mutableStateOf("") }
    val cardNumber = remember { mutableStateOf("") }
    val carNumber = remember { mutableStateOf("") }
    val date = remember { mutableStateOf(SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis())) }
    val saveing = remember { mutableStateOf(false) }

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
            value = cardNumber.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { text -> cardNumber.value = text },
            label = { Text("Numer karty") }
        )
        TextField(
            value = carNumber.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { text -> carNumber.value = text },
            label = { Text("Numer startowy") }
        )
        TextField(
            value = date.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { text -> date.value = text },
            label = { Text("Data") }
        )


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
                    saveing.value = true
                    viewModel.createCard(
                        Card(
                            name = name.value,
                            cardNumber = cardNumber.value.toInt(),
                            date = date.value,
                            carNumber = carNumber.value.toInt(),
                        ),
                        callback = { id ->
                            navController.navigate(NavigationItem.Card.route + "/${id}")
                        }
                    )
                },
                modifier = Modifier.padding(8.dp),
            ) {
                when {
                    saveing.value -> Text("Zapisywanie...")
                    else -> Text("Potwierdź")
                    }
            }
        }
    }

}