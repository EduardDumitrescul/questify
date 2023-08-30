package com.example.questify.ui.homepage

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
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
}