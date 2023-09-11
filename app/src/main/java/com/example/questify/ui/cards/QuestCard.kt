package com.example.questify.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun QuestCard(
    modifier: Modifier = Modifier,
    questName: String = "",
) {
    val state = remember {
        QuestCardState(
            questName = questName,
        )
    }

    state.onClick = {
        state.extended = !state.extended
    }

    QuestCardStateless(
        modifier = modifier,
        state = state
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestCardStateless(
    modifier: Modifier = Modifier,
    state: QuestCardState = QuestCardState(),
) {
    ElevatedCard(
        modifier = modifier.testTag("Quest Card"),
        onClick = state.onClick,
    ) {
        Column() {
            Text(
                text = state.questName,
                modifier = Modifier.testTag("Quest Name")
            )

            if(state.extended == true) {
                Column(
                    modifier = Modifier.testTag("body")
                ) {
                    TextButton(onClick = {  }) {
                        Text("edit")
                    }
                }
            }
        }
    }
}

@Stable
class QuestCardState(
    var questName: String = "",
) {
    var extended by mutableStateOf(false)
    var onClick: () -> Unit = {}
 }

@Preview(showBackground = true)
@Composable
fun QuestCardPreview() {
    QuestCard(questName = "Quest Name")
}