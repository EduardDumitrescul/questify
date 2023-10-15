package com.example.questify.ui.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


class ScreenFactory() {
    @Composable
    private fun SequentialScreen(
        title: String,
        content: @Composable () -> Unit,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall
            )
            content()
        }
    }
    enum class SCREENS {
        NAME_INPUT,
        DESCRIPTION_INPUT,
        TIMELIMIT_INPUT,
        TARGET_INPUT,
    }
    @Composable
    fun NameInputScreen() {
        SequentialScreen(
            title = "Define your Quest",
        ) {

        }
    }

    @Composable
    fun DescriptionInputScreen() {
        SequentialScreen(
            title = "Description",
        ) {
        }
    }

    @Composable
    fun TimeLimitInputScreen() {
        SequentialScreen(
            title = "Choose a Time Limit",
        ) {


        }
    }

    @Composable
    fun TargetInputScreen() {
        SequentialScreen(
            title = "What is your Target?",
        ) {
            Text(text = "Target")
        }
    }





    @Composable
    fun getSequence(screens: List<SCREENS>): List<@Composable () -> Unit> {
        val list = mutableListOf<@Composable () -> Unit>()
        for(i in screens.indices) {
            list.add { GetScreen(type = screens[i]) }
        }
        return list
    }

    @Composable
    fun GetScreen(type: SCREENS) = when(type) {
        SCREENS.NAME_INPUT -> NameInputScreen()
        SCREENS.DESCRIPTION_INPUT -> DescriptionInputScreen()
        SCREENS.TIMELIMIT_INPUT -> TimeLimitInputScreen()
        SCREENS.TARGET_INPUT -> TargetInputScreen()
    }
}