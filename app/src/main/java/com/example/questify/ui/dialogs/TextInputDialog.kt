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
fun TextInputDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    onComplete: (String) -> Unit = {},
    initialValue: String = "",
) {
    var value by remember {
        mutableStateOf(
            TextFieldValue(initialValue)
        )
    }
    val focusRequester = remember { FocusRequester() }

    TwoButtonDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        onComplete = {
            onComplete(value.text)
        }
    ) {
            OutlinedTextField(
                value = value,
                onValueChange = { textFieldValue: TextFieldValue ->
                    value = textFieldValue
                },
                modifier = Modifier
                    .testTag("Text Field")
                    .focusRequester(focusRequester)
                    .onFocusChanged {focusState ->
                        if(focusState.isFocused) {
                            value = value.copy(
                                selection = TextRange(0, value.text.length)
                            )
                        }

                    },
            )
    }
}