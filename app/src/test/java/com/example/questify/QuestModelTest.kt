package com.example.questify

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [33])
@RunWith(AndroidJUnit4::class)
class QuestModelTest {

    private val quest: QuestModel = QuestModel()

    @Test
    fun checkQuestExists() {
        assertNotNull(quest)
    }

    @Test
    fun checkQuestAttributes() {
        assertNotNull(quest.id)
        assertEquals(quest.name, "Base Quest")
        assertEquals(quest.description, "")
    }

}