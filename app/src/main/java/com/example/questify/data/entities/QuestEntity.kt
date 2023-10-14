package com.example.questify.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.questify.data.models.QuestModel
import java.time.LocalDate
import java.util.UUID

@Entity
data class QuestEntity(
    @PrimaryKey val id: UUID,
    val name: String,
    val description: String,
    val startDate: LocalDate,
    val timeLimit: Int?,
    val endDate: LocalDate?,
    val targetReps: Int,
)

data class QuestCompleteEntity(
    @Embedded val questEntity: QuestEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "questId"
    )
    val entryEntities: List<EntryEntity>
)

fun QuestModel.toEntity() = QuestEntity(
    id = id,
    name = name,
    description = description,
    startDate = startDate,
    timeLimit = timeLimit,
    endDate = endDate,
    targetReps = targetReps,
)

fun QuestEntity.toModel() = QuestModel(
    id = id,
    name = name,
    description = description,
    startDate = startDate,
    timeLimit = timeLimit,
    endDate = endDate,
    targetReps = targetReps,
)

fun QuestCompleteEntity.toModel() = questEntity.toModel().apply {
    entryEntities.forEach {
        this.addEntry(it.toModel())
    }
}


fun QuestModel.getEntryEntities(): List<EntryEntity> {
    return this.entryList.map { entry ->
        EntryEntity(
            id = entry.id,
            questId = this.id,
            date = entry.date,
        )
    }
}