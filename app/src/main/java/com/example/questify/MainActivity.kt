package com.example.questify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.questify.navigation.QuestifyNavHost
import com.example.questify.ui.theme.QuestifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Questify()
        }
    }
}


@Composable
fun Questify() {
    QuestifyTheme {
        val navController = rememberNavController()
        QuestifyNavHost(
            navController = navController,
        )
    }
}