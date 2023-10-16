package com.example.questify.ui.create

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import kotlinx.coroutines.flow.MutableStateFlow

class QuestDefineScreen(
    initialName: String,
    initialDescription: String,
    private val saveName: (String) -> Unit,
    private val saveDescription: (String) -> Unit,
): SequentialScreen() {
    private var nameStateFlow = MutableStateFlow(initialName)
    private var descriptionStateFlow = MutableStateFlow(initialDescription)

    private fun updateName(name: String) {
        nameStateFlow.value = name
    }

    private fun updateDescription(description: String) {
        descriptionStateFlow.value = description
    }
    @Composable
    override fun Screen() {
        BaseScreen(
            title = "Define your Quest",
        ) {
            val focusRequester = remember { FocusRequester() }

            val description by descriptionStateFlow.collectAsState()
            val name by nameStateFlow.collectAsState()

            OutlinedTextField(
                value = name,
                onValueChange = { updateName(it)},
                label = { Text("Quest") },
                singleLine = true,
                modifier = Modifier
                    .focusRequester(focusRequester)
            )

            OutlinedTextField(
                value = description,
                onValueChange = { updateDescription(it) },
                label = { Text("More Details (optional)") },
                singleLine = false,
            )
        }
    }

    override fun complete() {
        saveName(nameStateFlow.value)
        saveDescription(descriptionStateFlow.value)
    }
}