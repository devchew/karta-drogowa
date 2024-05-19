package com.devchew.kartadrogowa.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun Strip(
    pkc: Int,
    modifier: Modifier = Modifier
) {

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
            text = "PKC\n$pkc",
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
            text = "PKC\n${(pkc + 1)}",
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