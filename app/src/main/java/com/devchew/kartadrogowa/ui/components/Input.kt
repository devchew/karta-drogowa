package com.devchew.kartadrogowa.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
fun InputBox(prefix: String, value: Number, gray: Boolean = false) {

    var background: Color = Color.White

    if (gray) {
        background = Color.LightGray
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .border(width = 1.dp, color = Color.Black)
            .width(30.dp)
            .height(30.dp)
            .background(color = background)
    ) {
        Text(
            text = prefix,
            style = TextStyle(
                fontSize = 6.sp,
                fontWeight = FontWeight(800),
                color = Color.Black,
            ),
            modifier = Modifier
                .offset(3.dp, 1.dp)
        )
        Text(
            text = value.toString().padStart(2, '0'),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = Color.Black,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = -2.dp)
        )

    }
}

@Composable
fun InputGroup(
    name: String?,
    description: String?,
    h: Number? = null,
    m: Number? = null,
    s: Number? = null,
    tenths: Number? = null,
    gray: Boolean = false
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(-1.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (!name.isNullOrBlank()) {
            Text(
                text = name,
                style = TextStyle(
                    fontSize = 8.sp,
                    fontWeight = FontWeight(400),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .padding(bottom = 2.dp)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(-1.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (h != null) {
                InputBox("H", h, gray)
            }
            if (m != null) {
                InputBox("M", m, gray)
            }
            if (s != null) {
                InputBox("S", s, gray)
            }
            if (tenths != null) {
                InputBox("1/10", tenths, gray)
            }
        }
        if (!description.isNullOrBlank()) {
            Text(
                text = description,
                style = TextStyle(
                    fontSize = 8.sp,
                    fontWeight = FontWeight(400),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .padding(bottom = 2.dp)
            )
        }
    }
}

@Composable
@Preview
fun InputGroupPreview() {
    KartaDrogowaTheme {
        InputGroup(
            "test",
            null,
            4,
            13
        )
    }
}