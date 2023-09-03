package com.example.questify.ui.dialogs

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [33])
@RunWith(AndroidJUnit4::class)
class TextInputDialogTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun init() {
        composeTestRule.setContent {
            TextInputDialog(initialValue = "initial value")
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
    fun textField_enterInput_verifyInput() {
        composeTestRule
            .onNodeWithTag("Text Field")
            .assertTextEquals("initial value")
        composeTestRule
            .onNodeWithTag("Text Field")
            .performTextInput("test input")
        composeTestRule
            .onNodeWithTag("Text Field")
            .assertTextEquals("test input")
    }
}