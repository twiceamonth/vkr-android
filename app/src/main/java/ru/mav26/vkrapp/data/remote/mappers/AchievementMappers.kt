package ru.mav26.vkrapp.data.remote.mappers

import ru.mav26.vkrapp.data.remote.models.achievements.AchievementProgressResponse
import ru.mav26.vkrapp.data.remote.models.achievements.AchievementResponse
import ru.mav26.vkrapp.domain.model.achievements.Achievement
import ru.mav26.vkrapp.domain.model.achievements.AchievementProgress
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.util.Date
import java.util.Locale

fun String.toDateOrNull(): Date? {
    return try {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        formatter.parse(this)
    } catch (e: Exception) {
        null
    }
}

fun AchievementProgressResponse.fromApi() : AchievementProgress {
    return AchievementProgress(
        userLogin = this.userLogin,
        achievementId = this.achievementId,
        progressId = this.progressId,
        title = this.title,
        description = this.description,
        resetOnEvent = this.resetOnEvent,
        resentEventType = this.resentEventType,
        criteriaType = this.criteriaType,
        criteriaValue = this.criteriaValue,
        progressValue = this.progressValue,
        isCompleted = this.isCompleted,
        completeDate = if(this.completeDate != null && this.completeDate != "null") this.completeDate.toDateOrNull() else null,
        imagePath = this.imagePath
    )
}

fun AchievementResponse.fromApi() : Achievement {
    return Achievement(
        achievementId = this.achievementId,
        title = this.title,
        description = this.description,
        resetOnEvent = this.resetOnEvent,
        resentEventType = this.resentEventType,
        criteriaType = this.resentEventType,
        criteriaValue = this.criteriaValue,
        imagePath = this.imagePath
    )
}