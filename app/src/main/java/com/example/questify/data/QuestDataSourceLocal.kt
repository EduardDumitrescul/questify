package com.example.questify.data

import com.example.questify.QuestModel

class QuestDataSourceLocal {

    private val quests: MutableList<QuestModel> = mutableListOf()

    fun addQuest(quest: QuestModel) {
        quests.add(quest)
    }

    fun getNumberOfQuests() = quests.size
}