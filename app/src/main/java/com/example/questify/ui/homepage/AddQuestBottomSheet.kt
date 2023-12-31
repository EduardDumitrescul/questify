package com.example.questify.ui.homepage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.questify.data.models.QuestModel
import com.example.questify.data.models.Status
import com.example.questify.ui.FieldRow
import com.example.questify.ui.dialogs.NumberInputDialog
import com.example.questify.ui.dialogs.PeriodInputDialog
import com.example.questify.ui.dialogs.TextInputDialog
import com.example.questify.util.formatToWeeksAndDays

@Composable
fun AddQuestBottomSheet(
    onDismissRequest: () -> Unit,
    onSave: (QuestModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    val state = rememberState(
        onDismissRequest = onDismissRequest,
        onSave = onSave,
        )

    AddQuestBottomSheetStateless(
        state = state,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddQuestBottomSheetStateless(
    state: AddQuestBottomSheetState,
    modifier: Modifier = Modifier,
) {
    if(state.showNameTextInputDialog){
        TextInputDialog(
            modifier = Modifier.testTag("Text Input Dialog"),
            onDismissRequest = state.closeNameTextInputDialog,
            onComplete = {
                state.saveName(it)
                state.closeNameTextInputDialog()
            },
        )
    }
    if(state.showDescriptionInputDialog) {
        TextInputDialog(
            modifier = Modifier.testTag("Description Input Dialog"),
            onDismissRequest = state.closeDescriptionInputDialog,
            onComplete = {
                state.saveDescription(it)
                state.closeDescriptionInputDialog()
            }
        )
    }
    if(state.showTargetInputDialog) {
        NumberInputDialog(
            modifier = Modifier.testTag("Number Input Dialog"),
            onDismissRequest = state.closeTargetInputDialog,
            onComplete = {
                 state.saveTarget(it)
                state.closeTargetInputDialog()
            },
            initialValue = state.quest.targetReps.toString(),
        )
    }

    if(state.showPeriodInputDialog) {
        PeriodInputDialog(
            onDismissRequest = state.closePeriodInputDialog,
            onComplete = {
                state.savePeriod(it)
                state.closePeriodInputDialog()
            }
        )
    }

    ModalBottomSheet(
        modifier = modifier.testTag("Add Quest Bottom Sheet"),
        onDismissRequest = state.onDismissRequest
    ) {
        Column {
            Text(text = "New Quest")

            NameFieldRow(
                name = state.quest.name,
                onClick = state.onNameFieldClick,
            )
            DescriptionFieldRow(
                description = state.quest.description,
                onClick = state.onDescriptionFieldClick,
            )
            TargetFieldRow(
                target = state.quest.targetReps,
                onClick = state.onTargetFieldClick,
            )
            TimeLimitFieldRow(
                timeLimit = state.quest.timeLimit,
                onClick = state.onTimeLimitFieldClick
            )

            Button(onClick = {
                state.onSave(state.quest)
                state.onDismissRequest()
            }) {
                Text("Save")
            }
        }
    }
}


@Composable
private fun rememberState(
    onDismissRequest: () -> Unit,
    onSave: (QuestModel) -> Unit,
) = remember {
    AddQuestBottomSheetState(
        onDismissRequest = onDismissRequest,
        onSave = onSave
    )
}

@Stable
class AddQuestBottomSheetState(
    val onDismissRequest: () -> Unit,
    val onSave: (QuestModel) -> Unit,
) {

    val quest by mutableStateOf(QuestModel(status = Status.ACTIVE))
    var showNameTextInputDialog by mutableStateOf(false)
    var onNameFieldClick: () -> Unit = {
        showNameTextInputDialog = true
    }
    var closeNameTextInputDialog: () -> Unit = {
        showNameTextInputDialog = false
    }
    var saveName: (String) -> Unit = {
        quest.name = it
    }

    var showDescriptionInputDialog by mutableStateOf(false)
    var onDescriptionFieldClick: () -> Unit = {
        showDescriptionInputDialog = true
    }
    var closeDescriptionInputDialog: () -> Unit = {
        showDescriptionInputDialog = false
    }
    var saveDescription: (String) -> Unit = {
        quest.description = it
    }

    var showTargetInputDialog by mutableStateOf(false)
    var onTargetFieldClick = {
        showTargetInputDialog = true
    }
    var closeTargetInputDialog = {
        showTargetInputDialog = false
    }
    var saveTarget: (Int) -> Unit = {
        quest.targetReps = it
    }

    var showPeriodInputDialog by mutableStateOf(false)
    var onTimeLimitFieldClick = {
        showPeriodInputDialog = true
    }
    var closePeriodInputDialog = {
        showPeriodInputDialog = false
    }
    var savePeriod: (Int) -> Unit = {
        quest.timeLimit = it
    }
}

@Composable
fun NameFieldRow(
    name: String,
    onClick: () -> Unit = {},
) {
    FieldRow(
        contentBegin = {
            Text(text = "Name")
        },
        contentEnd = {
            Text(
                text = name,
                modifier = Modifier.testTag("Name Field")
            )
        },
        modifier = Modifier
            .testTag("Name Field Row")
            .clickable { onClick() },
    )
}

@Composable
fun DescriptionFieldRow(
    description: String,
    onClick: () -> Unit = {},
) {
    FieldRow(
        contentBegin = {
            Text(text = "Description")
        },
        contentEnd = {
            Text(
                text = description,
                modifier = Modifier.testTag("Description Field")
            )
        },
        modifier = Modifier
            .testTag("Description Field Row")
            .clickable { onClick() }
    )
}

@Composable
fun TargetFieldRow(
    target: Int,
    onClick: () -> Unit = {},
) {
    FieldRow(
        contentBegin = {
            Text(text = "Target")
        },
        contentEnd = {
            Text(
                text = target.toString(),
                modifier = Modifier.testTag("Target Field")
            )
        },
        modifier = Modifier
            .clickable { onClick() }
            .testTag("Target Field Row")
    )
}

@Composable
fun TimeLimitFieldRow(
    timeLimit: Int?,
    onClick: () -> Unit = {}
) {
    FieldRow(
        contentBegin = {
            Text(text = "Time Limit")
        },
        contentEnd = {
            Text(
                text = formatToWeeksAndDays(timeLimit),
                modifier = Modifier.testTag("Time Limit Field")
            )
        },
        modifier = Modifier
            .testTag("Time Limit Row")
            .clickable { onClick() }
    )
}
