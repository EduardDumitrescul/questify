package com.example.questify.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTextField(
    value: String = "",
    onValueChange: (String) -> Unit = {},
    shape: Shape = AppTheme.shapes.medium,
    label: @Composable () -> Unit = {},
    modifier: Modifier = Modifier,
    singleLine: Boolean = false,
    enabled: Boolean = true,
    leadingIcon: @Composable () -> Unit = {},
    contentPadding: PaddingValues = TextFieldDefaults.textFieldWithoutLabelPadding(
        top = 0.dp,
        bottom = 0.dp
    ),
     colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors()
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        singleLine = singleLine,
    ) { innerTextField ->
        TextFieldDefaults.OutlinedTextFieldDecorationBox(
            value = value,
            innerTextField = innerTextField,
            label = label,
            enabled = enabled,
            singleLine = singleLine,
            visualTransformation = VisualTransformation.None,
            leadingIcon = leadingIcon,
            contentPadding = contentPadding,
            interactionSource = remember { MutableInteractionSource() },
            colors = colors,
        )
    }
}


