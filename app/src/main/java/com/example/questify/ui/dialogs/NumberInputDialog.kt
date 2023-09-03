package com.example.questify.ui.dialogs

import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun NumberInputDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    onComplete: () -> Unit = {},
) {
    TwoButtonDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        onComplete = onComplete
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.testTag("Text Field")
        )
    }
}