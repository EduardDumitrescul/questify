package com.example.questify.ui.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeriodInputDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
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
    var selected by remember {
        mutableIntStateOf(0)
    }
    val selectorFields = listOf("days", "weeks")

    TwoButtonDialog(
        onDismissRequest = onDismissRequest,
        onComplete = { },
        modifier = modifier.testTag("Period Input Dialog")
    ) {
        Column {
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
                    .testTag("Number Field")
                    .focusRequester(focusRequester)
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused) {
                            value = value.copy(
                                selection = TextRange(0, value.text.length)
                            )
                        }
                    },
            )

            Row(
                modifier = Modifier
            ) {
                for(i in selectorFields.indices) {
                    FilterChip(
                        selected =  selected == i ,
                        onClick = { selected = i },
                        label = { Text(selectorFields[i]) },
                    )
                }
            }
        }
    }
}