package com.example.questify.data

import androidx.lifecycle.LiveData
import com.example.questify.data.models.EntryModel
import com.example.questify.data.models.QuestModel
import java.util.UUID

interface QuestRepository {
    fun addQuest(quest: QuestModel)

    fun addEntry(questId: UUID, entry: EntryModel)

    fun getQuestById(id: UUID): LiveData<QuestModel>

    fun getQuests(): LiveData<List<QuestModel>>

    fun deleteQuest(quest: QuestModel)

    fun getNumberOfQuests(): Int
}