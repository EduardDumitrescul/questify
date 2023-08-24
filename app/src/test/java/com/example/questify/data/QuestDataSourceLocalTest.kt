package com.example.questify.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.questify.QuestModel
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk=[33])
@RunWith(AndroidJUnit4::class)
class QuestDataSourceLocalTest {
    private val dataSource = QuestDataSourceLocal()

    @Test
    fun checkQuestDataSourceExists() {
        assertNotNull(dataSource)
    }

    @Test
    fun addQuest() {
        dataSource.addQuest(QuestModel())
        dataSource.addQuest(QuestModel())
        dataSource.addQuest(QuestModel())

        val numberOfQuests = dataSource.getNumberOfQuests()

        assertEquals(numberOfQuests, 3)
    }

    @Test
    fun getQuestById() {
        val quest = QuestModel()
        dataSource.addQuest(quest)

        val retrievedQuest: QuestModel = dataSource.getQuestById(quest.id)

        assertEquals(quest, retrievedQuest)
    }

}