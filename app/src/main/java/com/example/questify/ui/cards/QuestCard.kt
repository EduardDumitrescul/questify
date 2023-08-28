package com.example.questify.ui.cards

import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun QuestCard(
    modifier: Modifier = Modifier,
    questName: String = "",
) {
    ElevatedCard(
        modifier = modifier.testTag("Quest Card")
    ) {
        Text(
            questName,
            modifier = Modifier.testTag("Quest Name")
        )
    }
}