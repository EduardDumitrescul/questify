package com.example.questify.hilt

import com.example.questify.QuestModel
import com.example.questify.data.QuestDataSourceLocal
import com.example.questify.data.QuestRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideQuestDataSourceLocal(): QuestRepository {
        val dataSource = QuestDataSourceLocal()
        mutableListOf(
            QuestModel(name = "quest 1"),
            QuestModel(name = "quest 2"),
            QuestModel(name = "quest 3"),
            QuestModel(name = "quest 4"),
            QuestModel(name = "quest 5"),
        ).onEach {
            dataSource.addQuest(it)
        }
        return dataSource
    }
}