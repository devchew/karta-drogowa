package com.devchew.kartadrogowa.ui.components
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devchew.kartadrogowa.logic.OSPanelType
import com.devchew.kartadrogowa.logic.PanelLogic
import com.devchew.kartadrogowa.ui.theme.KartaDrogowaTheme

@Composable
fun OSPanel(
    panel: PanelLogic,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp).then(modifier)
    ) {
        Strip(pkc = panel.pkc)
        Details(
            panel = panel,
            modifier= Modifier.weight(1f)
        )
        NextOS(
            panel = panel,
        )
    }
}

@Preview(showBackground = true, name = "CardHeader", showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun PreviewOSPanel() {

    val panelData = PanelLogic(
        pkcType = OSPanelType.Normal,
        pkc = 1,
        name = "PKC 1",
        duration = 0f
    )

    KartaDrogowaTheme {
        OSPanel(panelData)
    }
}