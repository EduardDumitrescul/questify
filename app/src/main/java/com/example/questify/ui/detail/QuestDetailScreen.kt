package com.example.questify.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.questify.QuestModel

@Composable
fun QuestDetailScreen(
    viewModel: QuestDetailViewModel,
    modifier: Modifier = Modifier,
) {
    val quest by viewModel.quest.observeAsState()
    
    quest?.let {
        val state = QuestDetailState(
            quest = it,
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
    }
}


@Stable
class QuestDetailState(
    val quest: QuestModel,
)