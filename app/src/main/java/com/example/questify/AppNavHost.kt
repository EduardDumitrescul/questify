package com.example.questify

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.questify.ui.QuestEditScreen
import java.util.UUID

private const val TAG = "APPNAVHOST"

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Overview.route,
        modifier = modifier,
    ) {
        composable(Overview.route) {
            OverviewScreen(
                navigateToQuestEdit = { questId ->
                    Log.d(TAG, "overview screen arg")
                    navController.navigateToQuestEdit(questId)
                }
            )
        }
        composable(
            route = QuestEdit.routeWithArgument,
            arguments = QuestEdit.arguments
        ) {
            QuestEditScreen(
                navigateUp = { navController.navigateUp() }
            )
        }
    }

}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }

private fun NavHostController.navigateToQuestEdit(questId: UUID) {
    Log.d(TAG, "navigateToQuestEdit")
    this.navigateSingleTopTo("${QuestEdit.route}/$questId")
}