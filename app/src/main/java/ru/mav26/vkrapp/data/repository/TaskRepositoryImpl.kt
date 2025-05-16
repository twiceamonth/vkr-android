package ru.mav26.vkrapp.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import ru.mav26.vkrapp.data.remote.mappers.fromApi
import ru.mav26.vkrapp.data.remote.mappers.toApi
import ru.mav26.vkrapp.data.remote.models.tasks.HabitDetailsResponse
import ru.mav26.vkrapp.data.remote.models.tasks.HabitListResponse
import ru.mav26.vkrapp.data.remote.models.tasks.SubtaskCreate
import ru.mav26.vkrapp.data.remote.models.tasks.TaskDetailsResponse
import ru.mav26.vkrapp.data.remote.models.tasks.TasksListResponse
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

class TaskRepositoryImpl(
    private val client: HttpClient
) : TaskRepository {
    override suspend fun getTasksList(): List<Task> {
        val response: List<TasksListResponse> =
            client.get("/tasks-list").body()
        return response.map { it.fromApi() }
    }

    override suspend fun getHabitsList(): List<Habit> {
        val response: List<HabitListResponse> =
            client.get("/habit-list").body()
        return response.map { it.fromApi() }
    }

    override suspend fun getHabitDetails(id: String): HabitDetails {
        val response: HabitDetailsResponse = client.get("/habit-details/${id}").body()
        return response.fromApi()
    }

    override suspend fun getTaskDetails(id: String): TaskDetails {
        val response: TaskDetailsResponse = client.get("/task-details/${id}").body()
        return response.fromApi()
    }

    override suspend fun createTask(task: TaskCreate) {
        val response = client.post("/new-task") {
            contentType(ContentType.Application.Json)
            setBody(task.toApi())
        }
    }

    override suspend fun createHabit(habit: HabitCreate) {
        val response = client.post("/new-habit") {
            contentType(ContentType.Application.Json)
            setBody(habit.toApi())
        }
    }

    override suspend fun createSubtask(subtask: String, taskId: String) {
        val response = client.post("/new-subtask/${taskId}") {
            contentType(ContentType.Application.Json)
            setBody(SubtaskCreate(subtask))
        }
    }

    override suspend fun createDetails(details: DetailsCreate, taskId: String) {
        val response = client.post("/new-details/${taskId}") {
            contentType(ContentType.Application.Json)
            setBody(details.toApi())
        }
    }

    override suspend fun editTask(newTask: TaskEdit, taskId: String) {
        val response = client.patch("/edit-task/${taskId}") {
            contentType(ContentType.Application.Json)
            setBody(newTask.toApi())
        }
    }

    override suspend fun editSubtask(newSubtask: SubtaskEdit, subtaskId: String) {
        val response = client.patch("/edit-subtask/${subtaskId}") {
            contentType(ContentType.Application.Json)
            setBody(newSubtask.toApi())
        }
    }

    override suspend fun editHabit(newHabit: HabitEdit, habitId: String) {
        val response = client.patch("/edit-habit/${habitId}") {
            contentType(ContentType.Application.Json)
            setBody(newHabit.toApi())
        }
    }

    override suspend fun editDetails(newDetails: DetailsEdit, detailsId: String) {
        val response = client.patch("/edit-details/${detailsId}") {
            contentType(ContentType.Application.Json)
            setBody(newDetails.toApi())
        }
    }

    override suspend fun deleteTask(taskId: String) {
        val response = client.delete("/delete-task/${taskId}")
    }

    override suspend fun deleteHabit(habitId: String) {
        val response = client.delete("/delete-habit/${habitId}")
    }

    override suspend fun deleteSubtask(subtaskId: String) {
        val response = client.delete("/delete-subtask/${subtaskId}")
    }

    override suspend fun deleteDetails(detailsId: String) {
        val response = client.delete("/delete-details/${detailsId}")
    }

}