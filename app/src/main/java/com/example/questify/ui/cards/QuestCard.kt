package com.example.questify.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun QuestCard(
    modifier: Modifier = Modifier,
    questName: String = "",
) {
    var state by remember {
        mutableStateOf(
            QuestCardState(
                questName = questName,
                extended = false,
            )
        )
    }

    state.onClick = {
        state = state.copy(
            extended = !state.extended,
            questName = "sad"
        )
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
                    Spacer(Modifier.height(20.dp))
                }
            }
        }
    }
}

data class QuestCardState(
    var questName: String = "",
    var extended: Boolean = false,
    var onClick: () -> Unit = {}
)

@Preview(showBackground = true)
@Composable
fun QuestCardPreview() {
    QuestCard(questName = "Quest Name")
}