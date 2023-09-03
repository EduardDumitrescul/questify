package com.example.questify.ui.homepage

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToString
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.questify.QuestModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


private const val NEW_QUEST = "New Quest"
private const val ADD_QUEST_BOTTOM_SHEET = "Add Quest Bottom Sheet"
private const val TEXT_INPUT_DIALOG = "Text Input Dialog"
private const val NUMBER_INPUT_DIALOG = "Number Input Dialog"
private const val TEXT_FIELD = "Text Field"
private const val CANCEL = "Cancel"
private const val OK = "OK"

private const val NAME = "Name"
private const val NAME_ROW = "Name Field Row"
private const val NAME_FIELD = "Name Field"

private const val DESCRIPTION = "Description"
private const val DESCRIPTION_ROW = "Description Field Row"
private const val DESCRIPTION_FIELD = "Description Field"
private const val DESCRIPTION_INPUT_DIALOG = "Description Input Dialog"

private const val TARGET = "Target"
private const val TARGET_ROW = "Target Field Row"
private const val TARGET_FIELD = "Target Field"

private const val DEADLINE = "Deadline"
private const val DEADLINE_FIELD = "Deadline Field"



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
            .onNodeWithText(NEW_QUEST)
            .assertExists()
    }

    @Test
    fun verifyFieldsDisplayed() {
        composeTestRule
            .onNodeWithText(NAME)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText(DESCRIPTION)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText(TARGET)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText(DEADLINE)
            .assertIsDisplayed()
    }

    @Test
    fun verifyFieldValuesDisplayed() {
        val quest = QuestModel()
        println(composeTestRule.onNodeWithTag(ADD_QUEST_BOTTOM_SHEET).printToString())
        composeTestRule
            .onNodeWithTag(NAME_FIELD, useUnmergedTree = true)
            .assertTextEquals(quest.name)
        composeTestRule
            .onNodeWithTag(DESCRIPTION_FIELD, useUnmergedTree = true)
            .assertTextEquals(quest.description)
        composeTestRule
            .onNodeWithTag(TARGET_FIELD, useUnmergedTree = true)
            .assertTextEquals(quest.targetReps.toString())
        composeTestRule
            .onNodeWithTag(DEADLINE_FIELD, useUnmergedTree = true)
            .assertTextEquals(quest.deadlineDate.toString())
    }

    @Test
    fun NameField_performClick_openDialog() {
        composeTestRule
            .onNodeWithTag(NAME_ROW)
            .performClick()
        composeTestRule
            .onNodeWithTag(TEXT_INPUT_DIALOG)
            .assertExists()
    }

    @Test
    fun NameField_writeTextToDialog_cancel() {
        composeTestRule
            .onNodeWithTag(NAME_ROW)
            .performClick()
        composeTestRule
            .onNodeWithTag(TEXT_FIELD)
            .performTextInput("input name")
        composeTestRule
            .onNodeWithText(CANCEL)
            .performClick()
        composeTestRule
            .onNodeWithTag(TEXT_INPUT_DIALOG)
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithTag(NAME_FIELD, useUnmergedTree = true)
            .assertTextEquals(QuestModel().name)
    }

    @Test
    fun DescriptionField_writeTextToDialog_cancel() {
        composeTestRule
            .onNodeWithTag(DESCRIPTION_ROW)
            .performClick()
        composeTestRule
            .onNodeWithTag(TEXT_FIELD)
            .performTextInput("input description")
        composeTestRule
            .onNodeWithText(CANCEL)
            .performClick()
        composeTestRule
            .onNodeWithTag(DESCRIPTION_INPUT_DIALOG)
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithTag(DESCRIPTION_FIELD, useUnmergedTree = true)
            .assertTextEquals(QuestModel().description)
    }

    @Test
    fun NameField_writeTextToDialog_save() {
        composeTestRule
            .onNodeWithTag(NAME_ROW)
            .performClick()
        composeTestRule
            .onNodeWithTag(TEXT_FIELD)
            .performTextInput("input name")
        composeTestRule
            .onNodeWithText(OK)
            .performClick()
        composeTestRule
            .onNodeWithTag(TEXT_INPUT_DIALOG)
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithTag(NAME_FIELD, useUnmergedTree = true)
            .assertTextEquals("input name")
    }

    @Test
    fun DescriptionField_writeTextToDialog_save() {
        composeTestRule
            .onNodeWithTag(DESCRIPTION_ROW)
            .performClick()
        composeTestRule
            .onNodeWithTag(TEXT_FIELD)
            .performTextInput("input description")
        composeTestRule
            .onNodeWithText(OK)
            .performClick()
        composeTestRule
            .onNodeWithTag(TEXT_INPUT_DIALOG)
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithTag(DESCRIPTION_FIELD, useUnmergedTree = true)
            .assertTextEquals("input description")
    }

    @Test
    fun DescriptionField_performClick_openDialog() {
        composeTestRule
            .onNodeWithTag(DESCRIPTION_ROW)
            .performClick()
        composeTestRule
            .onNodeWithTag(DESCRIPTION_INPUT_DIALOG)
            .assertExists()
    }

    @Test
    fun TargetField_performClick_openDialog() {
        composeTestRule
            .onNodeWithTag(TARGET_ROW)
            .performClick()
        composeTestRule
            .onNodeWithTag(NUMBER_INPUT_DIALOG)
            .assertExists()
    }

}

