package ru.mav26.vkrapp.data.remote.models.tasks

import kotlinx.serialization.Serializable

@Serializable
data class DetailsEdit(
    val linkUrl: String? = null,
    val linkName: String? = null
)
