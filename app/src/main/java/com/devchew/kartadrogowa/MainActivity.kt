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
import com.devchew.kartadrogowa.screens.OSCard
import com.devchew.kartadrogowa.ui.components.CardHeader
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
                    OSCard()
                }
            }
        }
    }
}


@Preview(showBackground = true, name = "Mainview", showSystemUi = true)
@Composable
fun GreetingPreview() {
    KartaDrogowaTheme {
        OSCard()
    }
}