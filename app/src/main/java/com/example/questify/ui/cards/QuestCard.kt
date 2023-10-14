package com.example.questify.ui.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.questify.QuestModel
import com.example.questify.Status
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
        Column {
            Text(
                text = state.quest.name,
                modifier = Modifier.testTag("Quest Name")
            )
            when(state.quest.getPredictedStatus()) {
                Status.NO_LIMIT -> GreyStatusToken()
                Status.AHEAD -> GreenStatusToken()
                Status.BEHIND -> RedStatusToken()
                else -> YellowStatusToken()
            }
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

@Preview(showBackground = true)
@Composable
fun GreyStatusToken() {
    StatusToken(color = Color.Gray, text = Status.NO_LIMIT.text)
}

@Preview(showBackground = true)
@Composable
fun GreenStatusToken() {
    StatusToken(color = Color.Green, text = Status.AHEAD.text)
}

@Preview(showBackground = true)
@Composable
fun YellowStatusToken() {
    StatusToken(color = Color.Yellow, Status.ON_TARGET.text)
}

@Preview(showBackground = true)
@Composable
fun RedStatusToken() {
    StatusToken(color = Color.Red, text = Status.BEHIND.text)
}

@Composable
fun StatusToken(
    color: Color,
    text: String,
) {
    Text(
        text = text,
        modifier = Modifier
            .background(
                color = color,
                shape = MaterialTheme.shapes.extraSmall
            )
            .padding(horizontal = 8.dp)
    )
}