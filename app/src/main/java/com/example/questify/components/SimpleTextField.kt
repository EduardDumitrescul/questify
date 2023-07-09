package com.example.questify.components

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    shape: Shape = AppTheme.shapes.medium,
    label: @Composable () -> Unit = {},
    singleLine: Boolean = false,
    enabled: Boolean = true,
    leadingIcon: @Composable () -> Unit = {},
    contentPadding: PaddingValues = TextFieldDefaults.contentPaddingWithoutLabel(
        top = 0.dp,
        bottom = 0.dp,
    ),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
     ),
    isError: Boolean = false,
    interactionSource: InteractionSource = MutableInteractionSource()
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        singleLine = singleLine,
    ) { innerTextField ->
        OutlinedTextFieldDefaults.DecorationBox(
            value = value,
            innerTextField = innerTextField,
            enabled = enabled,
            singleLine = singleLine,
            visualTransformation = VisualTransformation.None,
            interactionSource = remember { MutableInteractionSource() },
            label = label,
            leadingIcon = leadingIcon,
            colors = colors,
            contentPadding = contentPadding,
            container = {
                OutlinedTextFieldDefaults.ContainerBox(enabled, isError, interactionSource, colors)
            },
        )
    }
}


