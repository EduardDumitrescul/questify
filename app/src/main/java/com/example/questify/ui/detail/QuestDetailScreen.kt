package com.example.questify.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.questify.QuestModel
import com.example.questify.ui.FieldRow

@Composable
fun QuestDetailScreen(
    viewModel: QuestDetailViewModel,
    modifier: Modifier = Modifier,
) {
    val quest by viewModel.quest.observeAsState()
    
    quest?.let {
        val state = QuestDetailState(
            quest = it,
            onPerformClick = { viewModel.performRep() },
        )
        StatelessQuestDetailScreen(
            state = state,
            modifier = modifier)
    }
    
}

@Composable
fun StatelessQuestDetailScreen(
    state: QuestDetailState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.testTag("Quest Detail Screen")
    ) {
        Text(text = state.quest.name)
        TextButton(
            onClick = state.onPerformClick
        ) {
            Text(text = "Perform Quest")
        }

        Text(text = state.quest.description)

        FieldRow(
            contentBegin = {
                Text(text = "Start Date")
            },
            contentEnd = {
                Text(text = state.quest.getStartDateString())
            }
        )

        FieldRow(
            contentBegin = {
                Text(text = "End Date")
            },
            contentEnd = {
                Text(text = state.quest.getEndDateString())
            }
        )

    }
}


@Stable
class QuestDetailState(
    val quest: QuestModel,
    val onPerformClick: () -> Unit,
)