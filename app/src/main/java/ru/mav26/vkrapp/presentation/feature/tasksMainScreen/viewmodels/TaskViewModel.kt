package ru.mav26.vkrapp.presentation.feature.tasksMainScreen.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.mav26.vkrapp.domain.model.task.DetailsCreate
import ru.mav26.vkrapp.domain.model.task.DetailsEdit
import ru.mav26.vkrapp.domain.model.task.HabitCreate
import ru.mav26.vkrapp.domain.model.task.HabitEdit
import ru.mav26.vkrapp.domain.model.task.SubtaskEdit
import ru.mav26.vkrapp.domain.model.task.TaskCreate
import ru.mav26.vkrapp.domain.model.task.TaskEdit
import ru.mav26.vkrapp.domain.usecase.TaskUseCase
import ru.mav26.vkrapp.presentation.feature.tasksMainScreen.states.TasksState

class TaskViewModel(
    private val taskUseCase: TaskUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(TasksState())
    val state: StateFlow<TasksState> = _state

    /*TODO: ADD TIMER STARTER*/

    fun getTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = taskUseCase.getTasks()
            _state.update { it.copy(tasks = list) }
        }
    }

    fun getHabits() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = taskUseCase.getHabits()
            _state.update { it.copy(habits = list) }
        }
    }

    fun getTaskDetails(taskId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val details = taskUseCase.getTaskDetails(taskId)
            _state.update { it.copy(currentTaskDetails = details) }
        }
    }

    fun getHabitDetails(habitId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val details = taskUseCase.getHabitDetails(habitId)
            _state.update { it.copy(currentHabitDetails = details) }
        }
    }

    fun createTask(task: TaskCreate) {
        viewModelScope.launch(Dispatchers.IO) {
            taskUseCase.createTask(task)
        }
    }

    fun createHabit(habit: HabitCreate) {
        viewModelScope.launch(Dispatchers.IO) {
            taskUseCase.createHabit(habit)
        }
    }

    fun createSubtask(subtask: String, taskId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            taskUseCase.createSubtask(subtask, taskId)
        }
    }

    fun createDetails(details: DetailsCreate, taskId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            taskUseCase.createDetails(details, taskId)
        }
    }

    fun editTask(newTask: TaskEdit, taskId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            taskUseCase.editTask(newTask, taskId)
        }
    }

    fun editSubtask(newSubtask: SubtaskEdit, subtaskId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            taskUseCase.editSubtask(newSubtask, subtaskId)
        }
    }

    fun editHabit(newHabit: HabitEdit, habitId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            taskUseCase.editHabit(newHabit, habitId)
        }
    }

    fun editDetails(newDetails: DetailsEdit, detailsId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            taskUseCase.editDetails(newDetails, detailsId)
        }
    }

    fun deleteTask(taskId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            taskUseCase.deleteTask(taskId)
        }
    }

    fun deleteHabit(habitId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            taskUseCase.deleteHabit(habitId)
        }
    }

    fun deleteSubtask(subtaskId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            taskUseCase.deleteSubtask(subtaskId)
        }
    }

    fun deleteDetails(detailsId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            taskUseCase.deleteDetails(detailsId)
        }
    }
}