package com.example.questify.ui.quest

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.outlined.OpenInNew
import androidx.compose.material.icons.outlined.Timelapse
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.example.questify.R
import com.example.questify.data.QuestModel
import java.util.UUID

private const val TAG = "QUEST CARD"

@Composable
fun QuestCard(
    navigateToQuestEdit: (UUID) -> Unit,
    questModel: QuestModel,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false)}
    StatelessQuestCard(
        quest = questModel,
        expanded = expanded,
        onClickHeader = { expanded = !expanded },
        onClickQuestExpand = navigateToQuestEdit,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatelessQuestCard(
    quest: QuestModel,
    expanded: Boolean = false,
    onClickHeader: () -> Unit,
    onClickQuestExpand: (UUID) -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier,
        onClick = {},
        elevation = CardDefaults.elevatedCardElevation(
            disabledElevation = 6.dp
        ),
        colors = CardDefaults.elevatedCardColors(
            disabledContentColor = AppTheme.colorScheme.tertiary
        )
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClickHeader() }
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val color: Color = if(quest.isCompleted())
                    AppTheme.colorScheme.surfaceTint.copy()
                else
                    AppTheme.colorScheme.surfaceTint.copy(alpha = 0.2f)
                Icon(imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = stringResource(id = R.string.quest_check),
                    modifier = Modifier.padding(8.dp),
                    tint = color
                )

                Column {
                    Text(
                        text = quest.name,
                        style = AppTheme.typography.titleMedium,
                        color = AppTheme.colorScheme.onSurface
                    )
                    Text(
                        text = quest.getTargetStatus(),
                        style = AppTheme.typography.labelMedium,
                        color = AppTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                }

                Spacer(
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = { onClickQuestExpand(quest.id) },
                    modifier = Modifier
                        .size(40.dp)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.OpenInNew,
                         contentDescription = stringResource(id = R.string.open)
                    )
                }
            }

            if(expanded) {
                Divider(
                    color = AppTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                )
                Spacer(modifier = Modifier.height(8.dp))

                QuestRow(
                    leadingImageVector = Icons.Outlined.Description,
                    leadingContentDescription = stringResource(id = R.string.description),
                    text = stringResource(id = R.string.description),
                    trailingText = quest.description,
                    trailingTextType = TrailingTextType.Normal,
                    showDivider = false,
                    modifier = Modifier.padding(8.dp)
                )
                QuestRow(
                    leadingImageVector = Icons.Outlined.Flag,
                    leadingContentDescription = stringResource(id = R.string.target),
                    text = stringResource(id = R.string.target),
                    trailingText = quest.target.toString(),
                    trailingTextType = TrailingTextType.FullColored,
                    showDivider = false,
                    modifier = Modifier.padding(8.dp)
                )
                QuestRow(
                    leadingImageVector = Icons.Outlined.Timelapse,
                    leadingContentDescription = stringResource(id = R.string.time_remaining),
                    text = stringResource(id = R.string.time_remaining),
                    trailingText = quest.getTimeRemaining(),
                    trailingTextType = TrailingTextType.FullColored,
                    showDivider = false,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun CardRow(
    leadingImageVector: ImageVector,
    leadingContentDescription: String,
    text: String, 
    trailingContent: (() -> Unit) = {},
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
    )
    {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
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
            trailingContent()
        }
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewQuestCard() {
    QuestCard(
        navigateToQuestEdit = {} ,
        questModel = QuestModel()
    )
}