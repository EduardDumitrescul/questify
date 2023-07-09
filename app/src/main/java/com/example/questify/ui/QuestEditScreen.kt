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
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.AppTheme
import com.example.questify.QuestEditViewModel
import com.example.questify.R
import com.example.questify.data.QuestModel
import com.example.questify.ui.dialogs.NumberInputDialog
import com.example.questify.ui.dialogs.TextInputDialog
import com.example.questify.ui.quest.EditTextType
import com.example.questify.ui.quest.QuestRow

@Composable
fun QuestEditScreen() {
    val viewModel: QuestEditViewModel = hiltViewModel()
    val questModel = viewModel.questModel.observeAsState()

    var showNameDialog by remember { mutableStateOf(false)}
    var showDescriptionDialog by remember { mutableStateOf(false)}
    var showTargetDialog by remember { mutableStateOf(false)}

    questModel.value?.let {quest ->
        StatelessQuestEditScreen(
            questModel = quest,
            onNameClick = {showNameDialog = true},
            onDescriptionClick = {showDescriptionDialog = true},
            onTargetClick = {showTargetDialog = true},
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
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatelessQuestEditScreen(
    questModel: QuestModel,
    modifier: Modifier = Modifier,
    onNameClick: () -> Unit = {},
    onDescriptionClick: () -> Unit = {},
    onTargetClick: () -> Unit = {},
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
                    onClick = { /*TODO*/ },
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
                    trailingTextType = EditTextType.FullColored,
                    modifier = Modifier.clickable { onTargetClick() }
                        .padding(vertical = 16.dp)
                )
                QuestRow(
                    leadingImageVector = Icons.Outlined.Checklist,
                    leadingContentDescription = stringResource(id = R.string.rep_min_requirement),
                    text = stringResource(id = R.string.rep_min_requirement),
                    trailingText = questModel.getRepRequirements(),
                    trailingTextType = if(questModel.requirementTime > 0) EditTextType.FullColored else EditTextType.Normal,
                    modifier = Modifier
                        .padding(vertical = 16.dp),
                )
                QuestRow(
                    leadingImageVector = Icons.Outlined.Today,
                    leadingContentDescription = stringResource(id = R.string.start_date),
                    text = stringResource(id = R.string.start_date),
                    trailingText = questModel.getDateCreatedFormatted(),
                    trailingTextType = EditTextType.FullColored,
                    modifier = Modifier
                        .padding(vertical = 16.dp),
                )
                QuestRow(
                    leadingImageVector = Icons.Outlined.Event,
                    leadingContentDescription = stringResource(id = R.string.deadline),
                    text = stringResource(id = R.string.deadline),
                    trailingText = questModel.getDeadlineFormatted(),
                    trailingTextType = if(questModel.hasDeadline) EditTextType.FullColored else EditTextType.Normal,
                    modifier = Modifier
                        .padding(vertical = 16.dp),
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