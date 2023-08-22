package com.example.questify

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun HomepageScreen(
    modifier: Modifier = Modifier
){
    Surface (
        modifier=  modifier.testTag("Homepage Screen")
    ) {

    }
}
