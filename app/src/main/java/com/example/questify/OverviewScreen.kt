package com.example.questify

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose.AppTheme
import com.example.questify.data.QuestModel
import com.example.questify.ui.dialogs.NumberInputDialog
import com.example.questify.ui.dialogs.NumberInputWithFilterChipsDialog
import com.example.questify.ui.dialogs.NumberInputWithUnitDialog
import com.example.questify.ui.dialogs.TextInputDialog
import com.example.questify.ui.quest.TrailingTextType
import com.example.questify.ui.quest.QuestCard
import com.example.questify.ui.quest.QuestRow
import java.util.UUID
import kotlin.random.Random

private val TAG = "OVERVIEW_SCREEN"

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun OverviewScreen(
    navigateToQuestEdit: (UUID) -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: OverviewViewModel = hiltViewModel(),
) {
    val quests = viewModel.quests.observeAsState()
    val showBottomSheet = remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showBottomSheet.value = true
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = stringResource(id = R.string.new_quest)
                )
            }
        }
    )
    {
        Column(modifier = Modifier.padding(it)) {
            WelcomeMessage(
                modifier = Modifier.padding(vertical = 32.dp, horizontal = 16.dp)
            )

            quests.value?.let { questList ->
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .graphicsLayer { alpha = 0.99F }
                        .drawWithContent {
                            val colors = mutableListOf(Color.Transparent)
                                .apply { repeat(50) { add(Color.Black) } }
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

        if(showBottomSheet.value) {
            NewQuestBottomSheet(
                onDismissRequest = {showBottomSheet.value = false}
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun NewQuestBottomSheet(
    onDismissRequest: () -> Unit = {},
    sheetState: SheetState = SheetState(false),
    save: (QuestModel) -> Unit = {},
) {
    var quest by remember { mutableStateOf(QuestModel()) }

    var showNameDialog by remember { mutableStateOf(false)}
    var showDescriptionDialog by remember { mutableStateOf(false)}
    var showTargetDialog by remember { mutableStateOf(false)}
    var showRequirementDialog by remember { mutableStateOf(false)}
    var showDeadlineDialog by remember { mutableStateOf(false) }

    if(showNameDialog) {
        TextInputDialog(
            initialValue = quest.name,
            label = { Text(text = stringResource(id = R.string.quest_name))},
            singleLine = true,
            onDismissRequest = {showNameDialog = false},
            save = { quest.name = it }
        )
    }

    if(showDescriptionDialog) {
        TextInputDialog(
            initialValue = quest.description,
            label =  { Text(text = stringResource(id = R.string.description))},
            singleLine = false,
            textFieldHeight = 136.dp,
            onDismissRequest = { showDescriptionDialog = false},
            save = { quest.description=it }
        )
    }
    if(showTargetDialog) {
        NumberInputDialog(
            initialValue = quest.target,
            onDismissRequest = {showTargetDialog = false},
            save = { quest.target=it }
        )
    }
    if(showRequirementDialog) {
        NumberInputWithUnitDialog(
            initialValue = quest.requirementTime,
            onDismissRequest = {showRequirementDialog = false},
            save = { quest.requirementTime=it },
            unit = "minutes",
        )
    }
    if(showDeadlineDialog) {
        NumberInputWithFilterChipsDialog(
            initialValue = quest.deadline,
            onDismissRequest = {showDeadlineDialog = false},
            save = {value, unit ->
                if(unit == 3) quest.deadline = 30 * value
                else if(unit == 2) quest.deadline = 7 * value
                else quest.deadline = value
            },
            chipList = listOf("days", "weeks", "months")
        )
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
         sheetState = sheetState,
         dragHandle = null,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.new_quest),
                style = AppTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            /*
                name
                description
                target
                requirement
                deadline
             */

            QuestRow(
                leadingImageVector = Icons.Outlined.Edit, 
                leadingContentDescription = stringResource(id = R.string.quest_name), 
                text = stringResource(id = R.string.quest_name),
                trailingText = quest.name,
                trailingTextType = TrailingTextType.Normal,
                showDivider = false,
                modifier = Modifier.clickable { showNameDialog = true }
            )
            QuestRow(
                leadingImageVector = Icons.Outlined.Description,
                leadingContentDescription = stringResource(id = R.string.description),
                text = stringResource(id = R.string.description),
                trailingText = quest.description,
                trailingTextType = TrailingTextType.Normal,
                showDivider = false,
                modifier = Modifier.clickable { showDescriptionDialog = true }
            )
            QuestRow(
                leadingImageVector = Icons.Outlined.Flag,
                leadingContentDescription = stringResource(id = R.string.target),
                text = stringResource(id = R.string.target),
                trailingText = quest.target.toString(),
                trailingTextType = TrailingTextType.FullColored,
                showDivider = false,
                modifier = Modifier.clickable { showTargetDialog = true }
            )
            QuestRow(
                leadingImageVector = Icons.Outlined.Checklist,
                leadingContentDescription = stringResource(id = R.string.rep_min_requirement),
                text = stringResource(id = R.string.rep_min_requirement),
                trailingText = quest.getRepRequirements(),
                trailingTextType = if(quest.requirementTime > 0) TrailingTextType.FullColored else TrailingTextType.Normal,
                showDivider = false,
                modifier = Modifier.clickable { showRequirementDialog = true }
            )
            QuestRow(
                leadingImageVector = Icons.Outlined.Event,
                leadingContentDescription = stringResource(id = R.string.deadline),
                text = stringResource(id = R.string.deadline),
                trailingText = quest.deadline.toString(),
                trailingTextType = if(quest.deadline > 0) TrailingTextType.FullColored else TrailingTextType.Normal,
                showDivider = false,
                modifier = Modifier.clickable { showDeadlineDialog = true },
            )

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                FilledTonalButton(
                    onClick = { onDismissRequest() },
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = AppTheme.colorScheme.surfaceVariant,
                        contentColor = AppTheme.colorScheme.onSurfaceVariant
                    )
                ) {
                    Text("Cancel")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = {
                    save(quest)
                    onDismissRequest()},
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = AppTheme.colorScheme.tertiaryContainer,
                        contentColor = AppTheme.colorScheme.onTertiaryContainer
                    )
                ) {
                    Text("Save")
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