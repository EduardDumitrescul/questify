package com.example.questify.ui.dialogs

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
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
            TextInputDialog()
        }
    }

    @Test
    fun verifyComponents() {
        composeTestRule
            .onNodeWithTag("Text Field")
            .assertExists()
        composeTestRule
            .onNodeWithTag("Cancel Button")
            .assertTextEquals("Cancel")
        composeTestRule
            .onNodeWithTag("Confirm Button")
            .assertTextEquals("OK")
    }
}