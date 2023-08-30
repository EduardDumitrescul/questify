package com.example.questify.ui.homepage

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.questify.QuestModel
import com.example.questify.ui.cards.QuestCard

@Composable
fun HomepageScreen(
    modifier: Modifier = Modifier,
    viewModel: HomepageViewModel = hiltViewModel(),
){
    val quests by viewModel.quests.observeAsState()

    var state by remember {
        mutableStateOf(
            HomepageScreenState(quests = quests)
        )
    }
    state.onClickAddQuest = {
        state = state.copy(
            showAddQuestBottomSheet = true
        )
    }
    state.onBottomSheetDismissRequest = {
        state = state.copy (
            showAddQuestBottomSheet = false
        )
    }

    HomepageScreenStateless(
        modifier = modifier,
        state = state,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomepageScreenStateless(
    modifier: Modifier = Modifier,
    state: HomepageScreenState = HomepageScreenState()
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
                        questName = quest.name
                    )
                }
            }

        }

        if(state.showAddQuestBottomSheet) {
            AddQuestBottomSheet(
                onDismissRequest = state.onBottomSheetDismissRequest
            )
        }
    }
}

data class HomepageScreenState(
    var quests: List<QuestModel>? = listOf(),
    var onClickAddQuest: () -> Unit = {},
    var showAddQuestBottomSheet: Boolean = false,
    var onBottomSheetDismissRequest: () -> Unit = {}
)

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun HomepageScreenPreview() {
    val state = HomepageScreenState(quests = mutableListOf(QuestModel(), QuestModel()))
    HomepageScreenStateless(state = state)
}
