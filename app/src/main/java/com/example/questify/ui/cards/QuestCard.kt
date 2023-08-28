package com.example.questify.ui.cards

import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun QuestCard(
    questName: String = ""
) {
    ElevatedCard {
        Text(
            questName,
            modifier = Modifier.testTag("Quest Name")
        )
    }
}