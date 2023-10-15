package com.example.questify.ui.create

import androidx.lifecycle.ViewModel
import com.example.questify.data.QuestRepository
import com.example.questify.data.models.QuestModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class QuestCreateViewModel @Inject constructor(
    val dataSource: QuestRepository,
): ViewModel() {
    var screenIndex = MutableStateFlow(0)
    private var quest = MutableStateFlow(QuestModel())

    fun setName(name: String) {
        quest.value.name = name
    }

    fun setDescription(description: String) {
        quest.value.description = description
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