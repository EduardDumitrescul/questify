package com.example.questify.ui.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeriodInputDialog(
    modifier: Modifier = Modifier,
) {
    val selectorFields = listOf("days", "weeks")

    TwoButtonDialog(
        onDismissRequest = {  },
        onComplete = { },
        modifier = modifier.testTag("Period Input Dialog")
    ) {
        Column {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.testTag("Number Field"),
            )

            Row(
                modifier = Modifier
            ) {
                for(i in selectorFields.indices) {
                    FilterChip(
                        selected = false,
                        onClick = {  },
                        label = { Text(selectorFields[i]) },
                    )
                }
            }
        }
    }
}