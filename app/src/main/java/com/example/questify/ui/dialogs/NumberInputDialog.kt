package com.example.questify.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun NumberInputDialog(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.testTag("Text Field")
        )

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            TextButton(onClick = {  }) {
                Text(text = "Cancel")
            }

            TextButton(onClick = { }) {
                Text(text = "OK")
            }
        }
    }
}