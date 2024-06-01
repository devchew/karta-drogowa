package com.devchew.kartadrogowa.ui.components
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devchew.kartadrogowa.database.Panel
import com.devchew.kartadrogowa.models.PanelModel

@Composable
fun OSPanel(
    panel: Panel,
    modifier: Modifier = Modifier,
) {
    val model = PanelModel(panel)
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp).then(modifier)
    ) {
        Strip(pkc = model.pkc.value)
        Details(
            panel = model,
            modifier= Modifier.weight(1f)
        )
        NextOS(
            panel = model,
        )
    }
}

//@Preview(showBackground = true, name = "CardHeader", showSystemUi = true,
//    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
//)
//@Composable
//fun PreviewOSPanel() {
//
//    val panelData = PanelModel(
//        pkcType = OSPanelType.Normal,
//        pkc = 1,
//        name = "PKC 1",
//        duration = 0f
//    )
//
//    KartaDrogowaTheme {
//        OSPanel(panelData)
//    }
//}