package com.example.questify.ui.homepage

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.questify.QuestModel
import com.example.questify.ui.FieldRow
import java.time.LocalDate

@Composable
fun AddQuestBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val state by remember {
        mutableStateOf(
            AddQuestBottomSheetState(
                onDismissRequest = onDismissRequest,
            )
        )
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
    ModalBottomSheet(
        modifier = modifier.testTag("Add Quest Bottom Sheet"),
        onDismissRequest = state.onDismissRequest
    ) {
        Column {
            Text(text = "New Quest")

            NameFieldRow(name = state.quest.name)
            DescriptionFieldRow(description = state.quest.description)
            TargetFieldRow(target = state.quest.targetReps)
            DeadlineFieldRow(deadline = state.quest.deadlineDate)
        }
    }
}

data class AddQuestBottomSheetState(
    val onDismissRequest: () -> Unit = {},
    val quest: QuestModel = QuestModel(),
)

@Composable
fun NameFieldRow(
    name: String
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
        }
    )
}

@Composable
fun DescriptionFieldRow(
    description: String
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
        }
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
