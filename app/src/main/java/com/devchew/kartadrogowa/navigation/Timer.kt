package com.devchew.kartadrogowa.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.devchew.kartadrogowa.models.TimeStruct
import com.devchew.kartadrogowa.models.getCurrentTime
import kotlinx.coroutines.delay


fun reamingTimeToGradient(remainingTime: Long): Array<Pair<Float, Color>> {
    val yellowTimeStart = 5 * 60 * 1000
    val yellowTimeEnd = 2 * 60 * 1000
    val redTimeStart = 2 * 60 * 1000
    val retTimeEnd = 1 * 60 * 1000


    val green = Color.Green
    val yellow = Color.Yellow
    val red = Color.Red

    val yellowPercent =
        (remainingTime - yellowTimeEnd) / (yellowTimeStart - yellowTimeEnd).toFloat()


    val redPercent = (remainingTime - retTimeEnd) / (redTimeStart - retTimeEnd).toFloat()

    if (remainingTime > yellowTimeStart) {
        return arrayOf(
            0f to green,
            1f to green
        )
    }
    if (remainingTime > yellowTimeEnd) {
        return arrayOf(
            0f to green,
            yellowPercent to green,
            yellowPercent to yellow,
            1f to yellow
        )
    }

    if (remainingTime > retTimeEnd) {
        return arrayOf(
            0f to green,
            yellowPercent to green,
            yellowPercent to yellow,
            redPercent to yellow,
            redPercent to red,
            1f to red
        )
    }

    return arrayOf(
        0f to red,
        1f to red
    )
}

@Composable
fun TimerPage(navController: NavHostController, timestamp: Long, cardId: Int) {
    val starting = TimeStruct().apply { fromMs(timestamp) }
    var countDownTimer by remember(timestamp) {
        mutableLongStateOf(timestamp - getCurrentTime().toMs())
    }

    LaunchedEffect(countDownTimer) {
        delay(10L)
        countDownTimer = timestamp - getCurrentTime().toMs()
    }

    val colorStops = reamingTimeToGradient(countDownTimer)

    val bigOutlinedStyle = LocalTextStyle.current.merge(
        fontSize = 120.sp,
        drawStyle = Stroke(
            miter = 10f,
            width = 10f,
            join = StrokeJoin.Round,
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.horizontalGradient(colorStops = colorStops)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "pozostało:",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight(700)
        )
        Text(
            text = TimeStruct().apply { fromMs(countDownTimer) }.toString(),
            color = Color.Black,
            style = bigOutlinedStyle,
            fontWeight = FontWeight(700)
        )

        Text(
            text = "Start o: $starting",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight(700)
        )
        Text(
            text = "teraz jest o: ${getCurrentTime()}",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight(700)
        )
        Button(onClick = {
            navController.navigate(NavigationItem.Card.route + "/${cardId}")
        }) {
            Text("Powrót")
        }
    }
}
