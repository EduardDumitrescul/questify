package com.example.questify.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.questify.data.models.EntryModel
import com.example.questify.data.QuestRepository
import com.example.questify.navigation.QuestDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class QuestDetailViewModel
@Inject constructor(
    val dataSource: QuestRepository,
    state: SavedStateHandle
): ViewModel(){
    val quest = dataSource.getQuestById(UUID.fromString(state[QuestDetail.questIdArg]))

    fun performRep() {
        quest.value?.id?.let {
            dataSource.addEntry(
                questId = it,
                EntryModel()
            )
        }
    }
}