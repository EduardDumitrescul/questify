package com.example.questify.navigation


interface QuestifyDestination {
    val route: String
}

object Homepage: QuestifyDestination {
    override val route = "homepage"
}