package com.example.questify.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.outlined.Today
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose.AppTheme
import com.example.questify.QuestEditViewModel
import com.example.questify.R
import com.example.questify.data.QuestModel
import com.example.questify.ui.dialogs.NumberInputDialog
import com.example.questify.ui.dialogs.NumberInputWithFilterChipsDialog
import com.example.questify.ui.dialogs.NumberInputWithUnitDialog
import com.example.questify.ui.dialogs.TextInputDialog
import com.example.questify.ui.quest.QuestRow
import com.example.questify.ui.quest.TrailingTextType

@Composable
fun QuestEditScreen(
    navigateUp: () -> Unit = {},
) {
    val viewModel: QuestEditViewModel = hiltViewModel()
    val questModel = viewModel.questModel.observeAsState()

    var showNameDialog by remember { mutableStateOf(false)}
    var showDescriptionDialog by remember { mutableStateOf(false)}
    var showTargetDialog by remember { mutableStateOf(false)}
    var showRequirementDialog by remember { mutableStateOf(false)}
    var showDeadlineDialog by remember { mutableStateOf(false)}

    questModel.value?.let {quest ->
        StatelessQuestEditScreen(
            questModel = quest,
            navigateUp = navigateUp,
            onNameClick = {showNameDialog = true},
            onDescriptionClick = {showDescriptionDialog = true},
            onTargetClick = {showTargetDialog = true},
            onRequirementsClick = {showRequirementDialog = true},
            onDeadlineClick = {showDeadlineDialog = true},
        )
        if(showNameDialog) {
            TextInputDialog(
                initialValue = quest.name,
                label = { Text(text = stringResource(id = R.string.quest_name))},
                singleLine = true,
                onDismissRequest = {showNameDialog = false},
                save = {viewModel.updateQuest(quest.apply {name = it})}
            )
        }

        if(showDescriptionDialog) {
            TextInputDialog(
                initialValue = quest.description,
                label =  { Text(text = stringResource(id = R.string.description))},
                singleLine = false,
                textFieldHeight = 136.dp,
                onDismissRequest = { showDescriptionDialog = false},
                save = {viewModel.updateQuest(quest.apply {description=it})}
            )
        }
        if(showTargetDialog) {
            NumberInputDialog(
                initialValue = quest.target,
                onDismissRequest = {showTargetDialog = false},
                save = {viewModel.updateQuest(quest.apply {target=it})}
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
    }

}

@Composable
fun StatelessQuestEditScreen(
    questModel: QuestModel,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
    onNameClick: () -> Unit = {},
    onDescriptionClick: () -> Unit = {},
    onTargetClick: () -> Unit = {},
    onRequirementsClick: () -> Unit = {},
    onDeadlineClick: () -> Unit = {},
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = navigateUp,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBackIosNew,
                        contentDescription = stringResource(id = R.string.back),
                        modifier = Modifier.size(16.dp),
                        tint = AppTheme.colorScheme.primary
                    )
                }
                Text(
                    text = questModel.name,
                    style = AppTheme.typography.titleMedium,
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            item {
                QuestRow(
                    leadingImageVector = Icons.Outlined.Edit,
                    leadingContentDescription = stringResource(id = R.string.quest_name),
                    text = stringResource(id = R.string.quest_name),
                    trailingText = questModel.name,
                    modifier = Modifier.clickable { onNameClick() }
                        .padding(vertical = 16.dp)
                )
                QuestRow(
                    leadingImageVector = Icons.Outlined.Description,
                    leadingContentDescription = stringResource(id = R.string.description),
                    text = stringResource(id = R.string.description),
                    trailingText = questModel.description,
                    modifier = Modifier.clickable{ onDescriptionClick() }
                        .padding(vertical = 16.dp)
                )
                QuestRow(
                    leadingImageVector = Icons.Outlined.Flag,
                    leadingContentDescription = stringResource(id = R.string.target_reps),
                    text = stringResource(id = R.string.target_reps),
                    trailingText = questModel.target.toString(),
                    trailingTextType = TrailingTextType.FullColored,
                    modifier = Modifier.clickable { onTargetClick() }
                        .padding(vertical = 16.dp)
                )
                QuestRow(
                    leadingImageVector = Icons.Outlined.Checklist,
                    leadingContentDescription = stringResource(id = R.string.rep_min_requirement),
                    text = stringResource(id = R.string.rep_min_requirement),
                    trailingText = questModel.getRepRequirements(),
                    trailingTextType = if(questModel.requirementTime > 0) TrailingTextType.FullColored else TrailingTextType.Normal,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .clickable { onRequirementsClick() },
                )
                QuestRow(
                    leadingImageVector = Icons.Outlined.Today,
                    leadingContentDescription = stringResource(id = R.string.start_date),
                    text = stringResource(id = R.string.start_date),
                    trailingText = questModel.getDateCreatedFormatted(),
                    trailingTextType = TrailingTextType.FullColored,
                    modifier = Modifier
                        .padding(vertical = 16.dp),
                )
                QuestRow(
                    leadingImageVector = Icons.Outlined.Event,
                    leadingContentDescription = stringResource(id = R.string.deadline),
                    text = stringResource(id = R.string.deadline),
                    trailingText = questModel.getDeadlineFormatted(),
                    trailingTextType = if(questModel.hasDeadline) TrailingTextType.FullColored else TrailingTextType.Normal,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .clickable { onDeadlineClick() },
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewQuestEditScreen(

) {
    StatelessQuestEditScreen(
        questModel = QuestModel()
    )
}