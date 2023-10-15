package com.example.questify.ui.create

import androidx.lifecycle.ViewModel
import com.example.questify.data.QuestRepository
import com.example.questify.data.models.QuestModel
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
    var quest = MutableStateFlow(QuestModel())
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
        quest.value.targetReps = target
    }

    fun setTimeLimit(days: Int) {
        quest.value.timeLimit = days
    }

    fun save() {
        // TODO()
    }
    fun next() {
        screenIndex.value ++
    }

    fun previous() {
        screenIndex.value --
    }
}