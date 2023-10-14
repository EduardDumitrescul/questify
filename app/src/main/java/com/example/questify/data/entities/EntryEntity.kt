package com.example.questify.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.example.questify.data.models.EntryModel
import java.time.LocalDate
import java.util.UUID

@Entity(
    foreignKeys = [ForeignKey(
        entity = QuestEntity::class,
        childColumns = ["questId"],
        parentColumns = ["id"],
        onDelete = CASCADE,
    )]
)
data class EntryEntity(
    @PrimaryKey val id: UUID,
    val questId: UUID,
    var date: LocalDate
)

//fun EntryModel.toEntity() = EntryEntity(id, date)

fun EntryEntity.toModel() = EntryModel(id, date)