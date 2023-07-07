package com.example.questify.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Backspace
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.outlined.KeyboardBackspace
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material.icons.outlined.Today
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.enableLiveLiterals
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.AppTheme
import com.example.questify.QuestEditViewModel
import com.example.questify.R
import com.example.questify.data.QuestModel
import org.w3c.dom.Text

@Composable
fun QuestEditScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: QuestEditViewModel = viewModel()
    val questModel = viewModel.questModel.observeAsState()

    var showNameDialog by remember { mutableStateOf(false)}
    var showDescriptionDialog by remember { mutableStateOf(false)}

    questModel.value?.let {quest ->
        StatelessQuestEditScreen(
            questModel = quest,
            onNameClick = {showNameDialog = true}
        )
        if(showNameDialog) {
            NameInputDialog(
                initialValue = quest.name,
                onDismissRequest = {showNameDialog = false},
                save = {viewModel.updateQuest(quest.apply {name = it})}
            )
        }

        if(showDescriptionDialog) {
            TextFieldDialog(
                label = stringResource(id = R.string.description),
                initialText = quest.description,
                onDismissRequest = { showDescriptionDialog = false },
                onComplete = {
                    viewModel.updateQuest(quest.apply {description = it})
                    showDescriptionDialog = false
                }
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
                QuestEditRow(
                    leadingImageVector = Icons.Outlined.Edit,
                    leadingContentDescription = stringResource(id = R.string.habit_name),
                    text = stringResource(id = R.string.habit_name),
                    trailingText = questModel.name,
                    modifier = Modifier.clickable { onNameClick() }
                )
                QuestEditRow(
                    leadingImageVector = Icons.Outlined.Description,
                    leadingContentDescription = stringResource(id = R.string.description),
                    text = stringResource(id = R.string.description),
                    trailingText = questModel.description,
                )
                QuestEditRow(
                    leadingImageVector = Icons.Outlined.Flag,
                    leadingContentDescription = stringResource(id = R.string.target_reps),
                    text = stringResource(id = R.string.target_reps),
                    trailingText = questModel.target.toString(),
                    trailingTextType = EditTextType.FullColored
                )
                QuestEditRow(
                    leadingImageVector = Icons.Outlined.Checklist,
                    leadingContentDescription = stringResource(id = R.string.rep_min_requirement),
                    text = stringResource(id = R.string.rep_min_requirement),
                    trailingText = questModel.getRepRequirements(),
                    trailingTextType = if(questModel.hasRequirements) EditTextType.FullColored else EditTextType.Normal,
                )
                QuestEditRow(
                    leadingImageVector = Icons.Outlined.Today,
                    leadingContentDescription = stringResource(id = R.string.start_date),
                    text = stringResource(id = R.string.start_date),
                    trailingText = questModel.getDateCreatedFormatted(),
                    trailingTextType = EditTextType.FullColored,
                )
                QuestEditRow(
                    leadingImageVector = Icons.Outlined.Event,
                    leadingContentDescription = stringResource(id = R.string.deadline),
                    text = stringResource(id = R.string.deadline),
                    trailingText = questModel.getDeadlineFormatted(),
                    trailingTextType = if(questModel.hasDeadline) EditTextType.FullColored else EditTextType.Normal,
                )
            }
        }
    }
}

enum class EditTextType {
    Normal,
    DigitColored,
    FullColored
}

@Composable
fun QuestEditRow(
    leadingImageVector: ImageVector,
    leadingContentDescription: String,
    text: String,
    trailingText: String = "",
    trailingTextType: EditTextType = EditTextType.Normal,
    modifier: Modifier = Modifier,
) {

    Surface(
        modifier = modifier
    )
    {

        Divider(
            color = AppTheme.colorScheme.outline.copy(alpha = 0.2f)
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 16.dp)
                .padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = leadingImageVector,
                contentDescription = leadingContentDescription,
                tint = AppTheme.colorScheme.primary,
                modifier = Modifier
                    .size(24.dp)
            )
            Text(
                text = text,
                style = AppTheme.typography.labelMedium,
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))



            when(trailingTextType) {
                EditTextType.DigitColored -> {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(shape = AppTheme.shapes.extraSmall)
                            .background(AppTheme.colorScheme.primaryContainer)
                            .padding(horizontal = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = trailingText,
                            style = AppTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = AppTheme.colorScheme.primary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                EditTextType.FullColored -> {
                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .height(24.dp)
                            .clip(shape = AppTheme.shapes.small)
                            .background(AppTheme.colorScheme.primaryContainer)
                            .padding(horizontal = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = trailingText,
                            style = AppTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = AppTheme.colorScheme.primary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                }
                else -> {
                    Text(
                        modifier = Modifier.widthIn(max = 120.dp),
                        text = trailingText,
                        style = AppTheme.typography.labelMedium,
                        color = AppTheme.colorScheme.outline,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldDialog(
    label: String,
    initialText: String,
    onDismissRequest: () -> Unit,
    onComplete: (String) -> Unit,
    singleLine: Boolean = false,
) {
    var textFieldValue by remember { mutableStateOf(initialText) }
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(false, false)
    ) {
        Column(
            modifier = Modifier
                .clip(shape = AppTheme.shapes.medium)
                .shadow(elevation = 6.dp, clip = true)
                .background(color = AppTheme.colorScheme.surface)
                .padding(horizontal = 16.dp)
                .padding(bottom = 8.dp, top = 16.dp)
        ) {
            OutlinedTextField(
                value = textFieldValue,
                onValueChange = {
                    textFieldValue = it
                },
                shape = AppTheme.shapes.small,
                label = {Text(label)},
                singleLine = singleLine,
                modifier = if(singleLine) Modifier else Modifier.height(120.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                 modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = {
                    onComplete(textFieldValue)
                }) {
                    Text(
                        text = stringResource(id = R.string.ok),
                        style = AppTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun NameInputDialog(
    initialValue: String = "test",
    onDismissRequest: () -> Unit = {},
    save: (String) -> Unit = {},
) {
    var value by remember {
        mutableStateOf(TextFieldValue(initialValue))
    }
    val focusRequester = remember { FocusRequester() }

    TwoButtonDialog(
        onDismissRequest = onDismissRequest,
        onCancel = onDismissRequest,
        onComplete = {
            save(value.text)
            onDismissRequest()
        }
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                value = it
            },
            shape = AppTheme.shapes.small,
            label = { Text(stringResource(R.string.habit_name)) },
            singleLine = true,
            enabled = true,
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .defaultMinSize(minHeight = 0.dp)
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        val text = value.text
                        value = value.copy(
                            selection = TextRange(0, text.length)
                        )
                    }
                },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = AppTheme.colorScheme.primary,
                unfocusedBorderColor = AppTheme.colorScheme.primary,
                focusedLabelColor = AppTheme.colorScheme.primary
            ),
        )
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun NumberInputDialog(
    initialValue: Int = 0,
    onDismissRequest: () -> Unit = {},
    save: (Int) -> Unit = {},
) {
    var value by remember { mutableIntStateOf(initialValue)}
    TwoButtonDialog(
        onDismissRequest = onDismissRequest,
        onCancel = onDismissRequest,
        onComplete = {
            onDismissRequest()
            save(value)
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(8.dp)
                .height(48.dp)
        ) {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
                    .background(AppTheme.colorScheme.primaryContainer)
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Remove,
                    contentDescription = stringResource(id = R.string.remove_rep),
                    tint = AppTheme.colorScheme.primary
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp),
                color = AppTheme.colorScheme.primary.copy(alpha = 0.4f)
            )
            OutlinedTextField(
                value = value.toString(),
                onValueChange = {
                    value = it.toInt()
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.NumberPassword
                ),
                visualTransformation = VisualTransformation.None ,
                modifier = Modifier.weight(1f),
                shape = CutCornerShape(0.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = AppTheme.colorScheme.primaryContainer,
                    textColor = AppTheme.colorScheme.primary,
                    focusedBorderColor = AppTheme.colorScheme.primaryContainer,
                    unfocusedBorderColor = AppTheme.colorScheme.primaryContainer,
                    cursorColor = AppTheme.colorScheme.primary
                ),
                textStyle = AppTheme.typography.titleMedium.copy(textAlign = TextAlign.Center),
                singleLine = true
            )
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp),
                color = AppTheme.colorScheme.primary.copy(alpha = 0.4f)
            )
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
                    .background(AppTheme.colorScheme.primaryContainer)
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = stringResource(id = R.string.add_rep),
                    tint = AppTheme.colorScheme.primary
                )
            }
        }
    }
}


@Preview
@Composable
fun TwoButtonDialog(
    onDismissRequest: () -> Unit = {},
    onCancel: () -> Unit = {},
    onComplete: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    Dialog(onDismissRequest = onDismissRequest)
    {
        Column(
            modifier = Modifier
                .clip(shape = AppTheme.shapes.medium)
                .shadow(elevation = 6.dp, clip = true)
                .width(200.dp)
                .background(AppTheme.colorScheme.surface),
        ) {
            content()

            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth(), color = AppTheme.colorScheme.surfaceVariant
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(
                    onClick = onCancel,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(bottomStart = 16.dp),
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        style = AppTheme.typography.labelMedium,
                        color = AppTheme.colorScheme.onSurface
                    )
                }
                Divider(
                    modifier = Modifier
                        .width(1.dp)
                        .height(48.dp), color = AppTheme.colorScheme.surfaceVariant
                )
                TextButton(
                    onClick = onComplete,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(bottomEnd = 16.dp),
                ) {
                    Text(
                        text = stringResource(id = R.string.ok),
                        style = AppTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = AppTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewTextFieldDialog() {
    TextFieldDialog(initialText = "initial text", onDismissRequest = {  }, label = "Label", onComplete = {})
}

@Preview
@Composable
fun PreviewQuestEditScreen(

) {
    StatelessQuestEditScreen(
        questModel = QuestModel()
    )
}