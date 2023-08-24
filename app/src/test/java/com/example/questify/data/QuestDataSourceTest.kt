package com.example.questify.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk=[33])
@RunWith(AndroidJUnit4::class)
class QuestDataSourceTest {
    private val dataSource = QuestDataSource()

    @Test
    fun checkQuestDataSourceExists() {
        assertNotNull(dataSource)
    }

}