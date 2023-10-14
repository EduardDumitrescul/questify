package com.example.questify.hilt

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
//    @Provides
//    @Singleton
//    fun provideQuestDataSourceLocal(): QuestRepository {
//        val dataSource = QuestDataSourceLocal()
//        mutableListOf(
//            QuestModel(
//                name = "quest 1",
//                startDate = LocalDate.now(),
//                timeLimit = 30,
//                entryList = mutableListOf(
//                    EntryModel(date = LocalDate.now().minusDays(9)),
//                    EntryModel(date = LocalDate.now().minusDays(8)),
//                    EntryModel(date = LocalDate.now().minusDays(6)),
//                    EntryModel(date = LocalDate.now().minusDays(4)),
//                    EntryModel(date = LocalDate.now().minusDays(3)),
//                    EntryModel(date = LocalDate.now().minusDays(2)),
//                    EntryModel(date = LocalDate.now().minusDays(1)),
//                )),
//            QuestModel(name = "quest 2"),
//            QuestModel(name = "quest 3"),
//            QuestModel(name = "quest 4"),
//            QuestModel(name = "quest 5"),
//        ).onEach {
//            dataSource.addQuest(it)
//        }
//        return dataSource
//    }
}