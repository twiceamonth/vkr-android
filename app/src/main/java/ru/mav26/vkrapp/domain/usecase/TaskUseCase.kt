package ru.mav26.vkrapp.domain.usecase

import ru.mav26.vkrapp.domain.model.task.DetailsCreate
import ru.mav26.vkrapp.domain.model.task.DetailsEdit
import ru.mav26.vkrapp.domain.model.task.Habit
import ru.mav26.vkrapp.domain.model.task.HabitCreate
import ru.mav26.vkrapp.domain.model.task.HabitDetails
import ru.mav26.vkrapp.domain.model.task.HabitEdit
import ru.mav26.vkrapp.domain.model.task.SubtaskEdit
import ru.mav26.vkrapp.domain.model.task.Task
import ru.mav26.vkrapp.domain.model.task.TaskCreate
import ru.mav26.vkrapp.domain.model.task.TaskDetails
import ru.mav26.vkrapp.domain.model.task.TaskEdit
import ru.mav26.vkrapp.domain.repository.TaskRepository

class TaskUseCase(
    private val taskRepository: TaskRepository
) {
    suspend fun getTasks(userLogin: String) : List<Task> {
        return taskRepository.getTasksList(userLogin)
    }

    suspend fun getHabits(userLogin: String) : List<Habit> {
        return taskRepository.getHabitsList(userLogin)
    }

    suspend fun getTaskDetails(id: String) : TaskDetails {
        return taskRepository.getTaskDetails(id)
    }

    suspend fun getHabitDetails(id: String) : HabitDetails {
        return taskRepository.getHabitDetails(id)
    }

    suspend fun createTask(task: TaskCreate, userLogin: String) {
        return taskRepository.createTask(task, userLogin)
    }

    suspend fun createHabit(habit: HabitCreate, userLogin: String) {
        return taskRepository.createHabit(habit, userLogin)
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

    suspend fun editSubtask(newSubtask: SubtaskEdit, subtaskId: String) {
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