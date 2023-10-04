package com.example.questify.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.example.questify.QuestModel
import java.util.UUID

@Composable
fun QuestCard(
    modifier: Modifier = Modifier,
    quest: QuestModel = QuestModel(),
    onClick: (UUID) -> Unit = {},
) {
    val state = remember {
        QuestCardState(
            quest = quest,
            onClick = onClick,
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
        onClick = {
            state.onClick(state.quest.id)
        },
    ) {
        Column() {
            Text(
                text = state.quest.name,
                modifier = Modifier.testTag("Quest Name")
            )
        }
    }
}

@Stable
class QuestCardState(
    var quest: QuestModel = QuestModel(),
    val onClick: (UUID) -> Unit = {},
) {
 }

@Preview(showBackground = true)
@Composable
fun QuestCardPreview() {
    QuestCard(quest = QuestModel(name = "Quest Name"))
}