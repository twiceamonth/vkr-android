package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mav26.vkrapp.domain.model.task.HabitEdit
import ru.mav26.vkrapp.domain.model.task.SubtaskEdit
import ru.mav26.vkrapp.domain.model.task.TaskEdit
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.viewmodels.TaskViewModel
import java.time.Duration
import java.time.LocalTime
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
            val isDone = IsDone(OffsetDateTime.parse(habit.lastPerformed))

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
                },
                onTimerStart = { /*TODO*/ },
                onCardClick = { /*TODO*/ }
            )
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