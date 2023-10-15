package com.example.questify.ui.create

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun QuestCreateScreen(
    viewModel: QuestCreateViewModel,
    modifier: Modifier = Modifier,
) {
    val screenList = ScreenFactory().getSequence(
        listOf(
            ScreenFactory.SCREENS.NAME_INPUT,
            ScreenFactory.SCREENS.DESCRIPTION_INPUT,
            ScreenFactory.SCREENS.TARGET_INPUT,
            ScreenFactory.SCREENS.TIMELIMIT_INPUT
        )
    )

    val screenIndex by viewModel.screenIndex.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        AnimatedContent(
            targetState = screenIndex,
            transitionSpec = {
                var reverse = 1
                if (targetState < initialState) {
                    reverse = -1
                }

                fadeIn(
                    animationSpec = tween(500, easing = EaseInOut)
                ) + slideInHorizontally(
                    animationSpec = tween(500, easing = EaseInOut),
                    initialOffsetX = { fullWidth -> reverse * fullWidth }
                ) togetherWith
                        fadeOut(
                            animationSpec = tween(500, 0, easing = EaseIn)
                        ) + slideOutHorizontally(
                    animationSpec = tween(500, delayMillis = 50, easing = EaseIn),
                    targetOffsetX = { fullWidth -> -reverse * fullWidth }
                )
            },

            label = "Sequential Screens Slide"
        ) {
            screenList[it]()
        }
        Spacer(modifier = Modifier.weight(1f))
        BottomBar(
            isFirst = screenIndex == 0,
            isLast = screenIndex == screenList.lastIndex,
            next = { viewModel.next() },
            prev = { viewModel.previous() },
            save = { viewModel.save() },
            modifier = Modifier
                .fillMaxWidth(),
        )

    }
}



@Composable
private fun BottomBar(
    modifier: Modifier = Modifier,
    isFirst: Boolean,
    isLast: Boolean,
    next: () -> Unit,
    prev: () -> Unit,
    save: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        if (isFirst == false) {
            Button(
                onClick = prev,
            ) {
                Text("prev")
            }
        }
        else {
            Surface {}
        }

        if (isLast == true) {
            Button(
                onClick = save,
            ) {
                Text("save")
            }
        } else {
            Button(
                onClick = next,
            ) {
                Text("next")
            }
        }
    }
}

