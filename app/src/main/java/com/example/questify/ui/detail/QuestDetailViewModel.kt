package com.example.questify.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.questify.data.models.EntryModel
import com.example.questify.data.QuestRepository
import com.example.questify.data.models.Status
import com.example.questify.navigation.QuestDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class QuestDetailViewModel
@Inject constructor(
    val dataSource: QuestRepository,
    state: SavedStateHandle
): ViewModel(){
    val quest = dataSource.getQuestById(UUID.fromString(state[QuestDetail.questIdArg]))
    var showCompletionDialog = MutableStateFlow(false)
        private set

    fun performRep() {
        quest.value?.id?.let {
            dataSource.addEntry(
                questId = it,
                EntryModel()
            )
        }

        checkCompletion()
    }

    fun checkCompletion() {
        if(quest.value?.status == Status.COMPLETED) {
            showCompletionDialog.value = true
        }
    }

    fun closeCompletionDialog() {
        showCompletionDialog.value = false
    }
}