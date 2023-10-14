package com.example.questify.ui.homepage

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.questify.data.models.QuestModel
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

    @Test
    fun FABDisplayed() {
        composeTestRule
            .onNodeWithTag("FAB")
            .assertIsDisplayed()
            .assertHasClickAction()
            .assertIsEnabled()
    }

    @Test
    fun FABclick_showAddQuestBottomSheet() {
        composeTestRule
            .onNodeWithTag("FAB")
            .performClick()
        composeTestRule
            .onNodeWithTag("Add Quest Bottom Sheet")
            .assertExists()
    }

    @Test
    fun BottomSheetSwipeDown_hide() {
        composeTestRule
            .onNodeWithTag("FAB")
            .performClick()
        composeTestRule
            .onNodeWithTag("Add Quest Bottom Sheet")
            .performTouchInput {
                swipeDown()
            }
        composeTestRule
            .onNodeWithTag("Add Quest Bottom Sheet")
            .assertDoesNotExist()
    }

    @Test
    fun bottomSheet_save_verifyQuestAdded() {
        val initialQuestNumber = dataSource.getNumberOfQuests()
        composeTestRule
            .onNodeWithTag("FAB")
            .performClick()
        composeTestRule
            .onNodeWithText("Save")
            .performClick()
        composeTestRule
            .onAllNodesWithTag("Quest Card")
            .assertCountEquals(initialQuestNumber + 1)
    }
}