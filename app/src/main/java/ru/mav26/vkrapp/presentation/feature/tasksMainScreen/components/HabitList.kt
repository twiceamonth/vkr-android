package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mav26.vkrapp.domain.model.task.HabitEdit
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.viewmodels.TaskViewModel
import java.time.Duration
import java.time.OffsetDateTime

@Composable
fun HabitList(
    taskViewModel: TaskViewModel,
    modifier: Modifier = Modifier,
) {
    val state by taskViewModel.state.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(state.habits) { habit ->
            val isDone = habit.lastPerformed?.let { IsDone(it) } ?: false

            HabitCard(
                habit = habit,
                done = isDone,
                onStatusChange = {
                    taskViewModel.editHabit(
                        HabitEdit(
                            lastPerformedAt = OffsetDateTime.now(),
                            streakCount = habit.streakCount + 1
                        ), habit.habitId
                    )
                    taskViewModel.getHabits()
                },
                onTimerStart = { /*TODO*/ },
                onCardClick = { /*TODO*/ }
            )

            Spacer(Modifier.height(12.dp))
        }
    }
}

fun IsDone(targetTime: OffsetDateTime): Boolean {
    val now = OffsetDateTime.now()

    // Сначала проверяем совпадение дат (день, месяц, год)
    if (now.toLocalDate() != targetTime.toLocalDate()) return false

    // Проверяем что разница меньше 24 часов
    val duration = Duration.between(now, targetTime).abs()
    return duration < Duration.ofDays(1)
}