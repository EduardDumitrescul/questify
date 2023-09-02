package com.example.questify.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.window.Dialog

@Composable
fun TextInputDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    initialValue: String = "",
) {
    var value by remember {
        mutableStateOf(
            TextFieldValue(initialValue)
        )
    }
    val focusRequester = remember { FocusRequester() }

    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = modifier.testTag("Text Input Dialog"),
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

            Row(
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TextButton(
                    onClick = onDismissRequest,
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