package com.xxh.sample.common.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun XDivider(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier
            .height(6.dp)
            .fillMaxWidth()
    )
}