package com.example.questify.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument


interface QuestifyDestination {
    val route: String
}

object Homepage: QuestifyDestination {
    override val route = "homepage"
}

object QuestDetail: QuestifyDestination {
    override val route = "quest_detail_screen"
    const val questTypeArg = "quest_id"

    val routeWithArgs = "$route/{$questTypeArg}"

    val arguments = listOf(
        navArgument(questTypeArg) { type = NavType.StringType}
    )
}