package com.example.questify

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.Rule
import org.junit.Test
import org.robolectric.annotation.Config

@Config(sdk = [33])
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun onCreate_HomepageExists() {
        composeTestRule.onNodeWithTag("Homepage Screen").assertExists()
    }
}