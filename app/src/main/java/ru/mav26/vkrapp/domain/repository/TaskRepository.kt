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
    fun getTasksList() : List<Task>

    fun getHabitsList() : List<Habit>

    fun getHabitDetails(id: String) : HabitDetails

    fun getTaskDetails(id: String) : TaskDetails

    fun createTask(task: TaskCreate)

    fun createHabit(habit: HabitCreate)

    fun createSubtask(subtask: String, taskId: String)

    fun createDetails(details: DetailsCreate, taskId: String)

    fun editTask(newTask: TaskEdit, taskId: String)

    fun editSubtask(newSubtask: String, subtaskId: String)

    fun editHabit(newHabit: HabitEdit, habitId: String)

    fun editDetails(newDetails: DetailsEdit, detailsId: String)

    fun deleteTask(taskId: String)

    fun deleteHabit(habitId: String)

    fun deleteSubtask(subtaskId: String)

    fun deleteDetails(detailsId: String)
}