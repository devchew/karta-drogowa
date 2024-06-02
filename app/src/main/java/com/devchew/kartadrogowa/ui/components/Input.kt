package com.devchew.kartadrogowa.ui.components

import android.content.res.Configuration
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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chargemap.compose.numberpicker.NumberPicker
import com.devchew.kartadrogowa.models.TimeStruct
import com.devchew.kartadrogowa.ui.theme.KartaDrogowaTheme

@Composable
fun InputBox(prefix: String, value: Int, gray: Boolean = false) {

    var background: Color = Color.White

    if (gray) {
        background = Color.LightGray
    }

    var textValue = "--"
    if (value >= 0) {
        textValue = value.toString().padStart(2, '0')
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
            text = textValue,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputGroup(
    name: String?,
    description: String?,
    h: Int? = null,
    m: Int? = null,
    s: Int? = null,
    tenths: Int? = null,
    onValueChange: (time: TimeStruct) -> Unit = { _ -> },
    beforeChane: () -> TimeStruct? = { null },
    gray: Boolean = false,
    modifier: Modifier = Modifier
) {
    var hValue by remember { mutableStateOf(h) }
    var mValue by remember { mutableStateOf(m) }
    var sValue by remember { mutableStateOf(s) }
    var tenthsValue by remember { mutableStateOf(tenths) }


    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    //to trzeba przebudować, dodać przycisk zatwierdź
    // zmienić inputy na zwykłe textfieldy

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Czas")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(
                        10.dp,
                        Alignment.CenterHorizontally
                    )
                ) {
                    if (h != null) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,

                            ) {
                            Text(text = "H: ")
                            NumberPicker(
                                value = hValue!!,
                                range = 0..24,
                                onValueChange = {
                                    hValue = it
                                }
                            )
                        }
                    }
                    if (m != null) {
                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Text(text = "M: ")
                            NumberPicker(
                                value = mValue!!,
                                range = 0..59,
                                onValueChange = {
                                    mValue = it
                                }
                            )
                        }
                    }
                    if (s != null) {
                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Text(text = "S: ")
                            NumberPicker(
                                value = sValue!!,
                                range = 0..60,
                                onValueChange = {
                                    sValue = it
                                }
                            )
                        }
                    }
                    if (tenths != null) {
                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Text(text = "1/10: ")
                            NumberPicker(
                                value = tenthsValue!!,
                                range = 0..100,
                                onValueChange = {
                                    tenthsValue = it
                                }
                            )
                        }
                    }
                }
                Button(
                    onClick = {
                        showBottomSheet = false
                        onValueChange(TimeStruct(hValue!!, mValue!!, sValue!!, tenthsValue!!))
                    },
                    modifier = Modifier.padding(bottom = 40.dp, top = 10.dp)
                ) {
                    Text("Zatwierdź")
                }
            }

        }
    }
    Surface(
        modifier = modifier,
        color = Color.Transparent,
        onClick = {
            showBottomSheet = true
            val before = beforeChane()
            if (before != null) {
                hValue = before.h
                mValue = before.m
                sValue = before.s
                tenthsValue = before.tenths
            }
        }
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
}

@Composable
@Preview(
    showBackground = true, backgroundColor = 0xFFFFFFFF, showSystemUi = false,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
fun InputGroupPreview() {
    KartaDrogowaTheme {
        InputGroup(
            "test",
            null,
            -1,
            13
        )
    }
}