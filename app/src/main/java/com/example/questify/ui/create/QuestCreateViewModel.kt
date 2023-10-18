package com.example.questify.ui.create

import androidx.lifecycle.ViewModel
import com.example.questify.data.QuestRepository
import com.example.questify.data.models.QuestModel
import com.example.questify.data.models.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class QuestCreateViewModel @Inject constructor(
    val dataSource: QuestRepository,
): ViewModel() {
    var screenIndex = MutableStateFlow(0)
        private set
    var quest = MutableStateFlow(QuestModel(status = Status.ACTIVE))
        private set

    fun setName(name: String) {
        quest.update {
            quest.value.copy(
                name = name
            )
        }
    }

    fun setDescription(description: String) {
        quest.update {
            quest.value.copy(
                description = description
            )
        }
    }

    fun setTarget(target: Int) {
        quest.update {
            quest.value.copy(
                targetReps = target
            )
        }
    }

    fun setTimeLimit(days: Int?) {
        quest.update {
            quest.value.copy(
                timeLimit = days
            )
        }
    }

    fun save() {
        dataSource.addQuest(quest.value)
    }
    fun next() {
        screenIndex.value ++
    }

    fun previous() {
        screenIndex.value --
    }
}