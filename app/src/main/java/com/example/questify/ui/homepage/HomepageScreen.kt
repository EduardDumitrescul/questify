package com.example.questify.ui.homepage

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.questify.data.models.QuestModel
import com.example.questify.data.models.Status
import com.example.questify.ui.cards.QuestCard
import java.util.UUID

@Composable
fun HomepageScreen(
    modifier: Modifier = Modifier,
    viewModel: HomepageViewModel = hiltViewModel(),
    navigateToQuestDetailScreen: (UUID) -> Unit,
    navigateToQuestCreateScreen: () -> Unit,
){
    val quests by viewModel.quests.observeAsState()

    val state = HomepageScreenState(
        quests = quests,
        addQuest = {
            viewModel.addQuest(it)
        },
        navigateToQuestDetailScreen = navigateToQuestDetailScreen,
        onClickAddQuest = navigateToQuestCreateScreen
    )

    HomepageScreenStateless(
        modifier = modifier,
        state = state,
    )
}

@Composable
fun HomepageScreenStateless(
    modifier: Modifier = Modifier,
    state: HomepageScreenState,
) {
    Scaffold(
        modifier =  modifier.testTag("Homepage Screen"),
        floatingActionButton = {
            FloatingActionButton(
                onClick = state.onClickAddQuest,
                modifier = Modifier
                    .testTag("FAB")
                    .width(64.dp)
                    .height(64.dp)
            ) {
                Icon(Icons.Default.Add, "Add quest button")
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        LazyColumn (
            modifier =  modifier
                .padding(it),
        ) {
            item {
                Text(
                    text = "Questify",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(48.dp)
                )
            }
            state.quests?.let {
                items(it) { quest ->
                    QuestCard(
                        quest = quest,
                        onClick = state.navigateToQuestDetailScreen,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                }
            }

        }
    }
}

@Stable
class HomepageScreenState(
    var quests: List<QuestModel>? = listOf(),
    val addQuest: (QuestModel) -> Unit,
    val navigateToQuestDetailScreen: (UUID) -> Unit = {},
    var onClickAddQuest: () -> Unit = {},
) {

}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun HomepageScreenPreview() {
    val state = HomepageScreenState(
        quests = mutableListOf(QuestModel(status = Status.ACTIVE), QuestModel(status = Status.ACTIVE)),
        addQuest = {}
    )
    HomepageScreenStateless(state = state)
}
