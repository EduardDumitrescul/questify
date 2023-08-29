package com.example.questify.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.questify.ui.homepage.HomepageScreen
import com.example.questify.ui.homepage.HomepageViewModel

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
            HomepageScreen(viewModel = viewmodel)
        }
    }
}
