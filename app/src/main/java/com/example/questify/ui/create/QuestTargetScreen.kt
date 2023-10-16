package com.example.questify.ui.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.text.isDigitsOnly
import kotlinx.coroutines.flow.MutableStateFlow

class QuestTargetScreen(
    initialTarget: Int,
    initialLimit: Int?,
    private val saveTarget: (Int) -> Unit,
    private val saveLimit: (Int?) -> Unit,
): SequentialScreen() {

    private val targetState = MutableStateFlow(initialTarget)
    private val limitState = MutableStateFlow(initialLimit)

    private fun updateTarget(target: Int) {
        targetState.value = target
    }

    private fun updateLimit(limit: Int?) {
        limitState.value = limit
    }

    @Composable
    override fun Screen() {
        BaseScreen(
            title = "What is your Target?",
        ) {
            val focusRequester = remember { FocusRequester() }
            val target by targetState.collectAsState()

            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = target.toString(),
                        onValueChange = {
                            if(it.startsWith("0")) {
                                updateTarget(0)
                            } else if(it.isDigitsOnly()) {
                                updateTarget(it.toInt())
                            }
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ) ,
                        modifier = Modifier
                            .focusRequester(focusRequester)
                            .weight(1f),
                    )

                    Text(
                        text = "reps",
                        modifier = Modifier.weight(1f)
                    )
                }

                HorizontalDivider()
                TimeLimitInput()

            }
        }
    }

    @Composable
    private fun TimeLimitInput() {
        var option by remember { mutableIntStateOf(0)}
        val limit by limitState.collectAsState()

        Row() {
            Checkbox(
                checked = option == 0,
                onCheckedChange = {
                    if(it == true) {
                        option = 0
                    }
                })

            Text(
                text = "I do not want a deadline"
            )
        }

        Row() {
            Checkbox(
                checked = option == 1,
                onCheckedChange = {
                    if(it == true) {
                        option = 1
                    }
                })

            Text(
                text = "Set a time limit"
            )
        }

        if(option == 1) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = limit?.toString() ?: "",
                    onValueChange = {
                        if(it.isEmpty()) {
                            updateLimit(null)
                        } else if(it.startsWith("0")) {
                            updateLimit(null)
                        } else if(it.isDigitsOnly()) {
                            updateLimit(it.toInt())
                        }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "days",
                    modifier = Modifier.weight(1f)
                )
            }
        }


    }

    override fun complete() {
        saveTarget(targetState.value)
        saveLimit(limitState.value)
    }

}