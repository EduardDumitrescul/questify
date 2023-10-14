package com.example.questify.ui.homepage

import androidx.lifecycle.ViewModel
import com.example.questify.data.models.QuestModel
import com.example.questify.data.QuestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomepageViewModel
@Inject constructor(private val dataSource: QuestRepository):
    ViewModel() {

    val quests = dataSource.getQuests()

    fun addQuest(quest: QuestModel) {
        dataSource.addQuest(quest)
    }
}
