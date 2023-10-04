package com.example.questify.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.questify.ui.detail.QuestDetailScreen
import com.example.questify.ui.detail.QuestDetailViewModel
import com.example.questify.ui.homepage.HomepageScreen
import com.example.questify.ui.homepage.HomepageViewModel
import java.util.UUID

@Composable
fun QuestifyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Homepage.route,
        modifier = modifier
    ) {
        composable(route = Homepage.route) {
            val viewmodel = hiltViewModel<HomepageViewModel>()
            HomepageScreen(
                navigateToQuestDetailScreen = {
                    navController.navigateToQuestDetailScreen(it)
                },
                viewModel = viewmodel
            )
        }

        composable(
            route = QuestDetail.routeWithArgs,
            arguments = QuestDetail.arguments
        ) { navBackStackEntry ->
            //val questId = navBackStackEntry.arguments?.getString(QuestDetail.questTypeArg)
            val viewModel: QuestDetailViewModel = hiltViewModel()
            QuestDetailScreen(viewModel = viewModel)
        }
    }
}

private fun NavHostController.navigateToQuestDetailScreen(questId: UUID) {
    this.navigate("${QuestDetail.route}/$questId")
}