package com.devchew.kartadrogowa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.devchew.kartadrogowa.database.OSPanelEntity
import com.devchew.kartadrogowa.screens.CardData
import com.devchew.kartadrogowa.screens.OSCard
import com.devchew.kartadrogowa.ui.components.CardHeader
import com.devchew.kartadrogowa.ui.components.OSPanelData
import com.devchew.kartadrogowa.ui.components.OSPanelType
import com.devchew.kartadrogowa.ui.components.TimeStruct
import com.devchew.kartadrogowa.ui.theme.KartaDrogowaTheme



class MainActivity : ComponentActivity() {

    private var tempCardData:CardData = CardData(
        cardNumber = 1,
        name = "Jan Kowalski",
        date = "2021-10-10",
        carNumber = 1,
        panels = listOf(
            OSPanelData(
                pkcType = OSPanelType.Start,
                pkc = 1,
                name = "Start",
                duration = 0f,
                finishTime = TimeStruct(-1,-1,-1,-1),
                finishResult = TimeStruct(-1,-1,-1,-1),
                provisionalStartTime = TimeStruct(-1,-1,-1,-1),
                realStartTime = TimeStruct(-1,-1,-1,-1),
                idealTime = TimeStruct(-1,-1,-1,-1),
                pkcTime = TimeStruct(-1,-1,-1,-1),
                estimatedTime = TimeStruct(-1,-1,-1,-1),
            ),
            OSPanelData(
                pkcType = OSPanelType.Normal,
                pkc = 2,
                name = "PKC 1",
                duration = 0f,
                finishTime = TimeStruct(-1,-1,-1,-1),
                finishResult = TimeStruct(-1,-1,-1,-1),
                provisionalStartTime = TimeStruct(-1,-1,-1,-1),
                realStartTime = TimeStruct(-1,-1,-1,-1),
                idealTime = TimeStruct(-1,-1,-1,-1),
                pkcTime = TimeStruct(-1,-1,-1,-1),
                estimatedTime = TimeStruct(-1,-1,-1,-1),
            ),
            OSPanelData(
                pkcType = OSPanelType.Normal,
                pkc = 3,
                name = "PKC 2",
                duration = 0f,
                finishTime = TimeStruct(-1,-1,-1,-1),
                finishResult = TimeStruct(-1,-1,-1,-1),
                provisionalStartTime = TimeStruct(-1,-1,-1,-1),
                realStartTime = TimeStruct(-1,-1,-1,-1),
                idealTime = TimeStruct(-1,-1,-1,-1),
                pkcTime = TimeStruct(-1,-1,-1,-1),
                estimatedTime = TimeStruct(-1,-1,-1,-1),
            )
        )
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KartaDrogowaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    OSCard(tempCardData)
                }
            }
        }
    }
}


@Preview(showBackground = true, name = "Mainview", showSystemUi = true)
@Composable
fun GreetingPreview() {

    val tempCardData:CardData = CardData(
        cardNumber = 1,
        name = "Jan Kowalski",
        date = "2021-10-10",
        carNumber = 1,
        panels = listOf(
            OSPanelData(
                pkcType = OSPanelType.Start,
                pkc = 1,
                name = "Start",
                duration = 0f,
                finishTime = TimeStruct(-1,-1,-1,-1),
                finishResult = TimeStruct(-1,-1,-1,-1),
                provisionalStartTime = TimeStruct(-1,-1,-1,-1),
                realStartTime = TimeStruct(-1,-1,-1,-1),
                idealTime = TimeStruct(-1,-1,-1,-1),
                pkcTime = TimeStruct(-1,-1,-1,-1),
                estimatedTime = TimeStruct(-1,-1,-1,-1),
            ),
            OSPanelData(
                pkcType = OSPanelType.Normal,
                pkc = 2,
                name = "PKC 1",
                duration = 4.78f,
                finishTime = TimeStruct(-1,-1,-1,-1),
                finishResult = TimeStruct(-1,-1,-1,-1),
                provisionalStartTime = TimeStruct(-1,-1,-1,-1),
                realStartTime = TimeStruct(-1,-1,-1,-1),
                idealTime = TimeStruct(-1,-1,-1,-1),
                pkcTime = TimeStruct(-1,-1,-1,-1),
                estimatedTime = TimeStruct(-1,-1,-1,-1),
            ),
            OSPanelData(
                pkcType = OSPanelType.Normal,
                pkc = 3,
                name = "PKC 2",
                duration = 2.69f,
                finishTime = TimeStruct(-1,-1,-1,-1),
                finishResult = TimeStruct(-1,-1,-1,-1),
                provisionalStartTime = TimeStruct(-1,-1,-1,-1),
                realStartTime = TimeStruct(-1,-1,-1,-1),
                idealTime = TimeStruct(-1,-1,-1,-1),
                pkcTime = TimeStruct(-1,-1,-1,-1),
                estimatedTime = TimeStruct(-1,-1,-1,-1),
            )
        )
    )
    KartaDrogowaTheme {
        OSCard(tempCardData)
    }
}