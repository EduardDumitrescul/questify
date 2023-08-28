package com.example.questify.ui.homepage

import androidx.lifecycle.ViewModel
import com.example.questify.data.QuestRepository

class HomepageViewModel(dataSource: QuestRepository): ViewModel() {

    val quests = dataSource.getQuests()
}