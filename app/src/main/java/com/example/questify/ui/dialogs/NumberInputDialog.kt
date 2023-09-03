package com.example.questify.ui.dialogs

import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun NumberInputDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    onComplete: () -> Unit = {},
) {
    var value by remember {
        mutableStateOf(
            TextFieldValue("")
        )
    }
    val focusRequester = remember {
        FocusRequester()
    }
    TwoButtonDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        onComplete = onComplete
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                value = if(it.text.startsWith("0")) {
                    it.copy(text = "")
                } else {
                    it
                }

            },
            modifier = Modifier
                .testTag("Text Field")
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        value = value.copy(
                            selection = TextRange(0, value.text.length)
                        )
                    }
                },
        )
    }
}