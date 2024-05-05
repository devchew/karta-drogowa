package com.devchew.kartadrogowa.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devchew.kartadrogowa.ui.theme.KartaDrogowaTheme

@Composable
fun Strip(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .width(24.dp)
            .fillMaxHeight()
            .background(color = Color(0xFF000000))
            .padding(start = 2.dp, top = 5.dp, end = 2.dp, bottom = 5.dp)
            .then(modifier)
    ) {
        Text(
            text = "PKC\n1",
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier
                .width(20.dp)
                .height(24.dp)
        )
        Text(
            text = "PKC\n2",
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier
                .width(20.dp)
                .height(24.dp)
        )
    }
}

@Composable
fun Details(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .border(width = 1.dp, color = Color.Black)
            .fillMaxHeight()
            .padding(start = 4.dp, top = 5.dp, end = 4.dp, bottom = 5.dp)
            .then(modifier)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "PS 1 Targ 1\n1,45 km",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                ),
                modifier = Modifier
                    .width(52.dp)
                    .height(24.dp)
            )
            InputGroup(
                name = "Czas mety",
                description = null,
                h = 4,
                m = 5,
                s = 56,
                tenths = 58
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            InputGroup(
                name = "Prowizoryczny\nczas startu(+3min)",
                description = null,
                h = 4,
                m = 5
            )
            Icon(
                Icons.Default.PlayArrow,
                contentDescription = "going to",
                modifier = Modifier.offset((-4).dp, 22.dp)
            )
            InputGroup(
                name = "Rzeczywisty\nczas startu",
                description = null,
                gray = true,
                h = 4,
                m = 5
            )
            Icon(
                Icons.Rounded.CheckCircle,
                contentDescription = "Finish",
                modifier = Modifier.offset(0.dp, 22.dp)
            )
            Icon(
                Icons.Default.PlayArrow,
                contentDescription = "going to",
                modifier = Modifier.offset((-4).dp, 22.dp)
            )
            InputGroup(
                name = "Idealny\nczas przejazdu",
                description = null,
                h = 4,
                m = 5
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            InputGroup(
                name = null,
                description = null,
                h = null,
                m = 5,
                s = 45,
                tenths = 67
            )
            InputGroup(
                name = null,
                description = "PKC3",
                gray = true,
                h = 4,
                m = 5
            )
        }
    }
}

@Composable
fun NextOS(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .background(color = Color.LightGray)
            .border(width = 1.dp, color = Color.Black)
            .padding(3.dp)
            .width(60.dp)
            .fillMaxHeight()
            .then(modifier)
    ) {
        InputGroup(
            name = "Przewidywany\nczas przejazdu",
            description = "PKC 1",
            h = 4,
            m = 5
        )
    }
}

@Composable
fun OSPanel(modifier: Modifier = Modifier) {
    val baseModifier: Modifier = Modifier
        .fillMaxWidth()
        .height(170.dp)

    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = baseModifier.then(modifier)
    ) {
        Strip()
        Details(Modifier.weight(1f))
        NextOS()
    }
}

@Preview(showBackground = true, name = "CardHeader", showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun PreviewOSPanel() {
    KartaDrogowaTheme {
        OSPanel()
    }
}