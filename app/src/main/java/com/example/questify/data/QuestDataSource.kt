package com.example.questify.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.UUID

class QuestDataSource: QuestRepository {
    private val quests:MutableList<QuestModel> = mutableListOf(
        QuestModel(name = "Quest 1"),
        QuestModel(name = "Quest 2"),
        QuestModel(name = "Quest 3"),
        QuestModel(name = "Quest 4"),
        QuestModel(name = "Quest 5"),
    )

    override fun getQuestById(id: UUID): LiveData<QuestModel> {
        quests.forEach {
            if (it.id == id) {
                return MutableLiveData(it)
            }
        }
        return MutableLiveData(QuestModel())
    }

    override fun getQuests(): LiveData<List<QuestModel>> {
        return MutableLiveData(quests)
    }

    override fun insertQuest(quest: QuestModel) {
        quests.add(quest)
    }

    override fun removeQuest(quest: QuestModel) {
        quests.remove(quest)
    }

    override fun updateQuest(quest: QuestModel) {
        for(i in 0 until quests.size) {
            if(quests[i].id == quest.id) {
                quests[i] = quest
            }
        }
    }

}