package com.example.questify

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ActionRow(
    leadingIcon: @Composable () -> Unit = {},
    leadingText: @Composable () -> Unit = {},
    trailingContent: @Composable () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,

    ) {
        leadingIcon()
        leadingText()
        Spacer(modifier = Modifier.weight(1f))
        trailingContent()
    }

}