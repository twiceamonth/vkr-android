package ru.mav26.vkrapp.domain.model.character

data class CharacterCreate(
    val characterName: String,
    val gender: Boolean,
    val hairId: String,
    val chestplateId: String,
    val footsId: String,
    val legsId: String,
    val backgroundId: String,
    val characterType: String
)
