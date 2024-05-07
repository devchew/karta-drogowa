package com.devchew.kartadrogowa.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devchew.kartadrogowa.R
import com.devchew.kartadrogowa.ui.theme.KartaDrogowaTheme

@Composable
fun CardHeader(carNumber: Number, description: String, modifier: Modifier = Modifier) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .border(width = 1.dp, color = Color(0xFF000000))
            .fillMaxWidth()
            .background(color = Color(0xFFFFFFFF))
            .padding(5.dp)
            .then(modifier)
    ) {
        Image(
            painter = painterResource(id = R.drawable.calvaria_logo),
            contentDescription = "image description",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .width(68.dp)
                .height(35.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .border(width = 1.dp, color = Color(0xFF000000))
                .width(49.dp)
                .height(36.dp)
                .padding(start = 2.dp, top = 1.dp, end = 2.dp, bottom = 1.dp)
        ) {
            Text(
                text = "Numer auta",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp),
                style = TextStyle(
                    fontSize = 8.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                )
            )
            Text(
                text = carNumber.toString().padStart(2, '0'),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                )
            )
        }
        Text(
            text = description,
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF000000),
            )
        )
        Image(
            painter = painterResource(id = R.drawable.pzm_logo),
            contentDescription = "image description",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .width(35.dp)
                .height(35.dp)
        )
    }
}

@Preview(showBackground = true, name = "CardHeader", showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun Preview() {
    KartaDrogowaTheme {
        CardHeader(69, "Karta drogowa 1\nRally monte calvaria\n17.02.2024")
    }
}