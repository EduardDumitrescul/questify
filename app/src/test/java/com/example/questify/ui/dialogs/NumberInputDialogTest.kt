package com.example.questify.ui.dialogs

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.Rule
import org.junit.Test
import org.robolectric.annotation.Config

private const val TEXT_FIELD = "Text Field"

@Config(sdk = [33])
@RunWith(AndroidJUnit4::class)
class NumberInputDialogTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun init() {
        composeTestRule.setContent {
            NumberInputDialog()
        }
    }

    @Test
    fun verifyComponents() {
        composeTestRule
            .onNodeWithTag("Text Field")
            .assertExists()
        composeTestRule
            .onNodeWithText("Cancel")
            .assertExists()
        composeTestRule
            .onNodeWithText("OK")
            .assertExists()
    }

    @Test
    fun verifyInput_number() {
        composeTestRule
            .onNodeWithTag(TEXT_FIELD)
            .performTextInput("12467890")
        composeTestRule
            .onNodeWithTag(TEXT_FIELD)
            .assertTextEquals("12467890")
    }

    @Test
    fun verifyInput_number_startWith0() {
        composeTestRule
            .onNodeWithTag(TEXT_FIELD)
            .performTextInput("0002467890")
        composeTestRule
            .onNodeWithTag(TEXT_FIELD)
            .performTextInput("123")
        composeTestRule
            .onNodeWithTag(TEXT_FIELD)
            .assertTextEquals("123")
    }

    @Test
    fun verifyInput_otherCharacters() {
        composeTestRule
            .onNodeWithTag(TEXT_FIELD)
            .performTextInput("11q2w4r67u890d.")
        composeTestRule
            .onNodeWithTag(TEXT_FIELD)
            .assertTextEquals("")
    }
}