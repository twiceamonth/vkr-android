package ru.mav26.vkrapp.presentation.feature.achievements.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mav26.vkrapp.domain.model.achievements.AchievementProgress
import ru.mav26.vkrapp.presentation.feature.achievements.AchievementsViewModel
import ru.mav26.vkrapp.presentation.feature.achievements.components.AchievementCompleteCard
import ru.mav26.vkrapp.presentation.feature.achievements.components.AchievementsProgressCard
import ru.mav26.vkrapp.presentation.theme.accentColor
import ru.mav26.vkrapp.presentation.theme.buttonColor

@Composable
fun AchievementsScreen(
    achievementsViewModel: AchievementsViewModel
) {
    val state by achievementsViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        achievementsViewModel.getAchievementsProgress()
        achievementsViewModel.getAchievementsList()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = "Получено",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        state.achievementsProgress.forEach { a ->
            if (a.isCompleted) {
                AchievementCompleteCard(
                    item = a,
                    color = buttonColor
                )
            }
        }

        Text(
            text = "В процессе",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        state.achievementsProgress.forEach { a ->
            if (!a.isCompleted) {
                AchievementsProgressCard(
                    item = a,
                    color = buttonColor
                )
            }
        }
    }
}