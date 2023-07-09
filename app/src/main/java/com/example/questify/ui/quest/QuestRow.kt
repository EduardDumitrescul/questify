package com.example.questify.ui.quest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme


enum class EditTextType {
    Normal,
    DigitColored,
    FullColored
}

@Composable
fun QuestRow(
    leadingImageVector: ImageVector,
    leadingContentDescription: String,
    text: String,
    trailingText: String = "",
    trailingTextType: EditTextType = EditTextType.Normal,
    showDivider: Boolean = true,
    modifier: Modifier = Modifier,
) {
    if(showDivider) {
        Divider(
            color = AppTheme.colorScheme.outline.copy(alpha = 0.2f)
        )
    }
    Surface(
        modifier = modifier
    )
    {
        Row(
            modifier = Modifier
                .padding(start = 8.dp, end = 16.dp),
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