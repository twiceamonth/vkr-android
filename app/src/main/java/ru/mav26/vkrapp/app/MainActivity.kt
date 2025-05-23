package ru.mav26.vkrapp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ru.mav26.vkrapp.app.navigation.AppNavigation
import ru.mav26.vkrapp.presentation.theme.VkrappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkrappTheme {
                AppNavigation(
                    navController = rememberNavController(),
                    tokenManager = koinInject(),
                    authViewModel = koinViewModel(),
                    createCharacterViewModel = koinViewModel(),
                    characterViewModel = koinViewModel(),
                    taskViewModel = koinViewModel(),
                    activitiesViewModel = koinViewModel(),
                    storeViewModel = koinViewModel(),
                    achievementsViewModel = koinViewModel(),
                    statisticsViewModel = koinViewModel(),
                    inventoryViewModel = koinViewModel()
                )
            }
        }
    }
}