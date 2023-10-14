package com.example.questify

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.questify.data.models.QuestModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.time.LocalDate

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
        val date: LocalDate = LocalDate.now()
        assertEquals(quest.startDate, date)
        assertNull(quest.timeLimit)
        assertNull(quest.endDate)
        assertEquals(quest.targetReps, 20)
        assertEquals(quest.currentReps, 0)
    }

    @Test
    fun checkQuestSetters() {
        quest.name = "new name"
        quest.description = "description"
        quest.timeLimit = 100
        quest.endDate = LocalDate.MAX
        quest.targetReps = 100

        assertEquals(quest.name, "new name")
        assertEquals(quest.description, "description")
        assertEquals(quest.timeLimit, 100)
        assertEquals(quest.endDate, LocalDate.MAX)
        assertEquals(quest.targetReps, 100)
        assertEquals(quest.currentReps, 100)
    }



}