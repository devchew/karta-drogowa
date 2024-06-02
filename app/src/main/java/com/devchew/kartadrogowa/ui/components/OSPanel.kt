package com.devchew.kartadrogowa.ui.components
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devchew.kartadrogowa.models.PanelModel

@Composable
fun OSPanel(
    panel: PanelModel,
    modifier: Modifier = Modifier,
) {

    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp).then(modifier)

    ) {
        Strip(pkc = panel.pkc.value)
        Details(
            panel = panel,
            modifier= Modifier.weight(1f)
        )
        NextOS(
            panel = panel,
        )
    }
}