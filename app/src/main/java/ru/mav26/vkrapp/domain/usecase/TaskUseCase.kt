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
    fun getTasks() : List<Task> {
        return taskRepository.getTasksList()
    }

    fun getHabits() : List<Habit> {
        return taskRepository.getHabitsList()
    }

    fun getTaskDetails(id: String) : TaskDetails {
        return taskRepository.getTaskDetails(id)
    }

    fun getHabitDetails(id: String) : HabitDetails {
        return taskRepository.getHabitDetails(id)
    }

    fun createTask(task: TaskCreate) {
        return taskRepository.createTask(task)
    }

    fun createHabit(habit: HabitCreate) {
        return taskRepository.createHabit(habit)
    }

    fun createSubtask(subtask: String, taskId: String) {
        return taskRepository.createSubtask(subtask, taskId)
    }

    fun createDetails(details: DetailsCreate, taskId: String) {
        return taskRepository.createDetails(details, taskId)
    }

    fun editTask(newTask: TaskEdit, taskId: String) {
        return taskRepository.editTask(newTask, taskId)
    }

    fun editSubtask(newSubtask: String, subtaskId: String) {
        return taskRepository.editSubtask(newSubtask, subtaskId)
    }

    fun editHabit(newHabit: HabitEdit, habitId: String) {
        return taskRepository.editHabit(newHabit, habitId)
    }

    fun editDetails(newDetails: DetailsEdit, detailsId: String) {
        return taskRepository.editDetails(newDetails, detailsId)
    }

    fun deleteTask(taskId: String) {
        return taskRepository.deleteTask(taskId)
    }

    fun deleteHabit(habitId: String) {
        return taskRepository.deleteHabit(habitId)
    }

    fun deleteSubtask(subtaskId: String) {
        return taskRepository.deleteSubtask(subtaskId)
    }

    fun deleteDetails(detailsId: String) {
        return taskRepository.deleteDetails(detailsId)
    }
}