package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.mav26.vkrapp.domain.model.task.Subtask
import ru.mav26.vkrapp.domain.model.task.Task
import java.time.LocalTime
import java.time.OffsetDateTime

class FakeVM : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _tasks.value = listOf(
                Task(
                    taskId = "1",
                    title = "Помыть посуду",
                    status = false,
                    subtasks = listOf(
                        Subtask("1-1", "Собрать вилки", false, "1"),
                        Subtask("1-2", "Помыть тарелки", false, "1")
                    ),
                    endTime = null,
                    difficulty ="easy",
                    priority = "low",
                    frequency = "day",
                    timerInterval = null,
                    description = "TODO()"
                ),
                Task(
                    taskId = "2",
                    title = "Сделать уроки",
                    status = true,
                    subtasks = listOf(
                        Subtask("2-1", "Математика", false, "2"),
                        Subtask("2-2", "Физика", true, "2")
                    ),
                    endTime = OffsetDateTime.now(),
                    difficulty ="easy",
                    priority = "low",
                    frequency = "day",
                    timerInterval = LocalTime.of(0, 2, 12),
                    description = "TODO()"
                )
            )
        }
    }

    fun test(task1: Task, new: Boolean) {
        viewModelScope.launch {
            delay(2000)
            _tasks.update { ct ->
                ct.map { task ->
                    if (task.taskId == task1.taskId) {
                        task.copy(status = new)
                    } else {
                        task
                    }
                }
            }
        }
    }

    fun updateTaskStatus(taskId: String, newStatus: Boolean) {
        viewModelScope.launch {
            _tasks.update { currentTasks ->
                currentTasks.map { task ->
                    if (task.taskId == taskId) {
                        task.copy(status = newStatus)
                    } else {
                        task
                    }
                }
            }
        }
    }

    fun updateSubtaskStatus(taskId: String, subtaskId: String, newStatus: Boolean) {
        viewModelScope.launch {
            _tasks.update { currentTasks ->
                currentTasks.map { task ->
                    if (task.taskId == taskId) {
                        val updatedSubtasks = task.subtasks.map { subtask ->
                            if (subtask.subtaskId == subtaskId) {
                                subtask.copy(status = newStatus)
                            } else {
                                subtask
                            }
                        }
                        task.copy(subtasks = updatedSubtasks)
                    } else {
                        task
                    }
                }
            }
        }
    }
}

private fun <T> MutableStateFlow<T>.update(transform: (T) -> T) {
    this.value = transform(this.value)
}