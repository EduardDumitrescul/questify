package com.example.questify.ui.homepage

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.questify.QuestModel
import com.example.questify.data.QuestDataSourceLocal
import com.example.questify.ui.theme.QuestifyTheme
import org.junit.Before
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [33])
@RunWith(AndroidJUnit4::class)
class HomepageScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val dataSource = QuestDataSourceLocal()
    private val homepageViewModel = HomepageViewModel(dataSource)


    @Before
    fun init() {
        mutableListOf(
            QuestModel(name = "quest 1"),
            QuestModel(name = "quest 2"),
            QuestModel(name = "quest 3"),
        ).onEach {
            dataSource.addQuest(it)
        }

        composeTestRule.setContent {
            QuestifyTheme {
                HomepageScreen(viewModel = homepageViewModel)
            }
        }
    }

    @Test
    fun questCardsDisplayed() {
        composeTestRule.onAllNodesWithTag("Quest Card").assertCountEquals(dataSource.getNumberOfQuests())
    }
}