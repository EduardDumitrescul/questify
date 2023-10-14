package com.example.questify.hilt

import android.content.Context
import androidx.room.Room
import com.example.questify.data.QuestDao
import com.example.questify.data.QuestDataSourceDatabase
import com.example.questify.data.QuestDatabase
import com.example.questify.data.QuestRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideQuestDao(questDatabase: QuestDatabase): QuestDao {
        return questDatabase.questDao()
    }

    @Provides
    @Singleton
    fun provideQuestDatabase(@ApplicationContext appContext: Context): QuestDatabase {
        return Room.databaseBuilder(
            appContext,
            QuestDatabase::class.java,
            "QuestDatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun provideQuestDataSourceDatabase(questDao: QuestDao): QuestRepository {
        val dataSource = QuestDataSourceDatabase(questDao)
        return dataSource
    }
}