package com.example.questify.ui.homepage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.questify.QuestModel
import com.example.questify.ui.FieldRow
import com.example.questify.ui.dialogs.TextInputDialog
import java.time.LocalDate

@Composable
fun AddQuestBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var state by remember {
        mutableStateOf(
            AddQuestBottomSheetState(
                onDismissRequest = onDismissRequest,
            )
        )
    }
    state.onNameFieldClick = {
        state = state.copy(
            showNameTextInputDialog = true,
        )
    }
    state.saveName = {
        state.quest.name = it
        state = state.copy()
    }
    state.closeNameTextInputDialog = {
        state = state.copy(
            showNameTextInputDialog = false
        )
    }
    state.onDescriptionFieldClick = {
        state = state.copy(
            showDescriptionTextInputDialog = true,
        )
    }
    state.closeDescriptionInputDialog = {
        state = state.copy(
            showDescriptionTextInputDialog = false
        )
    }
    state.saveDescription = {
        state.quest.description = it
        state = state.copy()
    }

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
            onConfirm = {
                state.saveName(it)
                state.closeNameTextInputDialog()
            },
        )
    }
    if(state.showDescriptionTextInputDialog) {
        TextInputDialog(
            modifier = Modifier.testTag("Description Input Dialog"),
            onDismissRequest = state.closeDescriptionInputDialog,
            onConfirm = {
                state.saveDescription(it)
                state.closeDescriptionInputDialog()
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
            TargetFieldRow(target = state.quest.targetReps)
            DeadlineFieldRow(deadline = state.quest.deadlineDate)
        }
    }
}

data class AddQuestBottomSheetState(
    val onDismissRequest: () -> Unit = {},
    val quest: QuestModel = QuestModel(),
    var onNameFieldClick: () -> Unit = {},
    var showNameTextInputDialog: Boolean = false,
    var closeNameTextInputDialog: () -> Unit = {},
    var saveName: (String) -> Unit = {},
    var onDescriptionFieldClick: () -> Unit = {},
    var closeDescriptionInputDialog: () -> Unit = {},
    var saveDescription: (String) -> Unit = {},
    var showDescriptionTextInputDialog: Boolean = false,
)

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
    target: Int
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
        }
    )
}

@Composable
fun DeadlineFieldRow(
    deadline: LocalDate?
) {
    FieldRow(
        contentBegin = {
            Text(text = "Deadline")
        },
        contentEnd = {
            Text(
                text = deadline.toString(),
                modifier = Modifier.testTag("Deadline Field")
            )
        }
    )
}
