package com.example.questify.data

import com.example.questify.QuestModel
import java.lang.IllegalArgumentException
import java.util.UUID

class QuestDataSourceLocal {

    private val quests: MutableList<QuestModel> = mutableListOf()

    fun addQuest(quest: QuestModel) {
        quests.add(quest)
    }

    fun getQuestById(id: UUID): QuestModel {
        quests.forEach {
            if (it.id == id) {
                return it
            }
        }
        throw IllegalArgumentException("No Quest with provided ID found!")
    }

    fun getNumberOfQuests() = quests.size
}