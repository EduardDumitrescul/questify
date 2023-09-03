package com.example.questify.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog

@Composable
fun TwoButtonDialog(
    onDismissRequest: () -> Unit,
    onComplete: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = modifier
        ) {
            content()

            Row(
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TextButton(
                    onClick = onDismissRequest,
                ) {
                    Text(text = "Cancel")
                }

                TextButton(
                    onClick = onComplete,
                ) {
                    Text(text = "OK")
                }
            }
        }
    }
}