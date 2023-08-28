package com.example.questify.ui.cards

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.questify.ui.theme.QuestifyTheme
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.Rule
import org.junit.Test
import org.robolectric.annotation.Config

@Config(sdk = [33])
@RunWith(AndroidJUnit4::class)
class QuestCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun initQuestCard() {
        composeTestRule.setContent {
            QuestifyTheme {
                QuestCard(questName = "Quest Name")
            }
        }
    }


    @Test
    fun questName() {
        composeTestRule.onNodeWithTag("Quest Name").assertTextEquals("Quest Name")
    }
}