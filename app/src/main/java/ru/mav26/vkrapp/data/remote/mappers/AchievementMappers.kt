package ru.mav26.vkrapp.data.remote.mappers

import ru.mav26.vkrapp.data.remote.models.achievements.AchievementProgressResponse
import ru.mav26.vkrapp.data.remote.models.achievements.AchievementResponse
import ru.mav26.vkrapp.domain.model.achievements.Achievement
import ru.mav26.vkrapp.domain.model.achievements.AchievementProgress
import java.time.OffsetDateTime

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
        completeDate = OffsetDateTime.parse(this.completeDate)
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
        criteriaValue = this.criteriaValue
    )
}