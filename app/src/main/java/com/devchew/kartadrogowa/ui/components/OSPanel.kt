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

enum class OSPanelType {
    Start,
    Normal
}

data class OSPanelData(
    val pkcType: OSPanelType,
    val pkc: Int,
    val name: String?,
    val duration: Float?,
    val finishTime: TimeStruct?,
    val finishResult: TimeStruct?,
    val provisionalStartTime: TimeStruct?,
    val realStartTime: TimeStruct?,
    val idealTime: TimeStruct?,
    val pkcTime: TimeStruct?,
    val estimatedTime: TimeStruct?,
)

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

@Composable
fun Details(
    pkcType: OSPanelType,
    pkc: Int,
    modifier: Modifier = Modifier,
    name: String?,
    duration: Float?,
    finishTime: TimeStruct? = null,
    finishResult: TimeStruct? = null,
    provisionalStartTime: TimeStruct? = null,
    realStartTime: TimeStruct? = null,
    idealTime: TimeStruct? = null,
    pkcTime: TimeStruct? = null,
) {
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

            if (pkcType == OSPanelType.Normal) {
                Text(
                    text = "${name}\n${duration}",
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
                    h = finishTime?.h,
                    m = finishTime?.m,
                    s = finishTime?.s,
                    tenths = finishTime?.tenths
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if(pkcType == OSPanelType.Normal) {
                InputGroup(
                    name = "Prowizoryczny\nczas startu(+3min)",
                    description = null,
                    h = provisionalStartTime?.h,
                    m = provisionalStartTime?.m,
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
                h = realStartTime?.h,
                m = realStartTime?.m,
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
                h = idealTime?.h,
                m = idealTime?.m,
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (pkcType == OSPanelType.Normal) {
                InputGroup(
                    name = null,
                    description = null,
                    h = null,
                    m = finishResult?.m,
                    s = finishResult?.s,
                    tenths = finishResult?.tenths
                )
            }
            InputGroup(
                name = null,
                description = "PKC ${(pkc + 1)}",
                gray = true,
                h = pkcTime?.h,
                m = pkcTime?.m,
            )
        }
    }
}

@Composable
fun NextOS(
    estimatedTime: TimeStruct? = null,
    modifier: Modifier = Modifier
) {
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
            h = estimatedTime?.h,
            m = estimatedTime?.m,
        )
    }
}



@Composable
fun OSPanel(
    modifier: Modifier = Modifier,
    panelData: OSPanelData
) {


    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp).then(modifier)
    ) {
        Strip(pkc = panelData.pkc)
        Details(
            pkcType = panelData.pkcType,
            name = panelData.name,
            duration = panelData.duration,
            pkc = panelData.pkc,
            finishTime = panelData.finishTime,
            finishResult = panelData.finishResult,
            provisionalStartTime = panelData.provisionalStartTime,
            realStartTime = panelData.realStartTime,
            idealTime = panelData.idealTime,
            pkcTime = panelData.pkcTime,
            modifier= Modifier.weight(1f)
        )
        NextOS(
            estimatedTime = panelData.estimatedTime
        )
    }
}

@Preview(showBackground = true, name = "CardHeader", showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun PreviewOSPanel() {

    val panelData = OSPanelData(
        pkcType = OSPanelType.Normal,
        pkc = 1,
        name = "PS 1 Targ 1",
        duration = 1.45f,
        finishTime = TimeStruct(-1, -1, -1, -1),
        finishResult = TimeStruct(-1, -1, -1, -1),
        provisionalStartTime = TimeStruct(-1, -1, -1, -1),
        realStartTime = TimeStruct(-1, -1, -1, -1),
        idealTime = TimeStruct(-1, -1, -1, -1),
        pkcTime = TimeStruct(-1, -1, -1, -1),
        estimatedTime = TimeStruct(-1, -1, -1, -1),
    )

    KartaDrogowaTheme {
        OSPanel(
            panelData = panelData
        )
    }
}