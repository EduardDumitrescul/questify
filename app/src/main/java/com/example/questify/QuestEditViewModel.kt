package com.example.questify

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.questify.data.QuestDataSource
import com.example.questify.data.QuestModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class QuestEditViewModel @Inject constructor (
    private val dataSource: QuestDataSource,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val questId: UUID = UUID.fromString(savedStateHandle.get<String>(QuestEdit.questIdArg)!!)
    val questModel: LiveData<QuestModel> = dataSource.getQuestById(questId)

    fun updateQuest(quest: QuestModel) {
        dataSource.updateQuest(quest)
    }
}