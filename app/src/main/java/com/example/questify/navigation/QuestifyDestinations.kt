package com.example.questify.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument


interface QuestifyDestination {
    val route: String
}

object Homepage: QuestifyDestination {
    override val route = "homepage"
}

object QuestCreate: QuestifyDestination {
    override val route = "quest_create"
}

object QuestDetail: QuestifyDestination {
    override val route = "quest_detail_screen"
    const val questIdArg = "quest_id"

    val routeWithArgs = "$route/{$questIdArg}"

    val arguments = listOf(
        navArgument(questIdArg) { type = NavType.StringType}
    )
}