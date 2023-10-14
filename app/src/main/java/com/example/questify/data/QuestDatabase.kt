package com.example.questify.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.questify.data.entities.EntryEntity
import com.example.questify.data.entities.QuestEntity

@Database(entities = [QuestEntity::class, EntryEntity::class], version = 1)
@TypeConverters(LocalDateConverter::class)
abstract class QuestDatabase : RoomDatabase() {

    abstract fun questDao(): QuestDao

}