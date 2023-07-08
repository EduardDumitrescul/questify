package com.example.questify

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.AppTheme
import com.example.questify.data.QuestDataSource
import com.example.questify.ui.quest.QuestCard
import java.util.UUID
import kotlin.random.Random

private val TAG = "OVERVIEW_SCREEN"

@Composable
fun OverviewScreen(
    navigateToQuestEdit: (UUID) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OverviewViewModel = viewModel(),
) {
    val quests = viewModel.quests.observeAsState()

    Column() {
        WelcomeMessage(
            modifier = Modifier.padding(vertical = 32.dp, horizontal = 16.dp)
        )

        quests.value?.let { questList ->
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = modifier.fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .graphicsLayer { alpha = 0.99F }
                    .drawWithContent {
                        val colors = mutableListOf(Color.Transparent)
                            .apply { repeat(50){ add(Color.Black) } }
                            .apply { add(Color.Transparent) }
                        drawContent()
                        drawRect(
                            brush = Brush.verticalGradient(colors.toList()),
                            blendMode = BlendMode.DstIn
                        )
                    },
                contentPadding = PaddingValues(vertical = 32.dp)
            ) {
                items(questList.size) { quest ->

                    QuestCard(
                        navigateToQuestEdit = {
                            Log.d(TAG, "quest card parameter")
                            navigateToQuestEdit(it)
                        },

                        questModel = questList[quest],
                        modifier = Modifier.fillMaxWidth()
                    )

                }
            }

        }
    }
}

@Composable
fun WelcomeMessage(
    modifier: Modifier = Modifier
) {
    val messages = listOf(
        "Welcome!\nLet's crush those goals!",
        "Hey there!\nReady to level up?",
        "Welcome!\nTime to make things happen.",
        "Hello!\nGet ready to slay your goals.",
        "Hey you!\nReady to make progress?"
    )
    
    StatelessWelcomeMessage(
        message = messages[Random.nextInt(0, 4)],
        modifier = modifier
    )
}

@Composable
fun StatelessWelcomeMessage(
    message: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = message,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.W900,
        modifier = modifier
    )
}