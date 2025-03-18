package ru.mav26.vkrapp.domain.repository

import ru.mav26.vkrapp.domain.model.task.DetailsCreate
import ru.mav26.vkrapp.domain.model.task.DetailsEdit
import ru.mav26.vkrapp.domain.model.task.Habit
import ru.mav26.vkrapp.domain.model.task.HabitCreate
import ru.mav26.vkrapp.domain.model.task.HabitDetails
import ru.mav26.vkrapp.domain.model.task.HabitEdit
import ru.mav26.vkrapp.domain.model.task.Task
import ru.mav26.vkrapp.domain.model.task.TaskCreate
import ru.mav26.vkrapp.domain.model.task.TaskDetails
import ru.mav26.vkrapp.domain.model.task.TaskEdit

interface TaskRepository {
    suspend fun getTasksList() : List<Task>

    suspend  fun getHabitsList() : List<Habit>

    suspend fun getHabitDetails(id: String) : HabitDetails

    suspend fun getTaskDetails(id: String) : TaskDetails

    suspend fun createTask(task: TaskCreate)

    suspend fun createHabit(habit: HabitCreate)

    suspend fun createSubtask(subtask: String, taskId: String)

    suspend fun createDetails(details: DetailsCreate, taskId: String)

    suspend fun editTask(newTask: TaskEdit, taskId: String)

    suspend fun editSubtask(newSubtask: String, subtaskId: String)

    suspend fun editHabit(newHabit: HabitEdit, habitId: String)

    suspend fun editDetails(newDetails: DetailsEdit, detailsId: String)

    suspend fun deleteTask(taskId: String)

    suspend fun deleteHabit(habitId: String)

    suspend fun deleteSubtask(subtaskId: String)

    suspend fun deleteDetails(detailsId: String)
}