package com.devchew.kartadrogowa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.devchew.kartadrogowa.logic.CardLogic
import com.devchew.kartadrogowa.logic.OSPanelType
import com.devchew.kartadrogowa.navigation.AppNavHost
import com.devchew.kartadrogowa.ui.components.OSCard
import com.devchew.kartadrogowa.ui.theme.KartaDrogowaTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KartaDrogowaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(navController = rememberNavController())
                }
            }
        }
    }
}


@Preview(showBackground = true, name = "Mainview", showSystemUi = true)
@Composable
fun GreetingPreview() {

    val tempCardData = CardLogic()

    tempCardData.create(
        carNumber = 68,
        name = "Rally Monte Calvaria",
        date = "2021-10-10",
        cardNumber = 4
    )

    tempCardData.addPanel(
        OSPanelType.Start,
        "Start",
        0f
    )

    tempCardData.addPanel(
        OSPanelType.Normal,
        "Słomczyn",
        4.6f
    )

    KartaDrogowaTheme {
        OSCard(tempCardData)
    }
}