package com.example.questify.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.window.Dialog

@Composable
fun TextInputDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Box(
            modifier = modifier.testTag("Text Input Dialog"),
        ) {
            OutlinedTextField(
                value = "not set",
                onValueChange = {},
                modifier = Modifier
                    .testTag("Text Field")
                    .align(Alignment.Center)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TextButton(
                    onClick = {  },
                    modifier = Modifier.testTag("Cancel Button"),
                ) {
                    Text(
                        text = "Cancel"
                    )
                }

                TextButton(
                    onClick = {},
                    modifier = Modifier.testTag("Confirm Button"),
                ) {
                    Text(
                        text = "OK"
                    )
                }
            }
        }
    }
}