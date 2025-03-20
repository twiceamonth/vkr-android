package ru.mav26.vkrapp.data.remote.mappers

import ru.mav26.vkrapp.data.remote.models.tasks.DetailsResponse
import ru.mav26.vkrapp.data.remote.models.tasks.DifficultyResponse
import ru.mav26.vkrapp.data.remote.models.tasks.FrequencyResponse
import ru.mav26.vkrapp.data.remote.models.tasks.HabitDetailsResponse
import ru.mav26.vkrapp.data.remote.models.tasks.HabitListResponse
import ru.mav26.vkrapp.data.remote.models.tasks.PriorityResponse
import ru.mav26.vkrapp.data.remote.models.tasks.SubtaskCreate
import ru.mav26.vkrapp.data.remote.models.tasks.SubtaskResponse
import ru.mav26.vkrapp.data.remote.models.tasks.TaskDetailsResponse
import ru.mav26.vkrapp.data.remote.models.tasks.TasksListResponse
import ru.mav26.vkrapp.domain.model.task.Details
import ru.mav26.vkrapp.domain.model.task.DetailsCreate
import ru.mav26.vkrapp.domain.model.task.DetailsEdit
import ru.mav26.vkrapp.domain.model.task.Difficulty
import ru.mav26.vkrapp.domain.model.task.Frequency
import ru.mav26.vkrapp.domain.model.task.Habit
import ru.mav26.vkrapp.domain.model.task.HabitCreate
import ru.mav26.vkrapp.domain.model.task.HabitDetails
import ru.mav26.vkrapp.domain.model.task.HabitEdit
import ru.mav26.vkrapp.domain.model.task.Priority
import ru.mav26.vkrapp.domain.model.task.Subtask
import ru.mav26.vkrapp.domain.model.task.SubtaskEdit
import ru.mav26.vkrapp.domain.model.task.Task
import ru.mav26.vkrapp.domain.model.task.TaskCreate
import ru.mav26.vkrapp.domain.model.task.TaskDetails
import ru.mav26.vkrapp.domain.model.task.TaskEdit
import java.time.LocalTime
import java.time.OffsetDateTime

fun DetailsResponse.fromApi(): Details {
    return Details(
        detailsId = this.detailsId,
        linkUrl = this.linkUrl,
        linkName = this.linkName
    )
}

fun DifficultyResponse.fromApi(): Difficulty {
    return Difficulty(
        difficultyName = this.difficultyName,
        multiplier = this.multiplier
    )
}

fun FrequencyResponse.fromApi(): Frequency {
    return Frequency(
        frequencyName = this.frequencyName
    )
}

fun PriorityResponse.fromApi(): Priority {
    return Priority(
        priorityName = this.priorityName,
        multiplier = this.multiplier
    )
}

fun HabitListResponse.fromApi(): Habit {
    return Habit(
        habitId = this.habitId,
        title = this.title,
        difficulty = this.difficulty,
        frequency = this.frequency,
        timerInterval = LocalTime.parse(this.timerInterval),
        description = this.description,
        streakCount = this.streakCount
    )
}

fun HabitDetailsResponse.fromApi(): HabitDetails {
    return HabitDetails(
        habitId = this.habitId,
        title = this.title,
        difficulty = this.difficulty.fromApi(),
        frequency = this.frequency.fromApi(),
        timerInterval = LocalTime.parse(this.timerInterval),
        description = this.description,
        createdAt = OffsetDateTime.parse(this.createdAt),
        streakCount = this.streakCount,
        lastPerformedAt = OffsetDateTime.parse(this.lastPerformedAt)
    )
}

fun SubtaskResponse.fromApi(): Subtask {
    return Subtask(
        subtaskId = this.subtaskId,
        title = this.title,
        status = this.status,
        taskId = this.taskId
    )
}

fun TasksListResponse.fromApi(): Task {
    return Task(
        taskId = this.taskId,
        title = this.title,
        endTime = OffsetDateTime.parse(this.endTime),
        difficulty = this.difficulty,
        priority = this.priority,
        frequency = this.frequency,
        status = this.status,
        timerInterval = LocalTime.parse(this.timerInterval),
        description = this.description,
        subtasks = this.subtasks.map { it.fromApi() }
    )
}

fun TaskDetailsResponse.fromApi(): TaskDetails {
    return TaskDetails(
        taskId = this.taskId,
        title = this.title,
        endTime = OffsetDateTime.parse(this.endTime),
        difficulty = this.difficulty.fromApi(),
        priority = this.priority.fromApi(),
        frequency = this.frequency.fromApi(),
        details = this.details.map { it.fromApi() },
        status = this.status,
        timerInterval = LocalTime.parse(this.timerInterval),
        description = this.description,
        createdAt = OffsetDateTime.parse(this.createdAt),
        finishedAt = OffsetDateTime.parse(this.finishedAt),
        subtasks = this.subtasks.map { it.fromApi() }
    )
}

fun DetailsCreate.toApi(): ru.mav26.vkrapp.data.remote.models.tasks.DetailsCreate {
    return ru.mav26.vkrapp.data.remote.models.tasks.DetailsCreate(
        linkUrl = this.linkUrl,
        linkName = this.linkName
    )
}

fun DetailsEdit.toApi(): ru.mav26.vkrapp.data.remote.models.tasks.DetailsEdit {
    return ru.mav26.vkrapp.data.remote.models.tasks.DetailsEdit(
        linkUrl = this.linkUrl,
        linkName = this.linkName
    )
}

fun Details.toApi(): DetailsResponse {
    return DetailsResponse(
        detailsId = this.detailsId,
        linkUrl = this.linkUrl,
        linkName = this.linkName
    )
}

fun HabitCreate.toApi(): ru.mav26.vkrapp.data.remote.models.tasks.HabitCreate {
    return ru.mav26.vkrapp.data.remote.models.tasks.HabitCreate(
        title = this.title,
        difficulty = this.difficulty,
        frequency = this.frequency,
        timerInterval = this.timerInterval,
        description = this.description,
        userLogin = this.userLogin
    )
}

fun HabitEdit.toApi(): ru.mav26.vkrapp.data.remote.models.tasks.HabitEdit {
    return ru.mav26.vkrapp.data.remote.models.tasks.HabitEdit(
        userLogin = this.userLogin,
        title = this.title,
        difficulty = this.difficulty,
        frequency = this.frequency,
        timerInterval = this.title,
        description = this.description,
        streakCount = this.streakCount,
        lastPerformedAt = this.lastPerformedAt.toString()
    )
}

fun SubtaskEdit.toApi(): ru.mav26.vkrapp.data.remote.models.tasks.SubtaskEdit {
    return ru.mav26.vkrapp.data.remote.models.tasks.SubtaskEdit(
        title = this.title,
        status = this.status
    )
}

fun TaskCreate.toApi(): ru.mav26.vkrapp.data.remote.models.tasks.TaskCreate {
    return ru.mav26.vkrapp.data.remote.models.tasks.TaskCreate(
        title = this.title,
        endTime = this.endTime.toString(),
        difficulty = this.difficulty,
        priority = this.priority,
        frequency = this.frequency,
        details = this.details.map { it.toApi() },
        timerInterval = this.timerInterval.toString(),
        description = this.description,
        subtasks = this.subtasks,
        userLogin = this.userLogin
    )
}

fun TaskEdit.toApi(): ru.mav26.vkrapp.data.remote.models.tasks.TaskEdit {
    return ru.mav26.vkrapp.data.remote.models.tasks.TaskEdit(
        title = this.title,
        endTime = this.endTime.toString(),
        difficulty = this.difficulty,
        priority = this.priority,
        frequency = this.frequency,
        status = this.status,
        timerInterval = this.timerInterval.toString(),
        description = this.description,
        finishedAt = this.finishedAt.toString(),
        subtasks = this.subtasks.map { SubtaskCreate(title = it) },
        userLogin = this.userLogin
    )
}