package com.example.questify.ui.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

abstract class SequentialScreen {
    @Composable
    abstract fun Screen()

    abstract fun complete()

    @Composable
    protected fun BaseScreen(
        title: String,
        content: @Composable () -> Unit,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall
            )
            content()
        }
    }
}