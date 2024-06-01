package com.devchew.kartadrogowa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.devchew.kartadrogowa.database.Database
import com.devchew.kartadrogowa.models.MainViewModel
import com.devchew.kartadrogowa.navigation.AppNavHost
import com.devchew.kartadrogowa.ui.theme.KartaDrogowaTheme


class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            Database::class.java,
            "kartadrogowa.db"
        ).build()
    }

    private val mainViewModelFactory by viewModels<MainViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MainViewModel(db.cardDao(), db.panelDao()) as T
                }
            }
        }
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
                    AppNavHost(
                        navController = rememberNavController(),
                        viewModel = mainViewModelFactory,
                    )
                }
            }
        }
    }
}

//
//@Preview(showBackground = true, name = "Mainview", showSystemUi = true)
//@Composable
//fun GreetingPreview() {
//
//    val tempCardData = CardLogic()
//
//    tempCardData.create(
//        carNumber = 68,
//        name = "Rally Monte Calvaria",
//        date = "2021-10-10",
//        cardNumber = 4
//    )
//
//    tempCardData.addPanel(
//        OSPanelType.Start,
//        "Start",
//        0f
//    )
//
//    tempCardData.addPanel(
//        OSPanelType.Normal,
//        "Słomczyn",
//        4.6f
//    )
//
//    KartaDrogowaTheme {
//        OSCard(tempCardData)
//    }
//}