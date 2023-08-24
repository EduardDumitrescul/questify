package com.example.questify.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.questify.QuestModel
import java.lang.IllegalArgumentException
import java.util.UUID

class QuestDataSourceLocal: QuestRepository {

    private val quests: MutableList<QuestModel> = mutableListOf()

    override fun addQuest(quest: QuestModel) {
        quests.add(quest)
    }

    override fun getQuestById(id: UUID): LiveData<QuestModel> {
        quests.forEach {
            if (it.id == id) {
                return MutableLiveData(it)
            }
        }
        throw IllegalArgumentException("No Quest with provided ID found!")
    }

    override fun getQuests(): LiveData<List<QuestModel>> {
        return MutableLiveData(quests)
    }

    override fun deleteQuest(quest: QuestModel) {
        quests.remove(quest)
    }

    override fun getNumberOfQuests() = quests.size
}