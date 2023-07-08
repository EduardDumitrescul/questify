package com.example.questify.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.compose.AppTheme
import com.example.questify.R


@Preview
@Composable
fun TwoButtonDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    onCancel: () -> Unit = {},
    onComplete: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    Dialog(onDismissRequest = onDismissRequest)
    {
        Surface(
            modifier = modifier
        ) {
            Column(
                modifier = Modifier
                    .clip(shape = AppTheme.shapes.medium)
                    .shadow(elevation = 6.dp, clip = true)
                    .defaultMinSize(minWidth = 200.dp)
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
}
