package com.example.questify

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.questify.data.QuestDataSource
import com.example.questify.data.QuestModel

class OverviewViewModel: ViewModel() {
    private val dataSource = QuestDataSource()

    val quests: LiveData<List<QuestModel>> = dataSource.getQuests()
}