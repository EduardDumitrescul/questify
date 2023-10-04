package com.example.questify.ui.homepage

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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.questify.QuestModel
import com.example.questify.ui.cards.QuestCard
import java.util.UUID

@Composable
fun HomepageScreen(
    modifier: Modifier = Modifier,
    viewModel: HomepageViewModel = hiltViewModel(),
    navigateToQuestDetailScreen: (UUID) -> Unit  = {},
){
    val quests by viewModel.quests.observeAsState()

    val state = HomepageScreenState(
        quests = quests,
        addQuest = {
            viewModel.addQuest(it)
        },
        navigateToQuestDetailScreen = navigateToQuestDetailScreen
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
            state.quests?.let {
                items(it) { quest ->
                    QuestCard(
                        quest = quest,
                        onClick = state.navigateToQuestDetailScreen,
                    )
                }
            }

        }

        if(state.showAddQuestBottomSheet) {
            AddQuestBottomSheet(
                onDismissRequest = state.onBottomSheetDismissRequest,
                onSave = {
                    state.addQuest(it)
                }

            )
        }
    }
}

@Stable
class HomepageScreenState(
    var quests: List<QuestModel>? = listOf(),
    val addQuest: (QuestModel) -> Unit,
    val navigateToQuestDetailScreen: (UUID) -> Unit = {},
) {
    var showAddQuestBottomSheet by mutableStateOf(false)
    var onClickAddQuest: () -> Unit = {
        showAddQuestBottomSheet = true
    }
    var onBottomSheetDismissRequest: () -> Unit = {
        showAddQuestBottomSheet = false
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun HomepageScreenPreview() {
    val state = HomepageScreenState(
        quests = mutableListOf(QuestModel(), QuestModel()),
        addQuest = {}
    )
    HomepageScreenStateless(state = state)
}
