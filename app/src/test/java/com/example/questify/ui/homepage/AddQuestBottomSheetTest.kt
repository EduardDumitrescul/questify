package com.example.questify.ui.homepage

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToString
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.questify.QuestModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [33])
@RunWith(AndroidJUnit4::class)
class AddQuestBottomSheetTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun init() {
        composeTestRule.setContent {
            AddQuestBottomSheet(onDismissRequest = { })
        }
    }

    @Test
    fun verifyTitleDisplayed() {
        composeTestRule
            .onNodeWithText("New Quest")
            .assertExists()
    }

    @Test
    fun verifyFieldsDisplayed() {
        composeTestRule
            .onNodeWithText("Name")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Description")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Target")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Deadline")
            .assertIsDisplayed()
    }

    @Test
    fun verifyFieldValuesDisplayed() {
        val quest = QuestModel()
        println(composeTestRule.onNodeWithTag("Add Quest Bottom Sheet").printToString())
        composeTestRule
            .onNodeWithTag("Name Field", useUnmergedTree = true)
            .assertTextEquals(quest.name)
        composeTestRule
            .onNodeWithTag("Description Field", useUnmergedTree = true)
            .assertTextEquals(quest.description)
        composeTestRule
            .onNodeWithTag("Target Field", useUnmergedTree = true)
            .assertTextEquals(quest.targetReps.toString())
        composeTestRule
            .onNodeWithTag("Deadline Field", useUnmergedTree = true)
            .assertTextEquals(quest.deadlineDate.toString())
    }

    @Test
    fun NameField_performClick_openDialog() {
        composeTestRule
            .onNodeWithTag("Name Field Row")
            .performClick()
        composeTestRule
            .onNodeWithTag("Text Input Dialog")
            .assertExists()
    }
}