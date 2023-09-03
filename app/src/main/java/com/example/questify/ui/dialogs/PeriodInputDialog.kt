package com.example.questify.ui.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag


@Composable
fun PeriodInputDialog(
    modifier: Modifier = Modifier,
) {
    TwoButtonDialog(
        onDismissRequest = {  },
        onComplete = { },
        modifier = modifier.testTag("Period Input Dialog")
    ) {

    }
}