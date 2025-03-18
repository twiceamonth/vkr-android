package ru.mav26.vkrapp.domain.usecase

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
import ru.mav26.vkrapp.domain.repository.TaskRepository

class TaskUseCase(
    private val taskRepository: TaskRepository
) {
    suspend fun getTasks() : List<Task> {
        return taskRepository.getTasksList()
    }

    suspend fun getHabits() : List<Habit> {
        return taskRepository.getHabitsList()
    }

    suspend fun getTaskDetails(id: String) : TaskDetails {
        return taskRepository.getTaskDetails(id)
    }

    suspend fun getHabitDetails(id: String) : HabitDetails {
        return taskRepository.getHabitDetails(id)
    }

    suspend fun createTask(task: TaskCreate) {
        return taskRepository.createTask(task)
    }

    suspend fun createHabit(habit: HabitCreate) {
        return taskRepository.createHabit(habit)
    }

    suspend fun createSubtask(subtask: String, taskId: String) {
        return taskRepository.createSubtask(subtask, taskId)
    }

    suspend fun createDetails(details: DetailsCreate, taskId: String) {
        return taskRepository.createDetails(details, taskId)
    }

    suspend fun editTask(newTask: TaskEdit, taskId: String) {
        return taskRepository.editTask(newTask, taskId)
    }

    suspend fun editSubtask(newSubtask: String, subtaskId: String) {
        return taskRepository.editSubtask(newSubtask, subtaskId)
    }

    suspend fun editHabit(newHabit: HabitEdit, habitId: String) {
        return taskRepository.editHabit(newHabit, habitId)
    }

    suspend fun editDetails(newDetails: DetailsEdit, detailsId: String) {
        return taskRepository.editDetails(newDetails, detailsId)
    }

    suspend fun deleteTask(taskId: String) {
        return taskRepository.deleteTask(taskId)
    }

    suspend fun deleteHabit(habitId: String) {
        return taskRepository.deleteHabit(habitId)
    }

    suspend fun deleteSubtask(subtaskId: String) {
        return taskRepository.deleteSubtask(subtaskId)
    }

    suspend fun deleteDetails(detailsId: String) {
        return taskRepository.deleteDetails(detailsId)
    }
}