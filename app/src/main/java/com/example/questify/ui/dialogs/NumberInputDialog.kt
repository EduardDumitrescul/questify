package com.example.questify.ui.dialogs

import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.core.text.isDigitsOnly

@Composable
fun NumberInputDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    onComplete: () -> Unit = {},
    initialValue: String = "",
) {
    var value by remember {
        mutableStateOf(
            TextFieldValue(initialValue)
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
                if(it.text.startsWith("0")) {
                    value = TextFieldValue("")
                } else if(it.text.isDigitsOnly()) {
                    value = it
                } else {
                    value = value.copy()
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ) ,
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