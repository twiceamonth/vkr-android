package ru.mav26.vkrapp.data.remote.models.tasks

import kotlinx.serialization.Serializable

@Serializable
data class DetailsResponse(
    val detailsId: String,
    val linkUrl: String,
    val linkName: String,
)
