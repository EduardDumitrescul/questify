package com.example.questify.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.example.questify.R
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun NumberInputWithFilterChipsDialog(
    initialValue: Int = 0,
    onDismissRequest: () -> Unit = {},
    save: (Int, Int) -> Unit = {p1, p2 -> },
    chipList: List<String> = listOf("days", "weeks", "months")
) {
    var value by remember { mutableStateOf(TextFieldValue(initialValue.toString())) }
    val focusRequester = remember { FocusRequester() }
    var selected by remember {
        mutableIntStateOf(0)
    }

    TwoButtonDialog(
        onDismissRequest = onDismissRequest,
        onCancel = onDismissRequest,
        onComplete = {
            onDismissRequest()
            if (value.text == "") {
                save(0, 0)
            } else {
                save(value.text.toInt(), selected)
            }
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .height(56.dp)
            ) {
                IconButton(
                    onClick = {
                        if (value.text.toInt() > 0) value =
                            value.copy(text = (value.text.toInt() - 1).toString())
                    },
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
                        .background(AppTheme.colorScheme.primaryContainer)
                        .size(56.dp)
                )
                {
                    Icon(
                        imageVector = Icons.Outlined.Remove,
                        contentDescription = stringResource(id = R.string.remove_rep),
                        tint = AppTheme.colorScheme.primary
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp),
                    color = AppTheme.colorScheme.primary.copy(alpha = 0.4f)
                )
                OutlinedTextField(
                    value = value,
                    onValueChange = {
                        value = it
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.NumberPassword
                    ),
                    visualTransformation = VisualTransformation.None,
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                val text = value.text
                                value = value.copy(
                                    selection = TextRange(0, text.length)
                                )
                            }
                        },
                    shape = CutCornerShape(0.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = AppTheme.colorScheme.primaryContainer,
                        unfocusedContainerColor = AppTheme.colorScheme.primaryContainer,
                        focusedTextColor = AppTheme.colorScheme.primary,
                        unfocusedTextColor = AppTheme.colorScheme.primary,
                        focusedBorderColor = AppTheme.colorScheme.primaryContainer,
                        unfocusedBorderColor = AppTheme.colorScheme.primaryContainer,
                        cursorColor = AppTheme.colorScheme.primary
                    ),
                    textStyle = AppTheme.typography.titleMedium.copy(textAlign = TextAlign.Center),
                    singleLine = true
                )
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp),
                    color = AppTheme.colorScheme.primary.copy(alpha = 0.4f)
                )
                IconButton(
                    onClick = { value = value.copy(text = (value.text.toInt() + 1).toString()) },
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
                        .background(AppTheme.colorScheme.primaryContainer)
                        .size(56.dp)
                )
                {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = stringResource(id = R.string.add_rep),
                        tint = AppTheme.colorScheme.primary
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 8.dp)
                    .fillMaxWidth()
            ) {
                for(i in chipList.indices) {
                    FilterChip(
                        selected = (i == selected),
                        onClick = { selected = i },
                        label = { Text(chipList[i]) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = AppTheme.colorScheme.primaryContainer
                        ), 
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}