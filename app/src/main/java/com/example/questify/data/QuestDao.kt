package com.example.questify.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.questify.data.entities.EntryEntity
import com.example.questify.data.entities.QuestCompleteEntity
import com.example.questify.data.entities.QuestEntity
import java.util.UUID

@Dao
interface QuestDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addQuest(quest: QuestEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addEntry(entry: EntryEntity)

    @Query("select * from questentity where id = :id")
    fun getQuestById(id: UUID): LiveData<QuestCompleteEntity>

    @Query("select * from questentity")
    fun getQuests(): LiveData<List<QuestCompleteEntity>>

    @Delete
    fun deleteQuest(quest: QuestEntity)

    @Query("select count(*) from questentity")
    fun getNumberOfQuests(): Int

}