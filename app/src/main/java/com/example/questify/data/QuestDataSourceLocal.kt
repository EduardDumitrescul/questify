package com.example.questify.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.questify.QuestModel
import java.lang.IllegalArgumentException
import java.util.UUID

class QuestDataSourceLocal {

    private val quests: MutableList<QuestModel> = mutableListOf()

    fun addQuest(quest: QuestModel) {
        quests.add(quest)
    }

    fun getQuestById(id: UUID): LiveData<QuestModel> {
        quests.forEach {
            if (it.id == id) {
                return MutableLiveData(it)
            }
        }
        throw IllegalArgumentException("No Quest with provided ID found!")
    }

    fun getQuests(): LiveData<List<QuestModel>> {
        return MutableLiveData(quests)
    }

    fun deleteQuest(quest: QuestModel) {
        quests.remove(quest)
    }

    fun getNumberOfQuests() = quests.size
}