package ru.mav26.vkrapp.data.remote.models.character

import kotlinx.serialization.Serializable

@Serializable
data class CharacterItems(
    val hairId: String? = null,
    val chestplateId: String? = null,
    val footsId: String? = null,
    val legsId: String? = null,
    val backgroundId: String? = null,
)
