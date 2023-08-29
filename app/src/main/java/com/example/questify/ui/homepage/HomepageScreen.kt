package com.example.questify.ui.homepage

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.questify.ui.cards.QuestCard

@Composable
fun HomepageScreen(
    modifier: Modifier = Modifier,
    viewModel: HomepageViewModel = hiltViewModel(),
){
    val quests by viewModel.quests.observeAsState()

    LazyColumn (
        modifier=  modifier.testTag("Homepage Screen")
    ) {
        quests?.let {
            items(it) { quest ->
                QuestCard(
                    questName = quest.name
                )
            }
        }

    }
}
