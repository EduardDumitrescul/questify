package com.example.questify.ui.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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


class ScreenFactory(private val viewModel: QuestCreateViewModel) {
    @Composable
    private fun SequentialScreen(
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
    enum class SCREENS {
        NAME_INPUT,
        DESCRIPTION_INPUT,
        TIMELIMIT_INPUT,
        TARGET_INPUT,
    }
    @Composable
    fun NameInputScreen(viewModel: QuestCreateViewModel) {
        SequentialScreen(
            title = "Define your Quest",
        ) {
            val focusRequester = remember { FocusRequester() }
            val quest by viewModel.quest.collectAsState()

            OutlinedTextField(
                value = quest.name,
                onValueChange = {
                    viewModel.setName(it)
                },
                label = {Text("Quest")},
                singleLine = true,
                modifier = Modifier
                    .focusRequester(focusRequester)
            )

            OutlinedTextField(
                value = quest.description,
                onValueChange = {
                    viewModel.setDescription(it)
                },
                label = {Text("More Details (optional)")},
                singleLine = false,
            )
        }
    }

    @Composable
    fun DescriptionInputScreen() {
        SequentialScreen(
            title = "Description",
        ) {
        }
    }

    @Composable
    fun TimeLimitInputScreen() {
        SequentialScreen(
            title = "Choose a Time Limit",
        ) {


        }
    }

    @Composable
    fun TargetInputScreen() {
        SequentialScreen(
            title = "What is your Target?",
        ) {
            val focusRequester = remember { FocusRequester() }
            val quest by viewModel.quest.collectAsState()
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = quest.targetReps.toString(),
                        onValueChange = {
                            if(it.startsWith("0")) {
                                viewModel.setTarget(0)
                            } else if(it.isDigitsOnly()) {
                                viewModel.setTarget(it.toInt())
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
                TimeLimitInput(
                    value = quest.timeLimit?.toString() ?: "",
                    updateValue = { viewModel.setTimeLimit(it) }
                )

            }
        }
    }

    @Composable
    fun TimeLimitInput(
        value: String,
        updateValue: (Int?) -> Unit,
    ) {
        var option by remember { mutableIntStateOf(0)}

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
                    value = value,
                    onValueChange = {
                        if(it.isEmpty()) {
                            updateValue(null)
                        } else if(it.startsWith("0")) {
                            updateValue(null)
                        } else if(it.isDigitsOnly()) {
                            updateValue(it.toInt())
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





    @Composable
    fun getSequence(screens: List<SCREENS>): List<@Composable () -> Unit> {
        val list = mutableListOf<@Composable () -> Unit>()
        for(i in screens.indices) {
            list.add { GetScreen(type = screens[i]) }
        }
        return list
    }

    @Composable
    fun GetScreen(type: SCREENS) = when(type) {
        SCREENS.NAME_INPUT -> NameInputScreen(viewModel)
        SCREENS.DESCRIPTION_INPUT -> DescriptionInputScreen()
        SCREENS.TIMELIMIT_INPUT -> TimeLimitInputScreen()
        SCREENS.TARGET_INPUT -> TargetInputScreen()
    }
}