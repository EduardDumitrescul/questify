package com.example.questify.data

import androidx.lifecycle.LiveData
import java.util.UUID

interface QuestRepository {
    fun getQuestById(id: UUID): LiveData<QuestModel>
    fun getQuests(): LiveData<List<QuestModel>>
    fun insertQuest(quest: QuestModel)
    fun removeQuest(quest: QuestModel)
    fun updateQuest(quest: QuestModel)
}