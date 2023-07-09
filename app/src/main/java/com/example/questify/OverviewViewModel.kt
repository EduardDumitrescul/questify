package com.example.questify

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.questify.data.QuestDataSource
import com.example.questify.data.QuestModel
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val dataSource: QuestDataSource
): ViewModel() {

    val quests: LiveData<List<QuestModel>> = dataSource.getQuests()

    fun addQuest(quest: QuestModel) {
        dataSource.insertQuest(quest)
    }
}