package com.example.questify.ui.dialogs

import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


private const val NUMBER_FIELD = "Number Field"
private const val CANCEL = "Cancel"
private const val OK = "OK"

@Config(sdk = [33])
@RunWith(AndroidJUnit4::class)
class PeriodInputDialogTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private var dismissed = false

    @Before
    fun init() {
        composeTestRule.setContent {
            PeriodInputDialog(
                onDismissRequest = {dismissed = true}
            )
        }
    }

    @Test
    fun verifyComponents() {
        composeTestRule
            .onNodeWithTag(NUMBER_FIELD)
            .assertExists()
        composeTestRule
            .onNodeWithText("days")
            .assertExists()
        composeTestRule
            .onNodeWithText("weeks")
            .assertExists()
        composeTestRule
            .onNodeWithText(CANCEL)
            .assertExists()
        composeTestRule
            .onNodeWithText(OK)
            .assertExists()

    }

    @Test
    fun verifyInput_number() {
        composeTestRule
            .onNodeWithTag(NUMBER_FIELD)
            .performTextInput("12467890")
        composeTestRule
            .onNodeWithTag(NUMBER_FIELD)
            .assertTextEquals("12467890")
    }

    @Test
    fun verifyInput_number_startWith0() {
        composeTestRule
            .onNodeWithTag(NUMBER_FIELD)
            .performTextInput("0002467890")
        composeTestRule
            .onNodeWithTag(NUMBER_FIELD)
            .performTextInput("123")
        composeTestRule
            .onNodeWithTag(NUMBER_FIELD)
            .assertTextEquals("123")
    }

    @Test
    fun verifyInput_otherCharacters() {
        composeTestRule
            .onNodeWithTag(NUMBER_FIELD)
            .performTextInput("11q2w4r67u890d.")
        composeTestRule
            .onNodeWithTag(NUMBER_FIELD)
            .assertTextEquals("")
    }

    @Test
    fun verifySelector_onlyOneValueSelected() {
        composeTestRule.onNodeWithText("days")
            .assertIsSelected()
        composeTestRule
            .onNodeWithText("weeks")
            .assertIsNotSelected()
            .performClick()
            .assertIsSelected()
        composeTestRule.onNodeWithText("days")
            .assertIsNotSelected()
            .performClick()
            .assertIsSelected()
        composeTestRule
            .onNodeWithText("weeks")
            .assertIsNotSelected()
    }

    @Test
    fun cancelButton_performClick_verifyDismissed() {
        composeTestRule
            .onNodeWithText(CANCEL)
            .performClick()
        assertEquals(dismissed, true)
    }
}