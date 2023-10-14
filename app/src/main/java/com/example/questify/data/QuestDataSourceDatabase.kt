package com.example.questify.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.questify.data.entities.EntryEntity
import com.example.questify.data.entities.getEntryEntities
import com.example.questify.data.entities.toEntity
import com.example.questify.data.entities.toModel
import com.example.questify.data.models.EntryModel
import com.example.questify.data.models.QuestModel
import java.util.UUID
import java.util.concurrent.Executors
import javax.inject.Inject

class QuestDataSourceDatabase
@Inject constructor(private val questDao: QuestDao):
QuestRepository {

    private val executor = Executors.newSingleThreadExecutor()
    override fun addQuest(quest: QuestModel) {
        executor.execute {
            val entity = quest.toEntity()
            questDao.addQuest(entity)
            val entryEntities = quest.getEntryEntities()
            entryEntities.forEach {
                questDao.addEntry(it)
            }
        }

    }

    override fun addEntry(questId: UUID, entry: EntryModel) {
        executor.execute {
            val entity = EntryEntity(
                id = entry.id,
                questId = questId,
                date = entry.date,
            )
            questDao.addEntry(entity)
        }
    }

    override fun getQuestById(id: UUID): LiveData<QuestModel> {
        val model = questDao.getQuestById(id).map {
            it.toModel()
        }
        return model
    }

    override fun getQuests(): LiveData<List<QuestModel>> {
        val quests = questDao.getQuests().map { list ->
            list.map { entity ->
                entity.toModel()
            }
        }
        return quests
    }

    override fun deleteQuest(quest: QuestModel) {
        executor.execute {
            val entity = quest.toEntity()
            questDao.deleteQuest(entity)
        }

    }

    override fun getNumberOfQuests(): Int {
        return questDao.getNumberOfQuests()
    }
}