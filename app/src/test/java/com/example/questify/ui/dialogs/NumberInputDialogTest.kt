package com.example.questify.ui.dialogs

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.Rule
import org.junit.Test
import org.robolectric.annotation.Config

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
}