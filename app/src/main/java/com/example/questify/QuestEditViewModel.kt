package com.example.questify

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.questify.data.QuestDataSource
import com.example.questify.data.QuestModel
import java.util.UUID

class QuestEditViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val dataSource: QuestDataSource = QuestDataSource()
    private val questId: UUID = UUID.fromString(savedStateHandle.get<String>(QuestEdit.questIdArg)!!)
    val questModel: LiveData<QuestModel> = dataSource.getQuestById(questId)

    fun updateQuest(quest: QuestModel) {
        dataSource.updateQuest(quest)
    }
}