package com.example.questify

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.questify.data.QuestDataSourceLocal
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [33])
@RunWith(AndroidJUnit4::class)
class HomepageViewModelTest {
    private val dataSource = QuestDataSourceLocal()
    private val viewModel = HomepageViewModel(dataSource)

    init {
        mutableListOf(
            QuestModel(name = "quest 1"),
            QuestModel(name = "quest 2"),
            QuestModel(name = "quest 3")
        ).onEach {
            dataSource.addQuest(it)
        }
    }
    @Test
    fun init() {
        assertNotNull(viewModel)
    }

    @Test
    fun getQuests() {
        val questsFromDataSource = dataSource.getQuests().value
        val questsFromViewModel = viewModel.quests.value

        assertEquals(questsFromDataSource, questsFromViewModel)
        dataSource.addQuest(QuestModel())
        assertEquals(questsFromDataSource, questsFromViewModel)
    }
}