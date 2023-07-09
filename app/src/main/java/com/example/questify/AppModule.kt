package com.example.questify

import com.example.questify.data.QuestDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    //private val dataSource: QuestDataSource = QuestDataSource()
    @Provides
    @Singleton
    fun provideQuestDataSource(): QuestDataSource {
        return QuestDataSource()
    }
}