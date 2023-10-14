package com.example.questify.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.questify.data.models.EntryModel
import com.example.questify.data.models.QuestModel
import java.lang.IllegalArgumentException
import java.util.UUID

class QuestDataSourceLocal: QuestRepository {

    private var quests: MutableLiveData<List<QuestModel>> = MutableLiveData(listOf())

    override fun addQuest(quest: QuestModel) {
        quests.value = quests.value?.plus(quest)
    }

    override fun addEntry(questId: UUID, entry: EntryModel) {
        // TODO
    }

    override fun getQuestById(id: UUID): LiveData<QuestModel> {
        quests.value?.forEach {
            if (it.id == id) {
                return MutableLiveData(it)
            }
        }
        throw IllegalArgumentException("No Quest with provided ID found!")
    }

    override fun getQuests(): LiveData<List<QuestModel>> {
        return quests
    }

    override fun deleteQuest(quest: QuestModel) {
        quests.value = quests.value?.minus(quest)
    }

    override fun getNumberOfQuests() = quests.value?.size ?: 0
}