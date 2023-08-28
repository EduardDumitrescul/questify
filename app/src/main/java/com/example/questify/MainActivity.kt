package com.example.questify

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.questify.ui.homepage.HomepageScreen
import com.example.questify.ui.theme.QuestifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("DASDAs", "ASDASDA")
        setContent {
            QuestifyTheme {
                HomepageScreen()

            }
        }
    }
}
