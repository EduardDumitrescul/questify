package com.example.questify.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface NavDestination {
    val icon: ImageVector
    val route: String
}

object Overview: NavDestination {
    override val icon: ImageVector
        get() = Icons.Outlined.Home
    override val route: String
        get() = "overview"
}

object QuestEdit: NavDestination {
    override val icon: ImageVector
        get() = Icons.Outlined.Edit
    override val route: String
        get() = "edit_quest"

    const val questIdArg = "quest_id"
    val routeWithArgument: String
        get() = "$route/{$questIdArg}"
    val arguments = listOf(navArgument(questIdArg) { type = NavType.StringType})
}