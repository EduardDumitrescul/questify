package com.example.questify.ui.dialogs

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme


@Preview
@Composable
fun TextInputDialog(
    initialValue: String = "",
    label: @Composable () -> Unit = {},
    singleLine: Boolean = false,
    textFieldHeight: Dp = 80.dp,
    onDismissRequest: () -> Unit = {},
    save: (String) -> Unit = {},
) {
    var value by remember {
        mutableStateOf(TextFieldValue(initialValue))
    }
    val focusRequester = remember { FocusRequester() }

    TwoButtonDialog(
        onDismissRequest = onDismissRequest,
        onCancel = onDismissRequest,
        onComplete = {
            save(value.text)
            onDismissRequest()
        }
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                value = it
            },
            shape = AppTheme.shapes.small,
            label = label,
            singleLine = singleLine,
            enabled = true,
            modifier = Modifier
                .height(textFieldHeight)
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .defaultMinSize(minHeight = 0.dp)
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        val text = value.text
                        value = value.copy(
                            selection = TextRange(0, text.length)
                        )
                    }
                },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AppTheme.colorScheme.primary,
                unfocusedBorderColor = AppTheme.colorScheme.primary,
                focusedLabelColor = AppTheme.colorScheme.primary
            ),
        )
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}