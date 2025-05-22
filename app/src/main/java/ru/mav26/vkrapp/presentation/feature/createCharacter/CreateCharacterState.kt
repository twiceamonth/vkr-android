package ru.mav26.vkrapp.presentation.feature.createCharacter

import ru.mav26.vkrapp.domain.model.character.CharacterType

data class CreateCharacterState(
    val types: List<CharacterType> = emptyList(),
    val characterName: String = "",
    val characterGender: Boolean = true,
    val characterType: String = "",

    val isLoading: Boolean = false
)
