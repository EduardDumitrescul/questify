package com.example.questify.ui.homepage

import androidx.lifecycle.ViewModel
import com.example.questify.data.QuestDataSourceLocal
import com.example.questify.data.QuestRepository

class HomepageViewModel(dataSource: QuestRepository = QuestDataSourceLocal()): ViewModel() {

    val quests = dataSource.getQuests()
}