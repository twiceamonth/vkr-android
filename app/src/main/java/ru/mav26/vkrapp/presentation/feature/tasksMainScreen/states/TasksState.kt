package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.states

import ru.mav26.vkrapp.domain.model.task.Habit
import ru.mav26.vkrapp.domain.model.task.HabitDetails
import ru.mav26.vkrapp.domain.model.task.Task
import ru.mav26.vkrapp.domain.model.task.TaskDetails

data class TasksState(
    val tasks: List<Task> = emptyList(),
    val habits: List<Habit> = emptyList(),

    val currentTaskDetails: TaskDetails? = null,
    val currentHabitDetails: HabitDetails? = null
)
