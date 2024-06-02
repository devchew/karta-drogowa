package com.devchew.kartadrogowa.ui.components

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devchew.kartadrogowa.database.OSPanelType
import com.devchew.kartadrogowa.models.PanelModel

@Composable
fun Details(
    panel: PanelModel,
    modifier: Modifier = Modifier,
) {
    val title = when (panel.duration.value) {
        0f -> "${panel.name.value}\n"
        else -> "${panel.name.value}\n${panel.duration.value}km"
    }

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

            if (panel.pkcType.value == OSPanelType.Normal) {
                Text(
                    text = title,
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
                    h = panel.finishTime.value.h,
                    m = panel.finishTime.value.m,
                    s = panel.finishTime.value.s,
                    tenths = panel.finishTime.value.tenths,
                    onValueChange = { time -> panel.finishTimeChange(time) },
                    beforeChane = { panel.finishTimeGetSuggested() },
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 64.dp, 0.dp)
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (panel.pkcType.value == OSPanelType.Normal) {
                InputGroup(
                    name = "Prowizoryczny\nczas startu(+3min)",
                    description = null,
                    h = panel.provisionalStartTime.value.h,
                    m = panel.provisionalStartTime.value.m,
                    onValueChange = { time -> panel.provisionalStartTimeChange(time) },
                    beforeChane = { panel.provisionalStartTimeGetSuggested() }
                )
                Icon(
                    Icons.Default.PlayArrow,
                    contentDescription = "going to",
                    modifier = Modifier.offset((-4).dp, 22.dp),
                    tint = Color.Black
                )
            }
            InputGroup(
                name = "Rzeczywisty\nczas startu",
                description = null,
                gray = true,
                h = panel.realStartTime.value.h,
                m = panel.realStartTime.value.m,
                onValueChange = { time -> panel.realStartTimeChange(time) },
                beforeChane = { panel.realStartTimeGetSuggested() }
            )
            Icon(
                Icons.Rounded.CheckCircle,
                contentDescription = "Finish",
                modifier = Modifier.offset(0.dp, 22.dp),
                tint = Color.Black
            )
            Icon(
                Icons.Default.PlayArrow,
                contentDescription = "going to",
                modifier = Modifier.offset((-4).dp, 22.dp),
                tint = Color.Black
            )
            InputGroup(
                name = "Idealny\nczas przejazdu",
                description = null,
                h = panel.idealTime.value.h,
                m = panel.idealTime.value.m,
                onValueChange = { time -> panel.idealTimeChange(time) },
                beforeChane = { panel.idealTimeGetSuggested() }
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (panel.pkcType.value == OSPanelType.Normal) {
                InputGroup(
                    name = null,
                    description = "Wynik",
                    h = null,
                    m = panel.finishResult.value.m,
                    s = panel.finishResult.value.s,
                    tenths = panel.finishResult.value.tenths,
                    onValueChange = {time -> panel.finishResultChange(time)},
                    beforeChane = { panel.finishResultGetSuggested() }
                )
            }
            InputGroup(
                name = null,
                description = "PKC ${(panel.pkc.value + 1)}",
                gray = true,
                h = panel.pkcTime.value.h,
                m = panel.pkcTime.value.m,
                onValueChange = {time -> panel.pkcTimeChange(time)},
                beforeChane = { panel.pkcTimeGetSuggested() }
            )
        }
    }
}