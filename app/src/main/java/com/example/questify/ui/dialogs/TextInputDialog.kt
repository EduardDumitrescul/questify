package com.example.questify.ui.dialogs

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
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
        Surface(
            modifier = modifier.testTag("Text Input Dialog"),
        ) {

        }
    }
}