package com.example.questify.ui.cards

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToString
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.questify.ui.theme.QuestifyTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
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
        composeTestRule
            .onNodeWithTag(testTag = "Quest Name", useUnmergedTree = true)
            .assertTextEquals("Quest Name")
    }

    @Test
    fun performClick_verifyExtended() {
        composeTestRule
            .onNodeWithTag("Quest Card", useUnmergedTree = true)
            .performClick()
        println(composeTestRule.onRoot(useUnmergedTree = true).printToString())
//        composeTestRule
//            .onNodeWithTag("body", useUnmergedTree = true)
//            .assertIsDisplayed()
    }
}