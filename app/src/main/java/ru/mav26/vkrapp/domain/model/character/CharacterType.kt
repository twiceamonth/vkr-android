package ru.mav26.vkrapp.domain.model.character

data class CharacterType(
    val characterType: String,
    val imagePath: String,
    val bonusType: String,
    val description: String,
    val effect: String,
    val multiplier: Int
)
