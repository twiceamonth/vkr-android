package ru.mav26.vkrapp.data.remote.models.store

import kotlinx.serialization.Serializable

@Serializable
data class InventoryResponse(
    val type: String,
    val imagePath: String
)
