package ru.mav26.vkrapp.data.remote.models.character

import kotlinx.serialization.Serializable

@Serializable
data class CreateCharacter(
    val characterName: String,
    val gender: Boolean,
    val hairId: String,
    val chestplateId: String,
    val footsId: String,
    val legsId: String,
    val backgroundId: String,
    val characterType: String
)
