package com.example.questify.ui.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.questify.data.models.QuestModel
import com.example.questify.R
import com.example.questify.data.models.Status
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
        modifier = modifier
            .testTag("Quest Card"),
        onClick = {
            state.onClick(state.quest.id)
        },
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 8.dp)
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = state.quest.name,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.testTag("Quest Name")
                )
                when(state.quest.getPredictedStatus()) {
                    Status.NO_LIMIT -> GreyStatusToken()
                    Status.AHEAD -> GreenStatusToken()
                    Status.BEHIND -> RedStatusToken()
                    else -> YellowStatusToken()
                }
            }

            if(state.quest.getStreak() >= 2) {
                Row(
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        text = state.quest.getStreak().toString(),
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(horizontal = 0.dp)
                    )
                    Image(
                        painterResource(id = R.drawable.fire),
                        contentDescription = "streak",
                        modifier = Modifier
                            .size(20.dp)
                            .padding(bottom = 2.dp)
                    )
                }
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
            .padding(horizontal = 2.dp, vertical = 1.dp),
        style = MaterialTheme.typography.labelSmall
    )
}