package com.example.questify.data

import androidx.lifecycle.LiveData
import com.example.questify.QuestModel
import java.util.UUID

interface QuestRepository {
    fun addQuest(quest: QuestModel)

    fun getQuestById(id: UUID): LiveData<QuestModel>

    fun getQuests(): LiveData<List<QuestModel>>

    fun deleteQuest(quest: QuestModel)

    fun getNumberOfQuests(): Int
}